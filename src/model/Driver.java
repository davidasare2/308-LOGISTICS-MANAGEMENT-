package src.model;
public class Driver {
    private final String driverID;
    private final String name;
    private boolean isAvailable;
    private int yearsExperience;
    private String currentLocation;
    private int delayedDeliveries;
    private int infractions;

    public Driver(String id, String name, int exp, String location) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Driver ID required");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Driver name required");
        
        this.driverID = id;
        this.name = name.trim();
        this.isAvailable = true;
        this.yearsExperience = Math.max(0, exp);
        this.currentLocation = (location != null && !location.isBlank()) ? location : "Depot";
        this.delayedDeliveries = 0;
        this.infractions = 0;
    }

    public String getDriverID() { return driverID; }
    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }
    public int getYearsExperience() { return yearsExperience; }
    public String getCurrentLocation() { return currentLocation; }
    public int getDelayedDeliveries() { return delayedDeliveries; }
    public int getInfractions() { return infractions; }
    
    public void setAvailability(boolean status) { 
        this.isAvailable = status; 
    }
    
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
    
    public void recordDelay() {
        delayedDeliveries++;
    }
    
    public void recordInfraction() {
        infractions++;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s) | Exp: %d yrs | Location: %s | Status: %s | Delays: %d | Infractions: %d",
                name, driverID, yearsExperience, currentLocation,
                isAvailable ? "Available" : "On Assignment",
                delayedDeliveries, infractions);
    }
}