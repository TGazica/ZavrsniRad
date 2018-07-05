package tomislavgazica.ferit.org.zavrsnirad.model;


import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.interfaces.ResponseModelCallbacks;


public class ResponseModel {
    private List<Food> foods = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<ItemSize> itemSizes = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private List<RecommendedDrinks> recommendedDrinks = new ArrayList<>();
    private ResponseModelCallbacks responseModelCallbacks;

    public void setResponseModelCallbacks(ResponseModelCallbacks responseModelCallbacks){
        this.responseModelCallbacks = responseModelCallbacks;
    }

    public void addItems(DataSnapshot dataSnapshot){
        addFoods(dataSnapshot);
        addDrinks(dataSnapshot);
        addCategories(dataSnapshot);
        addItemSizes(dataSnapshot);
        addTables(dataSnapshot);
        addOrder(dataSnapshot);
        addRecommendedDrinks(dataSnapshot);
    }

    private void addRecommendedDrinks(DataSnapshot dataSnapshot) {
        recommendedDrinks.clear();
        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_RECOMMENDED).getChildren();

        for (DataSnapshot child: children) {
            RecommendedDrinks value = child.getValue(RecommendedDrinks.class);
            recommendedDrinks.add(value);
        }
        responseModelCallbacks.onRecommendedDrinksUpdated(recommendedDrinks);

    }

    private void addFoods(DataSnapshot dataSnapshot){
        foods.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_FOOD).getChildren();

        for (DataSnapshot child: children) {
            Food value = child.getValue(Food.class);
            foods.add(value);
        }
        responseModelCallbacks.onFoodUpdated(foods);

    }

    private void addDrinks(DataSnapshot dataSnapshot){
        drinks.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_DRINK).getChildren();

        for (DataSnapshot child: children) {
            Drink value = child.getValue(Drink.class);
            drinks.add(value);
        }
        responseModelCallbacks.onDrinkUpdated(drinks);

    }

    private void addCategories(DataSnapshot dataSnapshot){
        categories.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_CATEGORY).getChildren();

        for (DataSnapshot child: children) {
            Category value = child.getValue(Category.class);
            categories.add(value);
        }
        responseModelCallbacks.onCategoryUpdated(categories);
    }

    private void addItemSizes(DataSnapshot dataSnapshot){
        itemSizes.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_SIZE).getChildren();

        for (DataSnapshot child: children) {
            ItemSize value = child.getValue(ItemSize.class);
            itemSizes.add(value);
        }
        responseModelCallbacks.onItemSizeUpdated(itemSizes);
    }

    private void addOrder(DataSnapshot dataSnapshot){
        orders.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_ORDER).getChildren();

        for (DataSnapshot child: children) {
            Order value = child.getValue(Order.class);
            orders.add(value);
        }
        responseModelCallbacks.onOrderUpdated(orders);
    }

    private void addTables(DataSnapshot dataSnapshot){
        tables.clear();

        Iterable<DataSnapshot> children = dataSnapshot.child(Constants.FIREBASE_TABLE).getChildren();

        for (DataSnapshot child: children) {
            Table value = child.getValue(Table.class);
            tables.add(value);
        }
        responseModelCallbacks.onTableUpdated(tables);
    }

}
