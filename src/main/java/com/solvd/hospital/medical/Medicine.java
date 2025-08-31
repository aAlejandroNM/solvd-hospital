package com.solvd.hospital.medical;

import com.solvd.hospital.model.MedicalItem;

public class Medicine extends MedicalItem<Integer> {
    private Integer quantity;

    public Medicine(String name, Integer quantity) {
        super(name, quantity);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getItemType() {
        return "Medicine";
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + getName() + '\'' +
                ", quantity=" + getQuantity() +
                '}';
    }
}
