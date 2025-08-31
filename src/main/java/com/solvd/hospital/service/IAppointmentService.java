package com.solvd.hospital.service;

import com.solvd.hospital.document.Appointment;
import com.solvd.hospital.exception.InvalidAppointmentException;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.staff.Doctor;

public interface IAppointmentService {
    Appointment scheduleAppointment(Patient patient, Doctor doctor) throws InvalidAppointmentException;
}
