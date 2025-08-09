package src.Managers;
import java.util.ArrayList;
import java.util.List;

import src.model.MaintenanceRecord;
import src.model.Vehicle;

public class MaintenanceManager {
    private final List<Vehicle> maintenanceHeap;
    
    public MaintenanceManager() {
        maintenanceHeap = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        maintenanceHeap.add(vehicle);
        heapifyUp(maintenanceHeap.size() - 1);
    }
    
    public Vehicle getNextForMaintenance() {
        if (maintenanceHeap.isEmpty()) return null;
        
        Vehicle next = maintenanceHeap.get(0);
        Vehicle last = maintenanceHeap.remove(maintenanceHeap.size() - 1);
        
        if (!maintenanceHeap.isEmpty()) {
            maintenanceHeap.set(0, last);
            heapifyDown(0);
        }
        
        return next;
    }
    
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (maintenanceHeap.get(index).getDueServiceMileage() < 
                maintenanceHeap.get(parentIndex).getDueServiceMileage()) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }
    
    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        
        if (leftChild < maintenanceHeap.size() && 
            maintenanceHeap.get(leftChild).getDueServiceMileage() < 
            maintenanceHeap.get(smallest).getDueServiceMileage()) {
            smallest = leftChild;
        }
        
        if (rightChild < maintenanceHeap.size() && 
            maintenanceHeap.get(rightChild).getDueServiceMileage() < 
            maintenanceHeap.get(smallest).getDueServiceMileage()) {
            smallest = rightChild;
        }
        
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
    
    private void swap(int i, int j) {
        Vehicle temp = maintenanceHeap.get(i);
        maintenanceHeap.set(i, maintenanceHeap.get(j));
        maintenanceHeap.set(j, temp);
    }
    
    public void recordMaintenance(Vehicle vehicle, MaintenanceRecord record) {
        vehicle.addMaintenanceRecord(record);
        maintenanceHeap.remove(vehicle);
        addVehicle(vehicle);
    }
}