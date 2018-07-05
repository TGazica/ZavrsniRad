package tomislavgazica.ferit.org.zavrsnirad.ui.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.viewHolder.OrderFoodViewHolder;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodViewHolder> {

    private List<Food> foods = new ArrayList<>();
    private Order order;
    private List<ItemSize> itemSizes = new ArrayList<>();
    private List<Food> foodsInOrder = new ArrayList<>();
    private List<String> orderedItems = new ArrayList<>();
    private OnOrderItemClickListener listener;

    public OrderFoodAdapter(OnOrderItemClickListener listener){
        this.listener = listener;
    }

    public void setFoods(List<Food> foods) {
        this.foods.clear();
        this.foods.addAll(foods);
        notifyDataSetChanged();
    }

    public void setOrder(Order order) {
        this.order = order;
        foodsInOrder.clear();
        orderedItems.clear();

        if (this.order != null) {
            if (this.order.getOrderedItemsIds() != null) {
                if (!this.order.getOrderedItemsIds().isEmpty()) {
                    for (int i = 0; i < this.order.getOrderedItemsIds().size(); i++) {
                        boolean isAdded = false;
                        for (int j = 0; j < orderedItems.size(); j++) {
                            if (this.order.getOrderedItemsIds().get(i).equals(orderedItems.get(j))) {
                                isAdded = true;
                            }
                        }
                        if (!isAdded) {
                            orderedItems.add(this.order.getOrderedItemsIds().get(i));
                        }
                    }
                }
            }
        }

        if (orderedItems != null){
            if (!orderedItems.isEmpty()){
                for (int i = 0; i < foods.size(); i++){
                    for (int j = 0; j <  orderedItems.size(); j++){
                        if (foods.get(i).getId().equals(orderedItems.get(j))){
                            foodsInOrder.add(foods.get(i));
                        }
                    }
                }
            }
        }

        notifyDataSetChanged();
    }

    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes.clear();
        this.itemSizes.addAll(itemSizes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderFoodViewHolder holder, int position) {
        int numOfItems = 0;
        Food food = foodsInOrder.get(position);
        holder.setOnOrderClickListener(listener);
        holder.setFood(food);

        if (order != null){
            if (order.getOrderedItemsIds() != null){
                if (!order.getOrderedItemsIds().isEmpty()){
                    for (int i = 0; i< order.getOrderedItemsIds().size(); i++){
                        if (food.getId().equals(order.getOrderedItemsIds().get(i))){
                            numOfItems++;
                        }
                    }
                    holder.setNumberOfItem(numOfItems);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return foodsInOrder.size();
    }
}
