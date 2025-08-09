package src.model;
public class Delivery {
    private final String packageID;
    private String origin;
    private String destination;
    private String vehicleRegNum;
    private String driverID;
    private String eta;
    private String status;
    private String currentLocation;

    public Delivery(String pkgID, String origin, String dest) {
        if (pkgID == null || pkgID.isBlank()) throw new IllegalArgumentException("Package ID required");
        
        this.packageID = pkgID;
        this.origin = (origin != null && !origin.isBlank()) ? origin : "Unknown";
        this.destination = (dest != null && !dest.isBlank()) ? dest : "Unknown";
        this.status = "PENDING";
        this.currentLocation = this.origin;
    }

    public void assignResources(String vehicleReg, String driverID) {
        if (vehicleReg == null || vehicleReg.isBlank()) throw new IllegalArgumentException("Vehicle required");
        if (driverID == null || driverID.isBlank()) throw new IllegalArgumentException("Driver required");
        
        this.vehicleRegNum = vehicleReg;
        this.driverID = driverID;
        this.status = "ASSIGNED";
    }

    public void setETA(String eta) { 
        this.eta = (eta != null && !eta.isBlank()) ? eta : "Not Estimated"; 
    }
    
    public void updateStatus(String newStatus, String location) {
        this.status = newStatus;
        this.currentLocation = location;
    }
    
    public void reroute(String newDestination) {
        this.destination = newDestination;
        this.status = "REROUTED";
    }

    public String getPackageID() { return packageID; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getVehicleRegNum() { return vehicleRegNum; }
    public String getDriverID() { return driverID; }
    public String getETA() { return eta; }
    public String getStatus() { return status; }
    public String getCurrentLocation() { return currentLocation; }
    
    @Override
    public String toString() {
        return String.format("Package %s: %s → %s | Status: %s\nCurrent Location: %s | ETA: %s\nAssigned: Driver %s | Vehicle %s",
                packageID, origin, destination, status, currentLocation, eta,
                driverID != null ? driverID : "None", 
                vehicleRegNum != null ? vehicleRegNum : "None");
    }
}