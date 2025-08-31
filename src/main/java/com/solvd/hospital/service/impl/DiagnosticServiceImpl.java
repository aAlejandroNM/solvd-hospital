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
        for (Disease disease : knownDiseases) {
            int matchingSymptoms = 0;

            for (Symptom diseaseSymptom : disease.getSymptoms()) {
                for (Symptom patientSymptom : patient.getSymptoms()) {
                    if (symptomsMatch(diseaseSymptom, patientSymptom)) {
                        matchingSymptoms++;
                        break;
                    }
                }
            }

            if (matchingSymptoms == disease.getSymptoms().size()) {
                return disease;
            }
        }
        return new Disease("Unknown", "No disease matches the symptoms", new ArrayList<>());
    }
    private boolean symptomsMatch(Symptom diseaseSymptom, Symptom patientSymptom) {
        return diseaseSymptom.getName().equals(patientSymptom.getName());
    }
}
