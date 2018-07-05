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
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.viewHolder.OrderDrinkViewHolder;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderDrinkAdapter extends RecyclerView.Adapter<OrderDrinkViewHolder> {

    private List<Drink> drinks = new ArrayList<>();
    private List<Drink> drinksInOrder = new ArrayList<>();
    private Order order;
    private List<String> orderedItems = new ArrayList<>();
    private OnOrderItemClickListener listener;

    public OrderDrinkAdapter(OnOrderItemClickListener listener) {
        this.listener = listener;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks.clear();
        this.drinks.addAll(drinks);
        notifyDataSetChanged();
    }

    public void setOrder(Order order) {
        this.order = order;
        drinksInOrder.clear();
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
                for (int i = 0; i < drinks.size(); i++){
                    for (int j = 0; j <  orderedItems.size(); j++){
                        if (drinks.get(i).getId().equals(orderedItems.get(j))){
                            drinksInOrder.add(drinks.get(i));
                        }
                    }
                }
            }
        }


        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderDrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderDrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDrinkViewHolder holder, int position) {
        int numOfItems = 0;
        Drink drink = drinksInOrder.get(position);
        holder.setOnOrderClickListener(listener);
        holder.setDrink(drink);

        if (order != null){
            if (order.getOrderedItemsIds() != null){
                if (!order.getOrderedItemsIds().isEmpty()){
                    for (int i = 0; i< order.getOrderedItemsIds().size(); i++){
                        if (drink.getId().equals(order.getOrderedItemsIds().get(i))){
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
        return drinksInOrder.size();
    }

}
