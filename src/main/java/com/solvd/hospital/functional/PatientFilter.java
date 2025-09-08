package com.solvd.hospital.functional;

import com.solvd.hospital.medical.Patient;

@FunctionalInterface
public interface PatientFilter {
    boolean test(Patient patient);
}