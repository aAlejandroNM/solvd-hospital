package com.solvd.hospital.service;

import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Patient;

public interface IDiagnosticService {
    Disease diagnose(Patient patient);
}
