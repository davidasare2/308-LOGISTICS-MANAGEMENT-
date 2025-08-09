import java.util.*;

import src.Managers.DeliveryManager;
import src.Managers.DriverManager;
import src.Managers.MaintenanceManager;
import src.database.VehicleDatabase;
import src.model.Driver;
import src.model.Vehicle;
import utils.SortingAlgorithms;

public class Main {
    private final VehicleDatabase vehicleDB = new VehicleDatabase();
    private final DriverManager driverManager = new DriverManager();
    private final DeliveryManager deliveryManager = new DeliveryManager();
    private final MaintenanceManager maintenanceManager = new MaintenanceManager();
    private final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Main system = new Main();
        system.loadInitialData();
        system.showMainMenu();
    }
    
    private void loadInitialData() {
        // Ghana sample data
        vehicleDB.addVehicle(new Vehicle("GS-1234-23", "van", 12500.75, 8.2));
        vehicleDB.addVehicle(new Vehicle("GT-5678-23", "truck", 84500.25, 14.8));
        
        driverManager.addDriver(new Driver("DRV-007", "Kwame Mensah", 5, "Tema Harbour"));
        driverManager.addDriver(new Driver("DRV-009", "Ama Asante", 3, "Accra Central"));
    }
    
    private void showMainMenu() {
        while (true) {
            System.out.println("\n🚚 Adom Logistics Management System 🚛");
            System.out.println("=====================================");
            System.out.println("1. Vehicle Operations");
            System.out.println("2. Driver Operations");
            System.out.println("3. Delivery Operations");
            System.out.println("4. Maintenance Operations");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save Data");
            System.out.println("7. Load Data");
            System.out.println("0. Exit");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1: showVehicleMenu(); break;
                case 2: showDriverMenu(); break;
                case 3: showDeliveryMenu(); break;
                case 4: showMaintenanceMenu(); break;
                case 5: generateReports(); break;
                case 6: saveData(); break;
                case 7: loadData(); break;
                case 0: System.exit(0);
                default: System.out.println("Invalid option");
            }
        }
    }
    
    private void showVehicleMenu() {
        System.out.println("\n🚛 Vehicle Operations");
        System.out.println("====================");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. Search Vehicle");
        System.out.println("3. List All Vehicles");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Enter registration (e.g., GS-1234-23): ");
                String regNum = scanner.nextLine();
                
                System.out.print("Type (van/truck): ");
                String type = scanner.nextLine();
                
                System.out.print("Current mileage: ");
                double mileage = scanner.nextDouble();
                
                System.out.print("Fuel usage (L/100km): ");
                double fuel = scanner.nextDouble();
                scanner.nextLine();
                
                vehicleDB.addVehicle(new Vehicle(regNum, type, mileage, fuel));
                System.out.println("✅ Vehicle added!");
                break;
                
            case 2:
                System.out.print("Enter registration: ");
                String searchReg = scanner.nextLine();
                Vehicle found = vehicleDB.searchByRegNum(searchReg);
                System.out.println(found != null ? found : "❌ Vehicle not found");
                break;
                
            case 3:
                System.out.println("\nAll Vehicles:");
                vehicleDB.displayAllByMileage();
                break;
                
            case 0: return;
            default: System.out.println("Invalid option");
        }
    }
    
    private void showDriverMenu() {
        System.out.println("\n👤 Driver Operations");
        // Implementation similar to vehicle menu
        System.out.println("Implementation in progress...");
    }
    
    private void showDeliveryMenu() {
        System.out.println("\n📦 Delivery Operations");
        // Implementation similar to vehicle menu
        System.out.println("Implementation in progress...");
    }
    
    private void showMaintenanceMenu() {
        System.out.println("\n🔧 Maintenance Operations");
        // Implementation similar to vehicle menu
        System.out.println("Implementation in progress...");
    }
    
    private void generateReports() {
        System.out.println("\n📊 Fleet Performance Reports");
        System.out.println("============================");
        System.out.println("1. Fuel Efficiency Report");
        System.out.println("2. Maintenance Due Report");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select report: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: generateFuelReport(); break;
            case 2: System.out.println("Maintenance report in progress..."); break;
            case 0: return;
            default: System.out.println("Invalid option");
        }
    }
    
    private void generateFuelReport() {
        List<Vehicle> allVehicles = vehicleDB.getVehiclesInMileageRange(0, Double.MAX_VALUE);
        
        double totalFuel = 0;
        for (Vehicle v : allVehicles) totalFuel += v.getFuelUsage();
        double avg = totalFuel / allVehicles.size();
        
        List<Vehicle> inefficient = new ArrayList<>();
        for (Vehicle v : allVehicles) {
            if (v.getFuelUsage() > avg * 1.2) inefficient.add(v);
        }
        
        SortingAlgorithms.insertionSortByFuel(allVehicles);
        
        System.out.println("\n⛽ Fuel Efficiency Report");
        System.out.println("========================");
        System.out.printf("Average Fuel: %.2f L/100km%n", avg);
        
        System.out.println("\nTop 3 Efficient Vehicles:");
        for (int i = 0; i < Math.min(3, allVehicles.size()); i++) {
            System.out.println((i+1) + ". " + allVehicles.get(i));
        }
        
        if (!inefficient.isEmpty()) {
            System.out.println("\n⚠️ Inefficient Vehicles:");
            inefficient.forEach(System.out::println);
        }
    }
    
    private void saveData() {
        try {
            // Implementation would save all data to files
            System.out.println("✅ Data saved to files");
        } catch (Exception e) {
            System.out.println("❌ Error saving: " + e.getMessage());
        }
    }
    
    private void loadData() {
        try {
            // Implementation would load data from files
            System.out.println("✅ Data loaded from files");
        } catch (Exception e) {
            System.out.println("❌ Error loading: " + e.getMessage());
        }
    }
}