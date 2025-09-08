package com.solvd.hospital.document;

import java.util.List;
import java.util.logging.Logger;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.model.MedicalDocument;

public class PatientHistory<T extends MedicalDocument> extends MedicalDocument {

    private static final Logger LOGGER = Logger.getLogger(PatientHistory.class.getName());
    private Patient patient;
    private List<T> medicalDocuments;

    public PatientHistory(Patient patient, List<T> medicalDocuments) {
        super();
        this.patient = patient;
        this.medicalDocuments = medicalDocuments;
    }

    public List<T> getMedicalDocuments() {
        return medicalDocuments;
    }

    public void setMedicalDocuments(List<T> medicalDocuments) {
        this.medicalDocuments = medicalDocuments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void printHistory() {
        LOGGER.info("History for " + patient.getName());
        medicalDocuments
                .forEach(doc -> LOGGER.info(doc.toString()));
    }

    @Override
    public String getDocumentType() {
        return "Patient History";
    }
}
