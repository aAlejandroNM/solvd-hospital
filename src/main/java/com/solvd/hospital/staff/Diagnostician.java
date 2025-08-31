package com.solvd.hospital.staff;

import java.util.logging.Logger;
import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.service.IDiagnosticService;
import com.solvd.hospital.model.MedicalSpecialty;

public class Diagnostician extends HospitalStaff {
    private static final Logger LOGGER = Logger.getLogger(Diagnostician.class.getName());
    private final IDiagnosticService diagnosticService;

    public Diagnostician(IDiagnosticService diagnosticService, MedicalSpecialty specialty) {
        super("Diagnostician", 23, specialty);
        this.diagnosticService = diagnosticService;
    }

    public Disease diagnose(Patient patient) {
        return diagnosticService.diagnose(patient);
    }

    @Override
    public void performDuties() {
        LOGGER.info(" " + getName() + " is analyzing symptoms and diagnosing diseases");
    }

    @Override
    public String getRole() {
        return "Diagnostician";
    }

    @Override
    public String toString() {
        return "Diagnostician{" +
                "name='" + getName() + '\'' +
                ", specialty='" + getSpecialty() + '\'' +
                '}';
    }
}
