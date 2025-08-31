package com.solvd.hospital.medical;

import java.util.Set;
import com.solvd.hospital.model.Person;

public class Patient extends Person {
    private Set<Symptom> symptoms;
    private Treatment treatment;

    public Patient(String name, Integer age, Set<Symptom> symptoms) {
        super(name, age);
        this.symptoms = symptoms;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", symptoms=" + getSymptoms() +
                ", treatment=" + getTreatment() +
                '}';
    }
}
