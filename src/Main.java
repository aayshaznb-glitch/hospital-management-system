import java.util.Scanner;

public class Main {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.displayInOrder();
                case 5 -> enqueuePatient();
                case 6 -> dequeuePatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> treatmentStack.displayStack();
                case 9 -> popTreatment();
                case 10 -> addVisit();
                case 11 -> searchVisit();
                case 12 -> removeVisit();
                case 13 -> displayVisitHistory();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("========================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("========================================================");
        System.out.println(" -- Patient Records (BST) --");
        System.out.println(" 1. Register new patient");
        System.out.println(" 2. Search patient by ID");
        System.out.println(" 3. Delete patient by ID");
        System.out.println(" 4. Display all patients (in-order)");
        System.out.println(" -- Emergency Queue --");
        System.out.println(" 5. Add patient to emergency queue (enqueue)");
        System.out.println(" 6. Treat next patient (dequeue) -> moves to treatment stack");
        System.out.println(" 7. Display waiting queue");
        System.out.println(" -- Treatment History (Stack) --");
        System.out.println(" 8. Display treatment history");
        System.out.println(" 9. Undo / remove most recent treatment record (pop)");
        System.out.println(" -- Patient Visit History (Linked List) --");
        System.out.println(" 10. Add a visit record to a patient");
        System.out.println(" 11. Search a visit record for a patient");
        System.out.println(" 12. Remove a visit record from a patient");
        System.out.println(" 13. Display a patient's full visit history");
        System.out.println(" 0. Exit");
        System.out.println("========================================================");
    }

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with that ID already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = sc.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + p);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "No patient found with ID " + id);
    }

    private static void enqueuePatient() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("No such patient. Register the patient first (option 1).");
            return;
        }
        emergencyQueue.enqueue(p);
    }

    private static void dequeuePatient() {
        Patient treated = emergencyQueue.dequeue();
        if (treated == null) {
            return;
        }
        System.out.println("Now treating: " + treated);

        System.out.print("Enter treatment details for this patient: ");
        String details = sc.nextLine();
        System.out.print("Enter completion date (e.g. 2026-09-04): ");
        String date = sc.nextLine();

        TreatmentRecord record = new TreatmentRecord(treated.getPatientId(), treated.getName(), details, date);
        treatmentStack.push(record);
    }

    private static void popTreatment() {
        TreatmentRecord popped = treatmentStack.pop();
        if (popped != null) {
            System.out.println("Removed most recent treatment record: " + popped);
        }
    }

    private static void addVisit() {
        int patientId = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(patientId);
        if (p == null) {
            System.out.println("No such patient.");
            return;
        }
        int visitId = readInt("Enter Visit ID: ");
        System.out.print("Enter Visit Date: ");
        String date = sc.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = sc.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = sc.nextLine();

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        p.getVisitHistory().addVisit(visit);
        System.out.println("Visit record added to patient " + p.getName() + "'s history.");
    }

    private static void searchVisit() {
        int patientId = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(patientId);
        if (p == null) {
            System.out.println("No such patient.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit v = p.getVisitHistory().searchVisit(visitId);
        System.out.println(v == null ? "Visit not found." : "Found: " + v);
    }

    private static void removeVisit() {
        int patientId = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(patientId);
        if (p == null) {
            System.out.println("No such patient.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = p.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit not found.");
    }

    private static void displayVisitHistory() {
        int patientId = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(patientId);
        if (p == null) {
            System.out.println("No such patient.");
            return;
        }
        System.out.println("Visit history for " + p.getName() + ":");
        p.getVisitHistory().displayHistory();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static void seedSampleData() {
        patientBST.insert(new Patient(101, "Kamal Perera", 45, "0771234567", "Chest pain"));
        patientBST.insert(new Patient(102, "Nadeesha Silva", 29, "0719876543", "Fracture"));
        patientBST.insert(new Patient(103, "Ruwan Fernando", 60, "0765554433", "High fever"));

        Patient p101 = patientBST.search(101);
        p101.getVisitHistory().addVisit(new Visit(1, "2026-01-10", "Dr. Jayasuriya", "Angina", "ECG + Medication"));

        emergencyQueue.enqueue(p101);
    }
}