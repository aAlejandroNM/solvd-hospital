package com.solvd.hospital.model;

public abstract class MedicalItem<T> {
    protected String name;
    protected T details;

    public MedicalItem(String name, T details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }

    public abstract String getItemType();

    @Override
    public String toString() {
        return "MedicalItem{" +
                "name='" + getName() + '\'' +
                ", details=" + details +
                '}';
    }
}
