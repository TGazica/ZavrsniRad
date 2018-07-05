package tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter.FoodGridAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;

public class FoodListViewHolder extends RecyclerView.ViewHolder {

    //Povezivanje pogleda radi mogućnosti interakcije

    @BindView(R.id.itemFoodCategoryName)
    TextView itemFoodCategoryName;
    @BindView(R.id.showFoodItemsGridView)
    RecyclerView showFoodItemsGridView;

    private List<Food> foods = new ArrayList<>();
    private Category category;
    private FoodGridAdapter foodGridAdapter;

    public void setCategory(Category category, List<Food> foods) {
        this.category = category;

        if (this.category != null){
            itemFoodCategoryName.setText(this.category.getCategoryName());
            this.foods.clear();
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getCategoryId().equals(this.category.getId())) {
                    this.foods.add(foods.get(i));
                }
            }
            foodGridAdapter.setFoods(this.foods);
        }
        itemFoodCategoryName.setText(this.category.getCategoryName());

        Log.v("numberOfFoods", this.category.getCategoryName() + ": " + this.foods.size());

    }

    //Postavljanje pogleda

    public FoodListViewHolder(View itemView, Context context, OnFoodGridClickListener onFoodGridClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        foodGridAdapter = new FoodGridAdapter(onFoodGridClickListener);
        showFoodItemsGridView.setLayoutManager(new GridLayoutManager(context, numOfItems(context)));
        showFoodItemsGridView.setAdapter(foodGridAdapter);
        foodGridAdapter.setFoods(foods);
    }

    private int numOfItems(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) ((dpWidth - 390) / 150);
        return noOfColumns;
    }
}
