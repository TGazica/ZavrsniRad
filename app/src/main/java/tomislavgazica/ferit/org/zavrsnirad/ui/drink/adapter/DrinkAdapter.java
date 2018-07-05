package tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter;

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
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.viewHolder.DrinkViewHolder;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder>{

    private List<Drink> drinks = new ArrayList<>();
    private OnDrinkClickListener onDrinkClickListener;
    private Order order;

    public void setOrder(Order order){
        this.order = order;
        notifyDataSetChanged();
    }

    public void updateDrinkList(List<Drink> drinks){
        this.drinks.clear();
        this.drinks.addAll(drinks);
        notifyDataSetChanged();
    }

    public void setOnDrinkClickListener(OnDrinkClickListener onDrinkClickListener){
        this.onDrinkClickListener = onDrinkClickListener;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.setDrink(drink);
        holder.setOnDrinkClickListener(onDrinkClickListener);
        int numOfItem = 0;
        if (order != null) {
            for (int i = 0; i < order.getOrderedItemsIds().size(); i++) {
                if (i == 0) {
                    numOfItem = 0;
                }
                if (order.getOrderedItemsIds().get(i).equals(drink.getId())) {
                    numOfItem++;
                }
            }
            holder.setItemDrinkNumberOfDrinks(numOfItem);
        }
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
