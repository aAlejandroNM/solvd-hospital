package com.solvd.hospital.service;

import com.solvd.hospital.medical.Medicine;
import com.solvd.hospital.exception.InsufficientMedicineException;

import java.util.List;

public interface IInventoryService {

    boolean isMedicineAvailable(List<Medicine> availableMeds, Medicine medicine);
    void deliverMedications(List<Medicine> stock, List<Medicine> toDeliver) throws InsufficientMedicineException;

}
