package com.solvd.hospital.medical;

import com.solvd.hospital.model.MedicalEntity;
import com.solvd.hospital.model.SymptomSeverity;

public class Symptom extends MedicalEntity {
    private SymptomSeverity severity;

    public Symptom(String name, String description, SymptomSeverity severity) {
        super(name, description);
        this.severity = severity;
    }

    public SymptomSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(SymptomSeverity severity) {
        this.severity = severity;
    }

    @Override
    public String getEntityType() {
        return "Symptom";
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", severity=" + severity +
                '}';
    }
}
