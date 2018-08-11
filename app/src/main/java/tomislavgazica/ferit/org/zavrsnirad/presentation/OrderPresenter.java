package tomislavgazica.ferit.org.zavrsnirad.presentation;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.firebase.FirebaseManager;
import tomislavgazica.ferit.org.zavrsnirad.interfaces.FirebaseCallbacks;
import tomislavgazica.ferit.org.zavrsnirad.interfaces.ResponseModelCallbacks;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.model.ResponseModel;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment.OrderContract;

public class OrderPresenter implements OrderContract.Presenter {

    private OrderContract.View view;
    private OrderManager order;
    private FirebaseManager firebaseManager;

    @Override
    public void setView(OrderContract.View view) {
        this.view = view;
        order = OrderManager.getInstance();
        firebaseManager = FirebaseManager.getInstance();
    }


    @Override
    public void getOrderData() {
        view.setOrder(order.getOrder());
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
