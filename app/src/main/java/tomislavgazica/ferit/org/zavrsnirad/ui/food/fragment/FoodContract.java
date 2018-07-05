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

        void setDrinks(List<Drink> drinks);

        void setRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks);

        void setItemSizes(List<ItemSize> itemSizes);

        void setOrder(Order order);

        void setCategories(List<Category> categories);

    }

    interface Presenter{

        //Postavi pogled s kojim komuniciraš

        void setView(FoodContract.View view);

        //Vrati pogledu hranu

        void getFoodData();

        //dodaj hranu u narudžbu

        void addItemToOrder(String id);

        //Makni hranu iz narudžbe

        void removeItemFromOrder(String id);

        //Metoda za prekid kada se pogled ne vidi

        void onDestroy();
    }

}
