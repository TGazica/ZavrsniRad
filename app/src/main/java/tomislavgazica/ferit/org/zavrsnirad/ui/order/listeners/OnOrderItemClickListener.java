package tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners;

import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;

public interface OnOrderItemClickListener {

    void addFoodToOrder(Food food);

    void removeFoodFromOrder(Food food);

    void removeFoodFromOrderAll(Food food);

    void addDrinkToOrder(Drink drink);

    void removeDrinkFromOrder(Drink drink);

    void removeDrinkFromOrderAll(Drink drink);

}
