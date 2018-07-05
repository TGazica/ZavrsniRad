package tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail;

import android.net.Uri;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodContract;

public interface ItemDetailContract {

    interface View{

        void setImage(Uri uri);

        void setOrder(Order order);

        void setItemRecommendedDrinks(List<Drink> drinks);

    }

    interface Presenter{

        void getOrder();

        void setView(ItemDetailContract.View view);

        void getPictureUri(Food food);

        void addItemToOrder(String id);

        void removeItemFromOrder(String id);

        void getItemRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks, List<Drink> drink, Food food);

    }

}
