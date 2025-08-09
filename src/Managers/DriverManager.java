package src.Managers;

import adomlogistics.core.Driver;
import adomlogistics.datastructures.Queue;
import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    private final Queue<Driver> availableDrivers = new Queue<>();
    private final List<Driver> allDrivers = new ArrayList<>();
    
    public void addDriver(Driver driver) {
        allDrivers.add(driver);
        availableDrivers.enqueue(driver);
    }
    
    public Driver assignDriver() {
        Driver driver = availableDrivers.dequeue();
        if (driver != null) {
            driver.setAvailability(false);
        }
        return driver;
    }
    
    public void releaseDriver(Driver driver) {
        driver.setAvailability(true);
        availableDrivers.enqueue(driver);
    }
}