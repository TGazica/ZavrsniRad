package tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners;

import tomislavgazica.ferit.org.zavrsnirad.model.Food;

public interface OnFoodSizesClickListener {

    void addItemToOrder(Food food);
    void removeItemFromOrder(Food food);

}
