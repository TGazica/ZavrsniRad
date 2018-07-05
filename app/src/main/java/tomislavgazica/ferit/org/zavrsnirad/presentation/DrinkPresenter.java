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
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkContract;

public class DrinkPresenter implements DrinkContract.Presenter, FirebaseCallbacks, ResponseModelCallbacks{

    private DrinkContract.View view;
    private FirebaseManager firebaseManager;
    private ResponseModel responseModel;
    private OrderManager order;

    @Override
    public void setView(DrinkContract.View view) {
        this.view = view;
        order = OrderManager.getInstance();
        this.responseModel = new ResponseModel();
        responseModel.setResponseModelCallbacks(this);
        firebaseManager = FirebaseManager.getInstance();
        firebaseManager.setFirebaseCallbacks(this);
        firebaseManager.setListener();
    }

    @Override
    public void getDrinkData() {
        view.setOrder(order.getOrder());
    }

    @Override
    public void addDrinkToOrder(String id) {
        order.addItemToOrder(id);
    }

    @Override
    public void removeDrinkFromOrder(String id) {
        order.removeIdFromOrder(id);
    }

    @Override
    public void onDestroy() {
        firebaseManager.removeListener();
    }

    @Override
    public void onNewItem(DataSnapshot dataSnapshot) {
        responseModel.addItems(dataSnapshot);
    }

    @Override
    public void onFoodUpdated(List<Food> foods) {

    }

    @Override
    public void onDrinkUpdated(List<Drink> drinks) {
        view.setDrinks(drinks);
    }

    @Override
    public void onCategoryUpdated(List<Category> categories) {
        view.setCategories(categories);
    }

    @Override
    public void onItemSizeUpdated(List<ItemSize> itemSizes) {

    }

    @Override
    public void onOrderUpdated(List<Order> orders) {

    }

    @Override
    public void onTableUpdated(List<Table> tables) {

    }

    @Override
    public void onRecommendedDrinksUpdated(List<RecommendedDrinks> recommendedDrinks) {

    }
}
