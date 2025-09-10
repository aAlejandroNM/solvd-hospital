package com.solvd.hospital.medical;

import java.util.List;
import com.solvd.hospital.model.MedicalEntity;

public record Disease(String name, String description, List<Symptom> symptoms) implements MedicalEntity {
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
        return "Disease";
    }
    public List<Symptom> getSymptoms() {
        return symptoms;
    }
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", symptoms=" + symptoms +
                '}';
    }
}
