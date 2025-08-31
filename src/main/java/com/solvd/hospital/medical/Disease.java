package com.solvd.hospital.medical;

import java.util.List;
import com.solvd.hospital.model.MedicalEntity;

public class Disease extends MedicalEntity {
    private List<Symptom> symptoms;

    public Disease(String name, String description, List<Symptom> symptoms) {
        super(name, description);
        this.symptoms = symptoms;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String getEntityType() {
        return "Disease";
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", symptoms=" + getSymptoms() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disease disease = (Disease) obj;
        return name.equals(disease.name) && 
               description.equals(disease.description) && 
               symptoms.equals(disease.symptoms);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + description.hashCode() * 17 + symptoms.hashCode();
    }
} 