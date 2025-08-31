package com.solvd.hospital.service.impl;

import com.solvd.hospital.Main;
import com.solvd.hospital.document.Appointment;
import com.solvd.hospital.exception.InvalidAppointmentException;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.model.AppointmentStatus;
import com.solvd.hospital.service.IAppointmentService;
import com.solvd.hospital.staff.Doctor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AppointmentServiceImpl implements IAppointmentService {
    public static final Logger LOGGER = LogManager.getLogger(AppointmentServiceImpl.class);

    @Override
    public Appointment scheduleAppointment(Patient patient, Doctor doctor) throws InvalidAppointmentException {
        if (patient == null) {
            throw new InvalidAppointmentException("The patient cannot be null.");
        }
        if (doctor == null) {
            throw new InvalidAppointmentException("The doctor cannot be null.");
        }
        LocalDate appointmentDate = LocalDate.now().plusDays(7);
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new InvalidAppointmentException("The appointment date cannot be in the past.");
        }
        LOGGER.info("Appointment scheduled for " + patient.getName() + " with Dr. " + doctor.getName() + " on " + appointmentDate);
        return new Appointment(patient, doctor, appointmentDate, AppointmentStatus.PENDING);
    }
}
