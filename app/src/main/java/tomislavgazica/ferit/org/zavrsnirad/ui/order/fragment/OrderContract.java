package tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment;


import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodContract;

public interface OrderContract {

    interface View {

        void setFoods(List<Food> foods);

        void setDrinks(List<Drink> drinks);

        void setItemSizes(List<ItemSize> itemSizes);

        void setOrder(Order order);

        void setCategories(List<Category> categories);

    }

    interface Presenter {

        void setView(OrderContract.View view);

        void getOrderData();

        void addItemToOrder(String id);

        void removeItemFromOrder(String id);

        void removeAllItemsFromOrder();

        void uploadOrder();

        void removeAllItemIdsFromOrder(String id);



    }
}