package tomislavgazica.ferit.org.zavrsnirad.presentation;

import tomislavgazica.ferit.org.zavrsnirad.dataHolder.DatabaseHolder;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkContract;

public class DrinkPresenter implements DrinkContract.Presenter{

    private DrinkContract.View view;
    private OrderManager order;
    private DatabaseHolder databaseHolder;

    @Override
    public void setView(DrinkContract.View view) {
        this.view = view;
        order = OrderManager.getInstance();
    }

    @Override
    public void getOrderedDrinks() {
        view.setOrder(order.getOrder());
    }

    @Override
    public void addDrinkToOrder(String id) {
        order.addItemToOrder(id);
    }

    @Override
    public void removeDrinkFromOrder(String id) {
        order.removeItemFromOrder(id);
    }

    @Override
    public void setData() {
        databaseHolder = DatabaseHolder.getInstance();
        view.setCategories(databaseHolder.getCategories());
        view.setDrinks(databaseHolder.getDrinks());
        view.setOrder(order.getOrder());
    }
}
