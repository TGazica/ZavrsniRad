package tomislavgazica.ferit.org.zavrsnirad.ui.drink.viewHolder;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter.DrinkAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;

public class DrinkListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.itemDrinkCategoryName)
    TextView itemDrinkCategoryName;
    @BindView(R.id.itemDrinksList)
    RecyclerView itemDrinksList;

    private DrinkAdapter drinkAdapter;
    private Category category;
    private List<Drink> drinks = new ArrayList<>();

    public DrinkListViewHolder(View itemView, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemDrinksList.setLayoutManager(new LinearLayoutManager(activity));
        drinkAdapter = new DrinkAdapter();
        itemDrinksList.setAdapter(drinkAdapter);
    }

    public void setOnDrinkClickListener(OnDrinkClickListener onDrinkClickListener) {
        drinkAdapter.setOnDrinkClickListener(onDrinkClickListener);
    }

    public void setOrder(Order order) {
        drinkAdapter.setOrder(order);
    }

    public void setCategory(Category category, List<Drink> drinks) {
        this.category = category;

        if (this.category != null){
            itemDrinkCategoryName.setText(this.category.getCategoryName());
            this.drinks.clear();
            for (int i = 0; i < drinks.size(); i++) {
                if (drinks.get(i).getCategoryId().equals(this.category.getId())) {
                    this.drinks.add(drinks.get(i));
                }
            }
            drinkAdapter.updateDrinkList(this.drinks);
        }

    }

}
