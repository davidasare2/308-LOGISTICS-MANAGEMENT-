import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MaintenanceRecord {
    private final String vehicleRegNum;
    private final LocalDate serviceDate;
    private final String replacedPart;
    private final double cost;

    public MaintenanceRecord(String regNum, String date, String part, double cost) {
        if (regNum == null || regNum.isBlank()) {
            throw new IllegalArgumentException("Vehicle registration required");
        }
        
        this.vehicleRegNum = regNum;
        this.serviceDate = parseDate(date);
        this.replacedPart = (part != null && !part.isBlank()) ? part : "General Service";
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
    public String getReplacedPart() { return replacedPart; }
    public double getCost() { return cost; }
    
    @Override
    public String toString() {
        return String.format("Maintenance Record for %s\nDate: %s | Part: %s | Cost: GHS %,.2f",
                vehicleRegNum,
                getServiceDate(),
                replacedPart,
                cost);
    }
}