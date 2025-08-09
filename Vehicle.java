public class Vehicle {
    private final String registrationNumber;
    private final String type;
    private double mileage;
    private final double fuelUsage;
    private String assignedDriverID;

    public Vehicle(String regNum, String type, double mileage, double fuelUsage) {
        if (regNum == null || regNum.isBlank()) {
            throw new IllegalArgumentException("Registration number must be provided");
        }
        if (!"truck".equalsIgnoreCase(type) && !"van".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Vehicle type must be 'truck' or 'van'");
        }
        
        this.registrationNumber = regNum;
        this.type = type.toLowerCase();
        this.mileage = Math.max(0, mileage);
        this.fuelUsage = Math.max(0, fuelUsage);
        this.assignedDriverID = null;
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public String getType() { return type; }
    public double getMileage() { return mileage; }
    public double getFuelUsage() { return fuelUsage; }
    public String getAssignedDriverID() { return assignedDriverID; }
    
    public void assignDriver(String driverID) { 
        this.assignedDriverID = driverID; 
    }
    
    public void removeDriver() {
        this.assignedDriverID = null;
    }
    
    @Override
    public String toString() {
        return String.format("Vehicle %s (%s) | Mileage: %,.1f km | Fuel: %.1f L/100km | Driver: %s",
                registrationNumber, 
                type.toUpperCase(),
                mileage,
                fuelUsage,
                assignedDriverID != null ? assignedDriverID : "Unassigned");
    }
}