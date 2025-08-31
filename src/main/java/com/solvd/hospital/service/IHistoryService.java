package com.solvd.hospital.service;

import com.solvd.hospital.document.MedicalRecord;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.staff.HospitalStaff;
import com.solvd.hospital.exception.UnauthorizedAccessException;
import com.solvd.hospital.exception.PatientNotFoundException;
import com.solvd.hospital.exception.DuplicateMedicalRecordException;

public interface IHistoryService {

    void addRecord(Patient patient, MedicalRecord medicalRecord, HospitalStaff staff) throws UnauthorizedAccessException, PatientNotFoundException, DuplicateMedicalRecordException;
    void printHistory(Patient patient) throws PatientNotFoundException;
}
