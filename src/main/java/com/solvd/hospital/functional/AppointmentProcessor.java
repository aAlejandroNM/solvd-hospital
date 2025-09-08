package com.solvd.hospital.functional;

import com.solvd.hospital.document.Appointment;

@FunctionalInterface
public interface AppointmentProcessor {
    Appointment process(Appointment appointment);
}

