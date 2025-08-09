package src.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MaintenanceRecord {
    private final String vehicleRegNum;
    private final LocalDate serviceDate;
    private final String serviceType;
    private final String replacedParts;
    private final double cost;

    public MaintenanceRecord(String regNum, String date, String serviceType, String parts, double cost) {
        if (regNum == null || regNum.isBlank()) throw new IllegalArgumentException("Vehicle registration required");
        
        this.vehicleRegNum = regNum;
        this.serviceDate = parseDate(date);
        this.serviceType = (serviceType != null && !serviceType.isBlank()) ? serviceType : "Routine";
        this.replacedParts = (parts != null && !parts.isBlank()) ? parts : "N/A";
        this.cost = Math.max(0, cost);
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return LocalDate.now();
        }
        
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Using current date");
            return LocalDate.now();
        }
    }

    public String getVehicleRegNum() { return vehicleRegNum; }
    public String getServiceDate() { 
        return serviceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
    }
    public String getServiceType() { return serviceType; }
    public String getReplacedParts() { return replacedParts; }
    public double getCost() { return cost; }
    
    @Override
    public String toString() {
        return String.format("%s | %s | Parts: %s | Cost: GHS %,.2f",
                getServiceDate(), serviceType, replacedParts, cost);
    }
}