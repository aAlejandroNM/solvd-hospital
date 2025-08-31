package com.solvd.hospital.document;

import java.time.LocalDate;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.staff.Doctor;
import com.solvd.hospital.model.AppointmentStatus;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private AppointmentStatus status;

    public Appointment(Patient patient, Doctor doctor, LocalDate date, AppointmentStatus status) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", date=" + getDate() +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return patient.equals(that.patient) && 
               doctor.equals(that.doctor) && 
               date.equals(that.date) &&
               status == that.status;
    }

    @Override
    public int hashCode() {
        return patient.hashCode() * 31 + doctor.hashCode() * 17 + date.hashCode() + status.hashCode();
    }
}
