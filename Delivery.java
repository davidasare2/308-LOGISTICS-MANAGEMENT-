public class Delivery {
    private final String packageID;
    private final String origin;
    private final String destination;
    private String vehicleRegNum;
    private String driverID;
    private String eta;

    public Delivery(String pkgID, String origin, String dest) {
        if (pkgID == null || pkgID.isBlank()) {
            throw new IllegalArgumentException("Package ID must be provided");
        }
        
        this.packageID = pkgID;
        this.origin = (origin != null && !origin.isBlank()) ? origin : "Unknown Origin";
        this.destination = (dest != null && !dest.isBlank()) ? dest : "Unknown Destination";
    }

    public void assignResources(String vehicleReg, String driverID) {
        if (vehicleReg == null || vehicleReg.isBlank()) {
            throw new IllegalArgumentException("Vehicle registration required");
        }
        if (driverID == null || driverID.isBlank()) {
            throw new IllegalArgumentException("Driver ID required");
        }
        
        this.vehicleRegNum = vehicleReg;
        this.driverID = driverID;
    }

    public void setETA(String eta) { 
        this.eta = (eta != null && !eta.isBlank()) ? eta : "Not Estimated"; 
    }

    public String getPackageID() { return packageID; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getVehicleRegNum() { return vehicleRegNum; }
    public String getDriverID() { return driverID; }
    public String getETA() { return eta; }
    
    @Override
    public String toString() {
        return String.format("Package %s\nRoute: %s → %s\nETA: %s\nAssigned: Driver %s | Vehicle %s",
                packageID,
                origin,
                destination,
                eta,
                driverID != null ? driverID : "None",
                vehicleRegNum != null ? vehicleRegNum : "None");
    }
}