public class EmergencyQueue {

    private static class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " (ID: " + patient.getPatientId() +
                ") added to the emergency waiting queue.");
    }

    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty. No patients waiting.");
            return null;
        }
        Patient dequeuedPatient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return dequeuedPatient;
    }

    public Patient peek() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty.");
            return null;
        }
        return front.patient;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        System.out.println("Patients currently waiting (front -> rear):");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}