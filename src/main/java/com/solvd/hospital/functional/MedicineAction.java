package com.solvd.hospital.functional;

import com.solvd.hospital.medical.Medicine;

@FunctionalInterface
public interface MedicineAction {
    void apply(Medicine medicine);
}


