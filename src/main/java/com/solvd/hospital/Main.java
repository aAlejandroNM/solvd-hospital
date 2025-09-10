package com.solvd.hospital;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import com.solvd.hospital.annotation.AuditUtil;
import com.solvd.hospital.exception.*;
import com.solvd.hospital.model.AppointmentStatus;
import com.solvd.hospital.model.MedicalSpecialty;
import com.solvd.hospital.model.SymptomSeverity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospital.medical.*;
import com.solvd.hospital.service.*;
import com.solvd.hospital.service.impl.*;
import com.solvd.hospital.staff.*;
import com.solvd.hospital.document.*;
import com.solvd.hospital.document.Appointment;
import com.solvd.hospital.document.MedicalRecord;
import com.solvd.hospital.medical.*;
import com.solvd.hospital.service.*;
import com.solvd.hospital.service.impl.*;
import com.solvd.hospital.staff.Diagnostician;
import com.solvd.hospital.staff.Doctor;
import com.solvd.hospital.repository.Repository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import com.solvd.hospital.functional.PatientFilter;
import com.solvd.hospital.functional.AppointmentProcessor;
import com.solvd.hospital.functional.MedicineAction;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws PatientNotFoundException {

        ITreatmentService treatmentService = new TreatmentServiceImpl();
        IAppointmentService appointmentService = new AppointmentServiceImpl();
        IInventoryService inventoryService = new InventoryServiceImpl();
        HistoryServiceImpl historyService = new HistoryServiceImpl();

        Symptom fever = new Symptom("Fever", "High temperature", SymptomSeverity.SEVERE);
        Symptom headache = new Symptom("Headache", "head pain", SymptomSeverity.MODERATE);
        Symptom cough = new Symptom("Cough", "Persistent cough", SymptomSeverity.MILD);
        Symptom fatigue = new Symptom("Fatigue", "Extreme tiredness", SymptomSeverity.MODERATE);
        Symptom nausea = new Symptom("Nausea", "Feeling of sickness", SymptomSeverity.MILD);
        Symptom vomiting = new Symptom("Vomiting", "Expelling stomach contents", SymptomSeverity.SEVERE);

        Medicine paracetamol = new Medicine("Paracetamol", 5);
        Medicine ibuprofen = new Medicine("Ibuprofen", 2);
        Medicine omeprazole = new Medicine("Omeprazole", 0);
        Medicine antacids = new Medicine("Antacids", 4);
        Medicine aspirin = new Medicine("Aspirin", 10);

        Set<Symptom> symptomsJhon = new HashSet<>(List.of(fever, cough, fatigue));
        Patient jhon = new Patient("Jhon Smith", 30, symptomsJhon);
        LOGGER.info("Síntomas únicos de Jhon: " + jhon.getSymptoms());

        List<Disease> knownDiseases = List.of(
                new Disease("Flu", "Influenza virus infection", List.of(fever, cough, fatigue)),
                new Disease("Gastritis", "Inflammation of the stomach lining", List.of(nausea, vomiting)),
                new Disease("Migraine", "Severe headache often accompanied by nausea", List.of(headache, fatigue))
        );
        IDiagnosticService diagnosticService = new DiagnosticServiceImpl(knownDiseases);
        Diagnostician diagnostician = new Diagnostician(diagnosticService, MedicalSpecialty.GENERAL_MEDICINE);

        Disease resultDisease = diagnostician.diagnose(jhon);
        LOGGER.info(jhon.getName() + " has : " + resultDisease.getName() + " - " + resultDisease.getDescription());

        MedicalRecord record = new MedicalRecord(jhon, resultDisease);
        Doctor doctor = new Doctor("Juan", 24, MedicalSpecialty.GENERAL_MEDICINE);

        try {
            historyService.addRecord(jhon, record, doctor);
        } catch (UnauthorizedAccessException | DuplicateMedicalRecordException e) {
            LOGGER.error("Unauthorized access when adding medical record: " + e.getMessage());
        }

        Diagnostician diagnosticianStaff = new Diagnostician(diagnosticService, MedicalSpecialty.GENERAL_MEDICINE);
        try {
            historyService.addRecord(jhon, record, diagnosticianStaff);
        } catch (UnauthorizedAccessException | DuplicateMedicalRecordException e) {
            LOGGER.error("Unauthorized access when adding medical record: " + e.getMessage());
        }

        Treatment treatment = treatmentService.planTreatment(jhon, resultDisease);
        LOGGER.info("Treatment: " + treatment.toString());

        doctor.assignTreatment(jhon, treatment);

        try {
            Appointment appointment = appointmentService.scheduleAppointment(jhon, doctor);
        } catch (InvalidAppointmentException e) {
            LOGGER.error("Error when scheduling a medical appointment: " + e.getMessage());
        }

        List<Medicine> medicineStock = List.of(paracetamol, ibuprofen, omeprazole, antacids, aspirin);
        treatment.getMedications().forEach(med -> {
            if (inventoryService.isMedicineAvailable(medicineStock, med)) {
                LOGGER.info("Medication " + med.getName() + " is available in stock.");
            } else {
                LOGGER.info("Medication " + med.getName() + " is not available in stock.");
            }
        });

        try {
            inventoryService.deliverMedications(medicineStock, treatment.getMedications());
        } catch (InsufficientMedicineException e) {
            LOGGER.error("Error delivering medication: " + e.getMessage());
        }

        Repository<Medicine> medicineRepository = new Repository<>();
        medicineRepository.add(paracetamol);
        medicineRepository.add(ibuprofen);
        medicineRepository.add(omeprazole);
        medicineRepository.add(antacids);
        medicineRepository.add(aspirin);

        LOGGER.info("Current medications:");
        medicineRepository.findAll()
                .forEach(med -> LOGGER.info(med.getName() + " (Cantidad: " + med.getQuantity() + ")"));

        medicineRepository.remove(omeprazole);
        LOGGER.info("Medications after discontinuing Omeprazole:");
        medicineRepository.findAll()
                .forEach(med -> LOGGER.info(med.getName() + " (quantity: " + med.getQuantity() + ")"));

        historyService.printHistory(jhon);

        Queue<Patient> waitingRoom = new LinkedList<>();
        waitingRoom.add(jhon);
        Patient ana = new Patient("Ana Lopez", 25, new HashSet<>(List.of(headache, nausea)));
        waitingRoom.add(ana);
        LOGGER.info("Patients in the waiting room: ");
        waitingRoom.forEach(p -> LOGGER.info(p.getName()));
        Patient nextPatient = waitingRoom.poll();
        assert nextPatient != null;
        LOGGER.info("Attending to: " + nextPatient.getName());

        Stack<MedicalRecord> medicalRecordStack = new Stack<>();
        try {
            historyService.addRecord(jhon, record, doctor);
            medicalRecordStack.push(record);
        } catch (UnauthorizedAccessException | DuplicateMedicalRecordException e) {
            LOGGER.error("Unauthorized access when adding medical record: " + e.getMessage());
        }

        MedicalRecord record2 = new MedicalRecord(jhon, resultDisease);
        try {
            historyService.addRecord(jhon, record2, doctor);
            medicalRecordStack.push(record2);
        } catch (UnauthorizedAccessException | DuplicateMedicalRecordException e) {
            LOGGER.error("Unauthorized access when adding medical record: " + e.getMessage());
        }

        if (!medicalRecordStack.isEmpty()) {
            MedicalRecord lastRecord = medicalRecordStack.pop();
            try {
                historyService.removeLastRecord(jhon);
                LOGGER.info("The last medical action has been undone.: " + lastRecord);
            } catch (PatientNotFoundException e) {
                LOGGER.error("Error while undoing the last medical action: " + e.getMessage());
            }
        }

        historyService.printHistory(jhon);

        Doctor cardiologist = new Doctor("Dr. House", 45, MedicalSpecialty.CARDIOLOGY);
        Doctor pediatrician = new Doctor("Dr. Grey", 38, MedicalSpecialty.PEDIATRICS);
        Doctor neurologist = new Doctor("Dr. Strange", 50, MedicalSpecialty.NEUROLOGY);

        Appointment app1 = new Appointment(jhon, cardiologist, java.time.LocalDate.now(), AppointmentStatus.PENDING);
        Appointment app2 = new Appointment(jhon, pediatrician, java.time.LocalDate.now().plusDays(1),AppointmentStatus.COMPLETED);
        Appointment app3 = new Appointment(jhon, neurologist, java.time.LocalDate.now().plusDays(2), AppointmentStatus.PENDING);
        List<Appointment> appointments = List.of(app1, app2, app3);
        LOGGER.info("PENDING appointments:");
        appointments.stream()
            .filter(a -> a.getStatus() == com.solvd.hospital.model.AppointmentStatus.PENDING)
            .forEach(a -> LOGGER.info(a));

        MedicalSpecialty requiredSpecialty = MedicalSpecialty.CARDIOLOGY;
        Doctor assignedDoctor = List.of(cardiologist, pediatrician, neurologist).stream()
            .filter(d -> d.getSpecialty() == requiredSpecialty)
            .findFirst()
            .orElse(null);
        if (assignedDoctor != null) {
            LOGGER.info("Doctor assigned to John by specialty (" + requiredSpecialty + "): " + assignedDoctor.getName());
        } else {
            LOGGER.info("There is no doctor available with the required specialty.: " + requiredSpecialty);
        }

        List<Symptom> symptoms = List.of(fever, headache, cough, fatigue, nausea, vomiting);
        symptoms.stream()
            .filter(s -> s.getSeverity() == SymptomSeverity.SEVERE || s.getSeverity() == SymptomSeverity.CRITICAL)
            .forEach(s -> LOGGER.warn("Alert! Serious symptom detected: " + s.getName() + " (" + s.getSeverity() + ")"));


        PatientFilter severeSymptomFilter = p -> p.getSymptoms().stream()
                .anyMatch(s -> s.getSeverity() == SymptomSeverity.SEVERE);
        LOGGER.info("Patients with severe symptoms in the waiting room:");
        waitingRoom.stream().filter(severeSymptomFilter::test)
                .forEach(p -> LOGGER.info(p.getName()));


        AppointmentProcessor completeAppointment = a -> {
            a.setStatus(AppointmentStatus.COMPLETED);
            return a;
        };
        try {
            Appointment appointment = appointmentService.scheduleAppointment(jhon, doctor);
            Appointment completed = completeAppointment.process(appointment);
            LOGGER.info("Appointment marked as COMPLETED for: " + completed.getPatient().getName());
        } catch (InvalidAppointmentException e) {
            LOGGER.error("Error when scheduling a medical appointment: " + e.getMessage());
        }

        MedicineAction discountStock = m -> m.setQuantity(m.getQuantity() - 1);
        LOGGER.info("Discounting stock of medicines used in treatment:");
        treatment.getMedications().forEach(discountStock::apply);
        treatment.getMedications().forEach(m -> LOGGER.info(m.getName() + " current stock: " + m.getQuantity()));

        List<String> wordsToFind = List.of("Java", "ai", "Scalability", "data", "and", "of", "easier", "in");
        String inputPath = "src/main/resources/input.txt";
        String outputPath = "src/main/resources/output.txt";
        findWordsAndWriteCounts(wordsToFind, inputPath, outputPath);

        AuditUtil.logAuditableMethods(HistoryServiceImpl.class);
        AuditUtil.logAuditableMethods(InventoryServiceImpl.class);
    }

    public static void findWordsAndWriteCounts(List<String> wordsToFind, String inputPath, String outputPath) {
        try {
            String content = FileUtils.readFileToString(new File(inputPath), StandardCharsets.UTF_8);

            String[] contentSeparated = content.split("\\W+");

            String result = wordsToFind.stream()
                    .map(word -> word + ": " +
                            java.util.Arrays.stream(contentSeparated)
                                    .filter(actualWord -> actualWord.equalsIgnoreCase(word))
                                    .count())
                    .collect(java.util.stream.Collectors.joining(System.lineSeparator()));


            FileUtils.writeStringToFile(new File(outputPath), result, StandardCharsets.UTF_8, true);
            LOGGER.info("findWordsAndWriteCounts completed");
        } catch (IOException e) {
            LOGGER.error("Error reading or writing file: " + e.getMessage());
        }
    }
}
