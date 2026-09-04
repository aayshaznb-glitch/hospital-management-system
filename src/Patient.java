public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private VisitHistory visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getContactNumber() { return contactNumber; }
    public String getMedicalCondition() { return medicalCondition; }
    public VisitHistory getVisitHistory() { return visitHistory; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Age: %-3d | Contact: %-12s | Condition: %s",
                patientId, name, age, contactNumber, medicalCondition);
    }
}