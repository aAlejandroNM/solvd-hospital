package com.solvd.hospital.model;

import java.time.LocalDate;

public abstract class MedicalDocument {
    protected LocalDate creationDate;

    public MedicalDocument() {
        this.creationDate = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public abstract String getDocumentType();

    @Override
    public String toString() {
        return "MedicalDocument{" +
                "creationDate=" + getCreationDate() +
                '}';
    }
} 