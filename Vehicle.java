import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Comparable<Vehicle> {
    private final String registrationNumber;
    private final String type;
    private double mileage;
    private final double fuelUsage;
    private String assignedDriverID;
    private double lastServiceMileage;
    private final double serviceInterval = 10000;
    private final List<MaintenanceRecord> maintenanceHistory;

    public Vehicle(String regNum, String type, double mileage, double fuelUsage) {
        if (regNum == null || regNum.isBlank()) throw new IllegalArgumentException("Registration number required");
        if (!"truck".equalsIgnoreCase(type) && !"van".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Vehicle type must be 'truck' or 'van'");
        }
        
        // Ghana plate validation
        if (!regNum.matches("(GS|GT)-\\d{4}-\\d{2}")) {
            System.out.println("⚠️  Warning: Non-standard Ghana plate format");
        }
        
        this.registrationNumber = regNum;
        this.type = type.toLowerCase();
        this.mileage = Math.max(0, mileage);
        this.fuelUsage = Math.max(0, fuelUsage);
        this.assignedDriverID = null;
        this.lastServiceMileage = 0;
        this.maintenanceHistory = new ArrayList<>();
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public String getType() { return type; }
    public double getMileage() { return mileage; }
    public double getFuelUsage() { return fuelUsage; }
    public String getAssignedDriverID() { return assignedDriverID; }
    public double getDueServiceMileage() { return lastServiceMileage + serviceInterval; }
    public List<MaintenanceRecord> getMaintenanceHistory() { return maintenanceHistory; }
    
    public void assignDriver(String driverID) { 
        this.assignedDriverID = driverID; 
    }
    
    public void updateMileage(double additionalKm) {
        this.mileage += Math.max(0, additionalKm);
    }
    
    public void addMaintenanceRecord(MaintenanceRecord record) {
        maintenanceHistory.add(record);
        lastServiceMileage = mileage;
    }
    
    @Override
    public int compareTo(Vehicle other) {
        return Double.compare(this.mileage, other.mileage);
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s | Mileage: %,.1f km | Fuel: %.1f L/100km | Service Due: %,.1f km",
                registrationNumber, 
                type.toUpperCase(),
                mileage,
                fuelUsage,
                getDueServiceMileage() - mileage);
    }
}