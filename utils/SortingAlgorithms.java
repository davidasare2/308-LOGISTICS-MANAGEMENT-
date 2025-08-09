package utils;
import java.util.ArrayList;
import java.util.List;
import src.model.Driver;
import src.model.Vehicle;

public class SortingAlgorithms {
    public static void insertionSortByFuel(List<Vehicle> vehicles) {
        for (int i = 1; i < vehicles.size(); i++) {
            Vehicle key = vehicles.get(i);
            int j = i - 1;
            
            while (j >= 0 && vehicles.get(j).getFuelUsage() > key.getFuelUsage()) {
                vehicles.set(j + 1, vehicles.get(j));
                j--;
            }
            vehicles.set(j + 1, key);
        }
    }
    
    public static void mergeSortByMileage(List<Vehicle> vehicles) {
        if (vehicles.size() < 2) return;
        
        int mid = vehicles.size() / 2;
        List<Vehicle> left = new ArrayList<>(vehicles.subList(0, mid));
        List<Vehicle> right = new ArrayList<>(vehicles.subList(mid, vehicles.size()));
        
        mergeSortByMileage(left);
        mergeSortByMileage(right);
        merge(vehicles, left, right);
    }
    
    private static void merge(List<Vehicle> result, List<Vehicle> left, List<Vehicle> right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMileage() <= right.get(j).getMileage()) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }
    
    public static void quickSortByExperience(List<Driver> drivers, int low, int high) {
        if (low < high) {
            int pi = partition(drivers, low, high);
            quickSortByExperience(drivers, low, pi - 1);
            quickSortByExperience(drivers, pi + 1, high);
        }
    }
    
    private static int partition(List<Driver> drivers, int low, int high) {
        Driver pivot = drivers.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (drivers.get(j).getYearsExperience() > pivot.getYearsExperience()) {
                i++;
                swap(drivers, i, j);
            }
        }
        
        swap(drivers, i + 1, high);
        return i + 1;
    }
    
    private static void swap(List<Driver> list, int i, int j) {
        Driver temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}