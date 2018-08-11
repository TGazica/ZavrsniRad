package tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodSizesClickListener;

public class FoodSizesViewHolder extends RecyclerView.ViewHolder {

    //single item
    TextView itemDetailSingleSizePrice;
    ImageView itemDetailFoodRemoveItemSingle;
    TextView itemDetailFoodItemSingleNumber;
    ImageView itemDetailFoodAddItemSingle;

    //multiple items

    TextView itemDetailFirstSizeName;
    TextView itemDetailFirstSizePrice;
    ImageView itemDetailFoodRemoveItemFirstSize;
    TextView itemDetailFoodItemFirstSizeNumber;
    ImageView itemDetailFoodAddItemFirstSize;

    private boolean isSingle;
    private String size;
    private Food food;
    private OnFoodSizesClickListener listener;

    public void setFood(Food food) {
        this.food = food;
        initBindItems();
    }

    public void setSize(String size){
        this.size = size;
    }

    public FoodSizesViewHolder(View itemView, boolean isSingle) {
        super(itemView);
        this.isSingle = isSingle;
        ButterKnife.bind(this, itemView);
    }

    public void setNumOfItems(int numOfItems){

        if (isSingle){
            itemDetailFoodItemSingleNumber.setText(Integer.toString(numOfItems));
        }else {
            itemDetailFoodItemFirstSizeNumber.setText(Integer.toString(numOfItems));
        }

    }

    public void setListener(OnFoodSizesClickListener listener) {
        this.listener = listener;
    }

    private void initBindItems() {
        if (isSingle) {
            itemDetailSingleSizePrice = itemView.findViewById(R.id.itemDetailSingleSizePrice);
            itemDetailFoodRemoveItemSingle = itemView.findViewById(R.id.itemDetailFoodRemoveItemSingle);
            itemDetailFoodItemSingleNumber = itemView.findViewById(R.id.itemDetailFoodItemSingleNumber);
            itemDetailFoodAddItemSingle = itemView.findViewById(R.id.itemDetailFoodAddItemSingle);

            itemDetailSingleSizePrice.setText(Double.toString(food.getPrice()));
        } else {
            itemDetailFirstSizeName = itemView.findViewById(R.id.itemDetailFirstSizeName);
            itemDetailFirstSizePrice = itemView.findViewById(R.id.itemDetailFirstSizePrice);
            itemDetailFoodRemoveItemFirstSize = itemView.findViewById(R.id.itemDetailFoodRemoveItemFirstSize);
            itemDetailFoodItemFirstSizeNumber = itemView.findViewById(R.id.itemDetailFoodItemFirstSizeNumber);
            itemDetailFoodAddItemFirstSize = itemView.findViewById(R.id.itemDetailFoodAddItemFirstSize);

            itemDetailFirstSizeName.setText(size);
            itemDetailFirstSizePrice.setText(Double.toString(food.getPrice()));
        }
    }

    @Optional
    @OnClick(R.id.itemDetailFoodAddItemSingle)
    public void addItemToOrderSingle(){
        listener.addItemToOrder(food);
    }

    @Optional
    @OnClick(R.id.itemDetailFoodRemoveItemSingle)
    public void removeFromOrderSingle(){
        listener.removeItemFromOrder(food);
    }

    @Optional
    @OnClick(R.id.itemDetailFoodAddItemFirstSize)
    public void addItemToOrder(){
        listener.addItemToOrder(food);
    }

    @Optional
    @OnClick(R.id.itemDetailFoodRemoveItemFirstSize)
    public void removeFromOrder(){
        listener.removeItemFromOrder(food);
    }

}
