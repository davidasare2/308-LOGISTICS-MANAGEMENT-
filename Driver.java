public class Driver {
    private final String driverID;
    private final String name;
    private boolean isAvailable;

    public Driver(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Driver ID must be provided");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Driver name must be provided");
        }
        
        this.driverID = id;
        this.name = name.trim();
        this.isAvailable = true;
    }

    public String getDriverID() { return driverID; }
    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailability(boolean status) { this.isAvailable = status; }
    
    @Override
    public String toString() {
        return String.format("Driver %s: %s | Status: %s",
                driverID,
                name,
                isAvailable ? "Available" : "On Assignment");
    }
}