import java.io.*;
import java.util.List;

public class FileStorage {
    public static void saveVehicles(List<Vehicle> vehicles, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Vehicle vehicle : vehicles) {
                writer.println(String.join(",",
                    vehicle.getRegistrationNumber(),
                    vehicle.getType(),
                    String.valueOf(vehicle.getMileage()),
                    String.valueOf(vehicle.getFuelUsage()),
                    vehicle.getAssignedDriverID() != null ? vehicle.getAssignedDriverID() : "N/A"
                ));
            }
        }
    }
    
    public static void saveDrivers(List<Driver> drivers, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Driver driver : drivers) {
                writer.println(String.join(",",
                    driver.getDriverID(),
                    driver.getName(),
                    driver.isAvailable() ? "1" : "0",
                    String.valueOf(driver.getYearsExperience()),
                    driver.getCurrentLocation(),
                    String.valueOf(driver.getDelayedDeliveries()),
                    String.valueOf(driver.getInfractions())
                ));
            }
        }
    }
    
    public static void loadVehicles(List<Vehicle> vehicles, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Vehicle vehicle = new Vehicle(
                        parts[0], parts[1], 
                        Double.parseDouble(parts[2]), 
                        Double.parseDouble(parts[3])
                    );
                    if (!"N/A".equals(parts[4])) {
                        vehicle.assignDriver(parts[4]);
                    }
                    vehicles.add(vehicle);
                }
            }
        }
    }
}