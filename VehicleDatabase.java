import java.util.ArrayList;
import java.util.List;

public class VehicleDatabase {
    private static class BSTNode {
        Vehicle vehicle;
        BSTNode left, right;
        
        BSTNode(Vehicle vehicle) {
            this.vehicle = vehicle;
        }
    }
    
    private static class HashNode {
        String type;
        List<Vehicle> vehicles;
        HashNode next;
        
        HashNode(String type) {
            this.type = type;
            this.vehicles = new ArrayList<>();
        }
    }
    
    private BSTNode mileageRoot;
    private final int HASH_SIZE = 10;
    private HashNode[] typeTable = new HashNode[HASH_SIZE];
    
    public void addVehicle(Vehicle vehicle) {
        mileageRoot = insertBST(mileageRoot, vehicle);
        addToHashTable(vehicle);
    }
    
    private BSTNode insertBST(BSTNode node, Vehicle vehicle) {
        if (node == null) return new BSTNode(vehicle);
        
        if (vehicle.compareTo(node.vehicle) < 0) {
            node.left = insertBST(node.left, vehicle);
        } else {
            node.right = insertBST(node.right, vehicle);
        }
        
        return node;
    }
    
    private void addToHashTable(Vehicle vehicle) {
        int index = hashFunction(vehicle.getType());
        HashNode current = typeTable[index];
        
        while (current != null) {
            if (current.type.equals(vehicle.getType())) {
                current.vehicles.add(vehicle);
                return;
            }
            current = current.next;
        }
        
        HashNode newNode = new HashNode(vehicle.getType());
        newNode.vehicles.add(vehicle);
        newNode.next = typeTable[index];
        typeTable[index] = newNode;
    }
    
    private int hashFunction(String type) {
        return Math.abs(type.hashCode()) % HASH_SIZE;
    }
    
    public Vehicle searchByRegNum(String regNum) {
        return searchBST(mileageRoot, regNum);
    }
    
    private Vehicle searchBST(BSTNode node, String regNum) {
        if (node == null) return null;
        
        if (node.vehicle.getRegistrationNumber().equals(regNum)) {
            return node.vehicle;
        }
        
        Vehicle left = searchBST(node.left, regNum);
        if (left != null) return left;
        
        return searchBST(node.right, regNum);
    }
    
    public List<Vehicle> getVehiclesByType(String type) {
        int index = hashFunction(type);
        HashNode current = typeTable[index];
        
        while (current != null) {
            if (current.type.equals(type)) {
                return current.vehicles;
            }
            current = current.next;
        }
        
        return new ArrayList<>();
    }
    
    public List<Vehicle> getVehiclesInMileageRange(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        inOrderRange(mileageRoot, min, max, result);
        return result;
    }
    
    private void inOrderRange(BSTNode node, double min, double max, List<Vehicle> result) {
        if (node == null) return;
        
        inOrderRange(node.left, min, max, result);
        
        if (node.vehicle.getMileage() >= min && 
            node.vehicle.getMileage() <= max) {
            result.add(node.vehicle);
        }
        
        inOrderRange(node.right, min, max, result);
    }
    
    public void displayAllByMileage() {
        inOrderDisplay(mileageRoot);
    }
    
    private void inOrderDisplay(BSTNode node) {
        if (node != null) {
            inOrderDisplay(node.left);
            System.out.println(node.vehicle);
            inOrderDisplay(node.right);
        }
    }
}