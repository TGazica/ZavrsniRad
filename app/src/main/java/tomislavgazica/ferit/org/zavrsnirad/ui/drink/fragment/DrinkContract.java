package tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;

public interface DrinkContract {

    interface View{

        void setDrinks(List<Drink> drinks);

        void setOrder(Order order);

        void setCategories(List<Category> categories);

    }

    interface Presenter{

        void setView(DrinkContract.View view);

        void getOrderedDrinks();

        void addDrinkToOrder(String id);

        void removeDrinkFromOrder(String id);

        void setData();
    }

}
