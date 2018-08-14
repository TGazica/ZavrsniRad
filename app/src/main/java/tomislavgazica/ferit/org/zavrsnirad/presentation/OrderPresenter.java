package tomislavgazica.ferit.org.zavrsnirad.presentation;

import tomislavgazica.ferit.org.zavrsnirad.dataHolder.DatabaseHolder;
import tomislavgazica.ferit.org.zavrsnirad.firebase.FirebaseManager;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment.OrderContract;

public class OrderPresenter implements OrderContract.Presenter {

    private OrderContract.View view;
    private OrderManager order;
    private FirebaseManager firebaseManager;
    private DatabaseHolder databaseHolder;

    @Override
    public void setView(OrderContract.View view) {
        this.view = view;
        order = OrderManager.getInstance();
        firebaseManager = FirebaseManager.getInstance();
    }


    @Override
    public void getOrderData() {
        view.setOrder(order.getOrder());
        databaseHolder = DatabaseHolder.getInstance();
        view.setDrinks(databaseHolder.getDrinks());
        view.setFoods(databaseHolder.getFoods());
        view.setCategories(databaseHolder.getCategories());
        view.setItemSizes(databaseHolder.getItemSizes());
    }

    @Override
    public void addItemToOrder(String id) {
        order.addItemToOrder(id);
    }

    @Override
    public void removeItemFromOrder(String id) {
        order.removeItemFromOrder(id);
    }

    @Override
    public void removeAllItemsFromOrder() {
        order.newOrder();
    }

    @Override
    public void uploadOrder() {
        firebaseManager.uploadOrder(order.getOrder());
        order.newOrder();
        view.setOrder(order.getOrder());
    }

    @Override
    public void removeAllItemIdsFromOrder(String id) {
        order.removeAllOfItemFromOrder(id);
    }
}
