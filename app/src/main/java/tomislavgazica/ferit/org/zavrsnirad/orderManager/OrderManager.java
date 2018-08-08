package tomislavgazica.ferit.org.zavrsnirad.orderManager;

import tomislavgazica.ferit.org.zavrsnirad.model.Order;

public class OrderManager {


    private static OrderManager ourInstance;
    private Order order;

    public static OrderManager getInstance() {
        if (ourInstance == null){
            ourInstance = new OrderManager();
        }
        return ourInstance;
    }

    private OrderManager() {
        order = new Order();
    }

    public void newOrder(){
        order = new Order();
    }

    public void addItemToOrder(String id){
        order.addItemToOrder(id);
    }

    public Order getOrder(){
        return order;
    }

    public void removeItemFromOrder(String id){
        order.removeItem(id);
    }

    public void removeAllOfItemFromOrder(String id){
        order.removeAllItems(id);
    }

}
