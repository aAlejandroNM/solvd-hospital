package com.solvd.hospital.medical;

import java.util.List;
import com.solvd.hospital.model.MedicalItem;

public class Treatment extends MedicalItem<Disease> {

    private Patient patient;
    private Disease diseaseToBeTreated;
    private List<Medicine> medications;
    private List<String> indications;

    public Treatment(Patient patient, Disease disease, List<Medicine> medications, List<String> indications) {
        super(patient.getName(), disease);
        this.patient = patient;
        this.diseaseToBeTreated = disease;
        this.medications = medications;
        this.indications = indications;
    }

    public List<Medicine> getMedications() {
        return medications;
    }

    public void setMedications(List<Medicine> medications) {
        this.medications = medications;
    }

    public Disease getDiseaseToBeTreated() {
        return diseaseToBeTreated;
    }

    public void setDiseaseToBeTreated(Disease diseaseToBeTreated) {
        this.diseaseToBeTreated = diseaseToBeTreated;
    }

    public List<String> getIndications() {
        return indications;
    }

    public void setIndications(List<String> indications) {
        this.indications = indications;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getItemType() {
        return "Treatment";
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "name='" + getName() + '\'' +
                ", patient=" + patient.getName() +
                ", disease=" + diseaseToBeTreated.getName() +
                ", medications=" + getMedications() +
                ", indications=" + getIndications() +
                '}';
    }
}
