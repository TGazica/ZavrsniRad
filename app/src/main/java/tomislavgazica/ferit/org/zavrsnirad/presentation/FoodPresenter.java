package tomislavgazica.ferit.org.zavrsnirad.presentation;

import tomislavgazica.ferit.org.zavrsnirad.dataHolder.DatabaseHolder;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodContract;

public class FoodPresenter implements FoodContract.Presenter {

    private FoodContract.View view;
    private OrderManager order;
    private DatabaseHolder databaseHolder;

    @Override
    public void setView(FoodContract.View view) {
        this.view = view;
        order = OrderManager.getInstance();
        setData();
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
    public void setData() {
        databaseHolder = DatabaseHolder.getInstance();
        view.setFoods(databaseHolder.getFoods());
        view.setCategories(databaseHolder.getCategories());
    }
}
