package tomislavgazica.ferit.org.zavrsnirad.ui.drink.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;

public class DrinkViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.itemDrinkName)
    TextView itemDrinkName;
    @BindView(R.id.itemDrinkSize)
    TextView itemDrinkSize;
    @BindView(R.id.itemDrinkAddDrink)
    ImageView itemDrinkAddDrink;
    @BindView(R.id.itemDrinkPrice)
    TextView itemDrinkPrice;
    @BindView(R.id.itemDrinkNumberOfDrinks)
    TextView itemDrinkNumberOfDrinks;
    @BindView(R.id.itemDrinkRemoveDrink)
    ImageView imageViewRemoveDrink;

    private Drink drink;
    private OnDrinkClickListener onDrinkClickListener;

    public DrinkViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setOnDrinkClickListener(OnDrinkClickListener onDrinkClickListener) {
        this.onDrinkClickListener = onDrinkClickListener;
    }

    public void setItemDrinkNumberOfDrinks(int numberOfDrinks) {
        itemDrinkNumberOfDrinks.setText(String.valueOf(numberOfDrinks));
    }

    public void setDrink(Drink drink) {
        this.drink = drink;

        StringBuilder price = new StringBuilder();
        StringBuilder size = new StringBuilder();

        if (this.drink != null) {
            itemDrinkName.setText(this.drink.getName());
            size.append(this.drink.getSize()).append(" l");
            itemDrinkSize.setText(size.toString());
            price.append(this.drink.getPrice()).append(" Kn");
            itemDrinkPrice.setText(price.toString());
        }
    }

    @OnClick(R.id.itemDrinkAddDrink)
    public void addDrinkToOrder() {
        onDrinkClickListener.onAddDrinkClick(drink.getId());
    }

    @OnClick(R.id.itemDrinkRemoveDrink)
    public void removeDrinkFromOrder() {
        onDrinkClickListener.onRemoveDrinkClick(drink.getId());
    }

}
