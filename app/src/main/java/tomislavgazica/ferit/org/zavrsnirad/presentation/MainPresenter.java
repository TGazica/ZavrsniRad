package tomislavgazica.ferit.org.zavrsnirad.presentation;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.dataHolder.DatabaseHolder;
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
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.MainContract;

public class MainPresenter implements MainContract.Presenter, FirebaseCallbacks, ResponseModelCallbacks {

    private MainContract.View view;
    private FirebaseManager firebaseManager;
    private ResponseModel responseModel;
    private DatabaseHolder databaseHolder;

    public MainPresenter(){
        databaseHolder = DatabaseHolder.getInstance();
        responseModel = new ResponseModel();
        responseModel.setResponseModelCallbacks(this);
        firebaseManager = FirebaseManager.getInstance();
        firebaseManager.setFirebaseCallbacks(this);
        firebaseManager.setListener();
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getTables() {
        view.setTables(databaseHolder.getTables());
    }

    @Override
    public void onNewItem(DataSnapshot dataSnapshot) {
        responseModel.addItems(dataSnapshot);
    }

    @Override
    public void onFoodUpdated(List<Food> foods) {
        databaseHolder.setFoods(foods);
    }

    @Override
    public void onDrinkUpdated(List<Drink> drinks) {
        databaseHolder.setDrinks(drinks);
    }

    @Override
    public void onCategoryUpdated(List<Category> categories) {
        databaseHolder.setCategories(categories);
    }

    @Override
    public void onItemSizeUpdated(List<ItemSize> itemSizes) {
        databaseHolder.setItemSizes(itemSizes);
    }

    @Override
    public void onOrderUpdated(List<Order> orders) {
    }

    @Override
    public void onTableUpdated(List<Table> tables) {
        databaseHolder.setTables(tables);
    }

    @Override
    public void onRecommendedDrinksUpdated(List<RecommendedDrinks> recommendedDrinks) {
        databaseHolder.setRecommendedDrinks(recommendedDrinks);
    }

    @Override
    public void onDataProcessed() {
        view.onDataUpdated();
    }
}
