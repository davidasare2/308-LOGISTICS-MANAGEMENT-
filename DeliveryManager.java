public class DeliveryManager {
    private static class DeliveryNode {
        Delivery delivery;
        DeliveryNode next;
        
        DeliveryNode(Delivery delivery) {
            this.delivery = delivery;
        }
    }
    
    private DeliveryNode head;
    private int size;
    
    public void addDelivery(Delivery delivery) {
        DeliveryNode newNode = new DeliveryNode(delivery);
        
        if (head == null) {
            head = newNode;
        } else {
            DeliveryNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public Delivery getNextDelivery() {
        if (head == null) return null;
        
        Delivery delivery = head.delivery;
        head = head.next;
        size--;
        return delivery;
    }
    
    public void updateDeliveryStatus(String packageID, String newStatus, String location) {
        DeliveryNode current = head;
        while (current != null) {
            if (current.delivery.getPackageID().equals(packageID)) {
                current.delivery.updateStatus(newStatus, location);
                return;
            }
            current = current.next;
        }
        System.out.println("Package not found: " + packageID);
    }
    
    public void rerouteDelivery(String packageID, String newDestination) {
        DeliveryNode current = head;
        while (current != null) {
            if (current.delivery.getPackageID().equals(packageID)) {
                current.delivery.reroute(newDestination);
                return;
            }
            current = current.next;
        }
        System.out.println("Package not found: " + packageID);
    }
    
    public void displayAllDeliveries() {
        DeliveryNode current = head;
        while (current != null) {
            System.out.println(current.delivery);
            System.out.println("----------------------------");
            current = current.next;
        }
    }
}