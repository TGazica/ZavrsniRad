package tomislavgazica.ferit.org.zavrsnirad.ui.order.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderDrinkViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.itemName)
    TextView itemName;
    @BindView(R.id.itemSize)
    TextView itemSize;
    @BindView(R.id.itemPrice)
    TextView itemPrice;
    @BindView(R.id.itemAdd)
    ImageView itemAdd;
    @BindView(R.id.itemNumber)
    TextView itemNumber;
    @BindView(R.id.itemRemove)
    ImageView itemRemove;
    @BindView(R.id.itemRemoveAllItems)
    ImageView itemRemoveAllItems;
    private Drink drink;
    private OnOrderItemClickListener onOrderItemClickListener;

    public void setOnOrderClickListener(OnOrderItemClickListener onOrderItemClickListener) {
        this.onOrderItemClickListener = onOrderItemClickListener;
    }

    public OrderDrinkViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setDrink(Drink drink) {
        this.drink = drink;

        itemName.setText(this.drink.getName());
        itemPrice.setText(Double.toString(this.drink.getPrice()));
        itemSize.setText(Double.toString(this.drink.getSize()));

    }

    public void setNumberOfItem(int numOfItems) {
        itemNumber.setText(Integer.toString(numOfItems));
    }

    @OnClick(R.id.itemAdd)
    public void addItem() {
        onOrderItemClickListener.addDrinkToOrder(drink);
    }

    @OnClick(R.id.itemRemove)
    public void removeItem() {
        onOrderItemClickListener.removeDrinkFromOrder(drink);
    }

    @OnClick(R.id.itemRemoveAllItems)
    public void removeAllItems() {
        onOrderItemClickListener.removeDrinkFromOrderAll(drink);
    }
}
