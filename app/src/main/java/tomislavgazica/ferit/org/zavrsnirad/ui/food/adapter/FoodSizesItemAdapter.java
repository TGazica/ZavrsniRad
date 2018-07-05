package tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodSizesClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder.FoodSizesViewHolder;

public class FoodSizesItemAdapter extends RecyclerView.Adapter<FoodSizesViewHolder> {

    private OnFoodSizesClickListener listener;
    private Food food;
    private List<Food> foods = new ArrayList<>();
    private List<ItemSize> itemSizes = new ArrayList<>();
    private Order order;
    private boolean isSingle;

    public FoodSizesItemAdapter(Food food, OnFoodSizesClickListener listener) {
        this.listener = listener;
        this.food = food;
        isSingle = true;
        notifyDataSetChanged();
    }

    public FoodSizesItemAdapter(List<Food> foods, OnFoodSizesClickListener listener) {
        this.listener = listener;
        this.foods.clear();
        this.foods.addAll(foods);
        isSingle = false;
        notifyDataSetChanged();
    }

    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes.clear();
        this.itemSizes.addAll(itemSizes);
        notifyDataSetChanged();
    }

    public void setOrder(Order order) {
        this.order = order;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodSizesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isSingle) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_food_single_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_food_multiple_sizes_item, parent, false);
        }
        return new FoodSizesViewHolder(view, isSingle);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodSizesViewHolder holder, int position) {
        if (isSingle) {
            Food food = this.food;
            holder.setFood(food);
            holder.setListener(listener);
            int numOfItem = 0;
            if (order != null) {
                for (int i = 0; i < order.getOrderedItemsIds().size(); i++) {
                    if (i == 0) {
                        numOfItem = 0;
                    }
                    if (order.getOrderedItemsIds().get(i).equals(food.getId())) {
                        numOfItem++;
                    }
                }
                holder.setNumOfItems(numOfItem);
            }

        } else {
            Food food = foods.get(position);
            String size = "";

            for (int i = 0; i < itemSizes.size(); i++) {
                if (itemSizes.get(i).getId().equals(food.getSizeId())) {
                    size = itemSizes.get(i).getSize();
                    break;
                }
            }
            holder.setSize(size);
            holder.setFood(food);
            holder.setListener(listener);

            int numOfItem = 0;
            if (order != null) {
                for (int i = 0; i < order.getOrderedItemsIds().size(); i++) {
                    if (i == 0) {
                        numOfItem = 0;
                    }
                    if (order.getOrderedItemsIds().get(i).equals(food.getId())) {
                        numOfItem++;
                    }
                }
                holder.setNumOfItems(numOfItem);
            }
        }
    }

    @Override
    public int getItemCount() {

        int i;

        if (isSingle) {
            i = 1;
        } else {
            i = foods.size();
        }

        return i;
    }
}
