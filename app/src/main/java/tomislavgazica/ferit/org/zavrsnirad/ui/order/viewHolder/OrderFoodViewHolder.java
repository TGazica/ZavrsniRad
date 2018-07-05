package tomislavgazica.ferit.org.zavrsnirad.ui.order.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderFoodViewHolder extends RecyclerView.ViewHolder {

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
    private Food food;
    private OnOrderItemClickListener onOrderItemClickListener;

    public void setOnOrderClickListener(OnOrderItemClickListener onOrderItemClickListener) {
        this.onOrderItemClickListener = onOrderItemClickListener;
    }

    public OrderFoodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setFood(Food food){
        this.food = food;

        itemName.setText(this.food.getName());
        itemPrice.setText(Double.toString(this.food.getPrice()));

    }

    public void setSize(String size){
        itemSize.setText(size);
    }

    public void setNumberOfItem(int numOfItems){
        itemNumber.setText(Integer.toString(numOfItems));
    }

    @OnClick(R.id.itemAdd)
    public void addItem(){
        onOrderItemClickListener.addFoodToOrder(food);
    }

    @OnClick(R.id.itemRemove)
    public void removeItem(){
        onOrderItemClickListener.removeFoodFromOrder(food);
    }

    @OnClick(R.id.itemRemoveAllItems)
    public void removeAllItems(){
        onOrderItemClickListener.removeFoodFromOrderAll(food);
    }


}
