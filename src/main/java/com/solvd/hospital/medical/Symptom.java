package com.solvd.hospital.medical;

import com.solvd.hospital.model.MedicalEntity;
import com.solvd.hospital.model.SymptomSeverity;

public record Symptom(String name, String description, SymptomSeverity severity) implements MedicalEntity {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getEntityType() {
        return "Symptom";
    }

    public String toString() {
        return "Symptom{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                '}';
    }
    public SymptomSeverity getSeverity() {
        return severity;
    }
}
