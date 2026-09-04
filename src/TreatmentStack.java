public class TreatmentStack {

    private static class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for " + record.getPatientName() + " pushed onto history stack.");
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("The treatment history stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord popped = top.record;
        top = top.next;
        size--;
        return popped;
    }

    public TreatmentRecord peek() {
        if (isEmpty()) {
            System.out.println("The treatment history stack is empty.");
            return null;
        }
        return top.record;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records available.");
            return;
        }
        System.out.println("Treatment history (most recent first):");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}