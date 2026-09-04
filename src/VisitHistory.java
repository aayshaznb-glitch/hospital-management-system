public class VisitHistory {
    private Visit head;
    private int size;

    public VisitHistory() {
        this.head = null;
        this.size = 0;
    }

    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
        } else {
            Visit current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newVisit;
        }
        size++;
    }

    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }
        if (head.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }
        Visit current = head;
        while (current.next != null && current.next.getVisitId() != visitId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.getVisitId() == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayHistory() {
        if (head == null) {
            System.out.println("   No visit history available.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("   " + current);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}