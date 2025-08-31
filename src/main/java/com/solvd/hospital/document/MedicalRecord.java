package com.solvd.hospital.document;

import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.model.MedicalDocument;

public class MedicalRecord extends MedicalDocument {

    private Patient patient;
    private Disease disease;

    public MedicalRecord(Patient patient, Disease disease) {
        super();
        this.patient = patient;
        this.disease = disease;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public String getDocumentType() {
        return "Medical Record";
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "patient=" + patient.getName() +
                ", disease=" + disease.getName() +
                ", creationDate=" + getCreationDate() +
                '}';
    }
} 