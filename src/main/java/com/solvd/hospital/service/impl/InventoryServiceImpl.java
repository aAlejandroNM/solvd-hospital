package com.solvd.hospital.service.impl;

import com.solvd.hospital.Main;
import com.solvd.hospital.annotation.Auditable;
import com.solvd.hospital.exception.InsufficientMedicineException;
import com.solvd.hospital.medical.Medicine;
import com.solvd.hospital.service.IInventoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class InventoryServiceImpl implements IInventoryService {
    public static final Logger LOGGER = LogManager.getLogger(InventoryServiceImpl.class);

    @Override
    public boolean isMedicineAvailable(List<Medicine> availableMeds, Medicine medicine) {
        return availableMeds.stream()
                .anyMatch(med -> med.getName().equals(medicine.getName()) && med.getQuantity() >= medicine.getQuantity());

    }

    @Override
    @Auditable(action = "deliverMedications")
    public void deliverMedications(List<Medicine> stock, List<Medicine> toDeliver) throws InsufficientMedicineException {
        for (Medicine medToDeliver : toDeliver) {
            Optional<Medicine> optionalStockMed = stock.stream()
                    .filter(med -> med.getName().equals(medToDeliver.getName()))
                    .findFirst();

            if (optionalStockMed.isPresent()) {
                Medicine stockMed = optionalStockMed.get();
                if (stockMed.getQuantity() > 0) {
                    stockMed.setQuantity(stockMed.getQuantity() - 1);
                    LOGGER.info("Delivering " + stockMed.getName() + " (remaining: " + stockMed.getQuantity() + ")");
                } else {
                    throw new InsufficientMedicineException("There is not enough stock of " + stockMed.getName() + ".");
                }
            } else {
                throw new InsufficientMedicineException("The medicine " + medToDeliver.getName() + " not in stock.");
            }
        }
    }
}
