package com.solvd.hospital.service;

import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.medical.Treatment;

public interface ITreatmentService {
    Treatment planTreatment(Patient patient, Disease disease);
}
