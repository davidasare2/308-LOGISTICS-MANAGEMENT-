package src.Managers;

import adomlogistics.core.Delivery;
import adomlogistics.datastructures.LinkedList;
import adomlogistics.datastructures.LinkedList.Consumer;

public class DeliveryManager {
    private final LinkedList<Delivery> deliveries = new LinkedList<>();
    
    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }
    
    public void processDeliveries(Consumer<Delivery> handler) {
        deliveries.forEach(handler);
    }
    
    public interface DeliveryHandler {
        void handle(Delivery delivery);
    }
}