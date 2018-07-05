package tomislavgazica.ferit.org.zavrsnirad.interfaces;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;


public interface ResponseModelCallbacks {
    void onFoodUpdated(List<Food> foods);
    void onDrinkUpdated(List<Drink> drinks);
    void onCategoryUpdated(List<Category> categories);
    void onItemSizeUpdated(List<ItemSize> itemSizes);
    void onOrderUpdated(List<Order> orders);
    void onTableUpdated(List<Table> tables);
    void onRecommendedDrinksUpdated(List<RecommendedDrinks> recommendedDrinks);
}
