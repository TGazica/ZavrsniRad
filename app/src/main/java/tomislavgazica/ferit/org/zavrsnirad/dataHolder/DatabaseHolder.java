package tomislavgazica.ferit.org.zavrsnirad.dataHolder;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;

public class DatabaseHolder {

    private static DatabaseHolder instance;

    private List<Food> foods;
    private List<Drink> drinks;
    private List<Category> categories;
    private List<ItemSize> itemSizes;
    private List<RecommendedDrinks> recommendedDrinks;
    private List<Table> tables;

    private DatabaseHolder(){
        foods = new ArrayList<>();
        drinks = new ArrayList<>();
        categories = new ArrayList<>();
        itemSizes = new ArrayList<>();
        recommendedDrinks = new ArrayList<>();
        tables = new ArrayList<>();
    }

    public static DatabaseHolder getInstance() {
        if (instance == null){
            instance = new DatabaseHolder();
        }
        return instance;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods.clear();
        this.foods.addAll(foods);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks.clear();
        this.drinks.addAll(drinks);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
    }

    public List<ItemSize> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes.clear();
        this.itemSizes.addAll(itemSizes);
    }

    public List<RecommendedDrinks> getRecommendedDrinks() {
        return recommendedDrinks;
    }

    public void setRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks) {
        this.recommendedDrinks.clear();
        this.recommendedDrinks.addAll(recommendedDrinks);
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables.clear();
        this.tables.addAll(tables);
    }
}
