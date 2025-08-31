package com.solvd.hospital.model;

public abstract class MedicalEntity {
    protected String name;
    protected String description;

    public MedicalEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getEntityType();

    @Override
    public String toString() {
        return "MedicalEntity{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
} 