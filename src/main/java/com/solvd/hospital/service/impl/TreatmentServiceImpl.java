package com.solvd.hospital.service.impl;

import com.solvd.hospital.medical.Disease;
import com.solvd.hospital.medical.Medicine;
import com.solvd.hospital.medical.Patient;
import com.solvd.hospital.medical.Treatment;
import com.solvd.hospital.service.ITreatmentService;

import java.util.List;

public class TreatmentServiceImpl implements ITreatmentService {
    @Override
    public Treatment planTreatment(Patient patient, Disease disease) {
        List<String> indications;
        List<Medicine> medications;

        switch (disease.getName()) {
            case "Flu" -> {
                indications = List.of("Rest", "Hydration");
                medications = List.of(new Medicine("Paracetamol",1), new Medicine("Ibuprofen",1));
            }
            case "Gastritis" -> {
                indications = List.of("Avoid spicy food", "Eat small portions");
                medications = List.of(new Medicine("Omeprazole",1), new Medicine("Antacids",1));
            }
            case "Migraine" -> {
                indications = List.of("Rest in a dark room", "Hydration");
                medications = List.of(new Medicine("Ibuprofen",1), new Medicine("Aspirin",1));
            }
            default -> {
                throw new IllegalArgumentException("No treatment available for the disease: " + disease.getName());
            }
        }

        return new Treatment(patient, disease, medications, indications);
    }
}
