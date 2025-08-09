public class Main {
    public static void main(String[] args) {
        System.out.println("🚚 Logistics Management System - Phase 1 🚚");
        System.out.println("==========================================\n");
        
        Vehicle deliveryVan = new Vehicle("KLM-2024", "van", 12500.75, 8.2);
        Driver driverSarah = new Driver("DRV-007", "Sarah Chen");
        Delivery urgentDelivery = new Delivery("PKG-ULTRA", "Central Warehouse", "Downtown Office");
        MaintenanceRecord vanService = new MaintenanceRecord("KLM-2024", "20/09/2023", "Oil Filter", 1250.75);
        
        deliveryVan.assignDriver(driverSarah.getDriverID());
        driverSarah.setAvailability(false);
        urgentDelivery.assignResources(deliveryVan.getRegistrationNumber(), driverSarah.getDriverID());
        urgentDelivery.setETA("15:45 25/10/2023");
        
        System.out.println("=== VEHICLE ===");
        System.out.println(deliveryVan);
        System.out.println("\n=== DRIVER ===");
        System.out.println(driverSarah);
        System.out.println("\n=== DELIVERY ===");
        System.out.println(urgentDelivery);
        System.out.println("\n=== MAINTENANCE ===");
        System.out.println(vanService);
        System.out.println("\nSystem initialization complete ✅");
    }
}