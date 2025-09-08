package com.solvd.hospital.service.impl;

import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.medical.Symptom;
import com.solvd.hospital.service.IDiagnosticService;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticServiceImpl implements IDiagnosticService {
    private final ArrayList<Disease> knownDiseases;
    public DiagnosticServiceImpl(List<Disease> knownDiseases) {
        this.knownDiseases = new ArrayList<>(knownDiseases);
    }
    @Override
    public Disease diagnose(Patient patient) {
        return knownDiseases.stream()
                .filter(disease -> disease.getSymptoms().stream()
                        .allMatch(diseaseSymptom -> patient.getSymptoms().stream()
                                .anyMatch(patientSymptom -> symptomsMatch(diseaseSymptom, patientSymptom))))
                .findFirst()
                .orElse(new Disease("Unknown", "No disease matches the symptoms", new ArrayList<>()));

    }
    private boolean symptomsMatch(Symptom diseaseSymptom, Symptom patientSymptom) {
        return diseaseSymptom.getName().equals(patientSymptom.getName());
    }
}
