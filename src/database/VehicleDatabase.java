package src.database;

import adomlogistics.core.Vehicle;
import adomlogistics.datastructures.BST;
import adomlogistics.datastructures.HashTable;
import java.util.ArrayList;
import java.util.List;

public class VehicleDatabase {
    private final BST<Double, Vehicle> mileageTree = new BST<>();
    private final HashTable<String, List<Vehicle>> typeTable = new HashTable<>();

    public void addVehicle(Vehicle vehicle) {
        // Add to BST sorted by mileage
        mileageTree.put(vehicle.getMileage(), vehicle);
        
        // Add to hash table by type
        String type = vehicle.getType();
        List<Vehicle> vehicles = typeTable.get(type);
        if (vehicles == null) {
            vehicles = new ArrayList<>();
            typeTable.put(type, vehicles);
        }
        vehicles.add(vehicle);
    }

    public Vehicle searchByMileage(double mileage) {
        return mileageTree.get(mileage);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> result = typeTable.get(type);
        return result != null ? result : new ArrayList<>();
    }

    public void traverseByMileage(BST.Visitor<Vehicle> visitor) {
        mileageTree.inOrderTraversal(visitor);
    }
}