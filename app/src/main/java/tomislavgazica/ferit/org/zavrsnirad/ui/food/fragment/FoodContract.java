package tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkContract;

public interface FoodContract {

    interface View{

        void setFoods(List<Food> foods);

        void setCategories(List<Category> categories);

    }

    interface Presenter{

        void setView(FoodContract.View view);

        void addItemToOrder(String id);

        void removeItemFromOrder(String id);

        void setData();

    }

}
