package tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.viewHolder.DrinkListViewHolder;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListViewHolder> {

    private OnDrinkClickListener onDrinkClickListener;
    private Order order;
    private List<Category> categories = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private Activity activity;

    public void setOnDrinkClickListener(OnDrinkClickListener onDrinkClickListener) {
        this.onDrinkClickListener = onDrinkClickListener;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setOrders(Order order) {
        this.order = order;
        notifyDataSetChanged();
    }

    public void setCategories(List<Category> categories) {
        this.categories.clear();

        for (int i = 0; i< categories.size(); i++){
            if (categories.get(i).getCategoryType().equals(Constants.FIREBASE_DRINK)){
                this.categories.add(categories.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks.clear();
        this.drinks.addAll(drinks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrinkListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink_category, parent, false);
        return new DrinkListViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkListViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.setOnDrinkClickListener(onDrinkClickListener);
        holder.setOrder(order);
        holder.setCategory(category, drinks);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
