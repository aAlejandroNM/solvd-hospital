package com.solvd.hospital.staff;

import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.medical.Treatment;
import com.solvd.hospital.model.MedicalSpecialty;

import java.util.logging.Logger;

public class Doctor extends HospitalStaff {
    private static final Logger LOGGER = Logger.getLogger(Doctor.class.getName());

    public Doctor(String name, int age, MedicalSpecialty specialty) {
        super(name, age, specialty);
    }

    public void assignTreatment(Patient patient, Treatment treatment) {
        patient.setTreatment(treatment);
    }

    @Override
    public void performDuties() {
        LOGGER.info("Doctor " + getName() + " is performing main.java.com.solv.hospital.medical duties");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + getName() + '\'' +
                ", specialty='" + getSpecialty() + '\'' +
                '}';
    }
}
