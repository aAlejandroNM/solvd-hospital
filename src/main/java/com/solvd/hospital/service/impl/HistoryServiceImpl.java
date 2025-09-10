package com.solvd.hospital.service.impl;

import com.solvd.hospital.exception.PatientNotFoundException;
import com.solvd.hospital.Main;
import com.solvd.hospital.document.MedicalRecord;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.service.IHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.staff.HospitalStaff;
import com.solvd.hospital.staff.Doctor;
import com.solvd.hospital.exception.UnauthorizedAccessException;
import com.solvd.hospital.exception.DuplicateMedicalRecordException;
import com.solvd.hospital.annotation.Auditable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryServiceImpl implements IHistoryService {
    public static final Logger LOGGER = LogManager.getLogger(HistoryServiceImpl.class);
    private final Map<Patient, List<MedicalRecord>> store = new HashMap<>();
    @Override
    @Auditable(action = "addRecord")
    public void addRecord(Patient patient, MedicalRecord record, HospitalStaff staff) throws UnauthorizedAccessException, PatientNotFoundException, DuplicateMedicalRecordException {
        if (patient == null) {
            throw new PatientNotFoundException("The patient is null or does not exist in the system.");
        }
        if (!(staff instanceof Doctor)) {
            throw new UnauthorizedAccessException("Only a doctor can add medical records to the history.");
        }
        List<MedicalRecord> records = store.computeIfAbsent(patient, p -> new ArrayList<>());
        if (records.contains(record)) {
            throw new DuplicateMedicalRecordException("A medical record already exists for this patient.");
        }
        records.add(record);
    }

    @Override
    public void printHistory(Patient patient) throws PatientNotFoundException {
        if (patient == null || !store.containsKey(patient)) {
            throw new PatientNotFoundException("The patient was not found in the system.");
        }
        List<MedicalRecord> history = store.get(patient);
        if (history != null) {
            LOGGER.info("Medical History for " + patient.getName() + ":");
            for (MedicalRecord record : history) {
                LOGGER.info(record.toString());
            }
        } else {
            LOGGER.info("No history found for " + patient.getName() + ".");
        }
    }

    @Override
    @Auditable(action = "removeLastRecord")
    public void removeLastRecord(Patient patient) throws PatientNotFoundException {
        if (patient == null || !store.containsKey(patient)) {
            throw new PatientNotFoundException("The patient was not found in the system.");
        }
        List<MedicalRecord> records = store.get(patient);
        if (records != null && !records.isEmpty()) {
            records.remove(records.size() - 1);
        }
    }
}
