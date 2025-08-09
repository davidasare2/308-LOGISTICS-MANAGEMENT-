import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    private static class QueueNode {
        Driver driver;
        QueueNode next;
        
        QueueNode(Driver driver) {
            this.driver = driver;
        }
    }
    
    private QueueNode front, rear;
    private final List<Driver> allDrivers = new ArrayList<>();
    
    public void addDriver(Driver driver) {
        allDrivers.add(driver);
        enqueue(driver);
    }
    
    private void enqueue(Driver driver) {
        QueueNode newNode = new QueueNode(driver);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }
    
    public Driver assignDriver() {
        if (front == null) return null;
        
        Driver driver = front.driver;
        driver.setAvailability(false);
        
        front = front.next;
        if (front == null) {
            rear = null;
        }
        
        return driver;
    }
    
    public void releaseDriver(Driver driver) {
        driver.setAvailability(true);
        enqueue(driver);
    }
    
    public Driver findNearestDriver(String location) {
        Driver nearest = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (Driver driver : allDrivers) {
            if (driver.isAvailable()) {
                int distance = Math.abs(
                    driver.getCurrentLocation().hashCode() - location.hashCode()
                );
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = driver;
                }
            }
        }
        return nearest;
    }
    
    public Driver findMostExperiencedDriver() {
        Driver mostExperienced = null;
        int maxExp = -1;
        
        for (Driver driver : allDrivers) {
            if (driver.isAvailable() && driver.getYearsExperience() > maxExp) {
                maxExp = driver.getYearsExperience();
                mostExperienced = driver;
            }
        }
        return mostExperienced;
    }
}