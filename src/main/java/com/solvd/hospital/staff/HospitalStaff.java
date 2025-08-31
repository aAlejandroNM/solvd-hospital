package com.solvd.hospital.staff;

import com.solvd.hospital.model.MedicalSpecialty;
import com.solvd.hospital.model.Person;

public abstract class HospitalStaff extends Person {
    protected MedicalSpecialty specialty;

    public HospitalStaff(String name, Integer age, MedicalSpecialty specialty) {
        super(name, age);
        this.specialty = specialty;
    }

    public MedicalSpecialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(MedicalSpecialty specialty) {
        this.specialty = specialty;
    }

    public abstract void performDuties();

    @Override
    public String getRole() {
        return "Hospital Staff";
    }

    @Override
    public String toString() {
        return "HospitalStaff{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
