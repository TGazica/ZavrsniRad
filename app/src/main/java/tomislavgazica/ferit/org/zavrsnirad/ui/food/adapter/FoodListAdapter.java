package tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter;

import android.app.Activity;
import android.content.Context;
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
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder.FoodListViewHolder;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListViewHolder> {

    private OnFoodGridClickListener onFoodGridClickListener;
    private List<Category> categories = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private Context context;

    public void setContext(Context context){
        this.context = context;
    }

    public void setCategories(List<Category> categories) {
        this.categories.clear();

        boolean isThereFood;

        for (int i = 0; i< categories.size(); i++){
            isThereFood = false;
            if (categories.get(i).getCategoryType().equals(Constants.FIREBASE_FOOD)){
                for(int j = 0; j < foods.size(); j++){
                    if (foods.get(j).getCategoryId().equals(categories.get(i).getId())){
                        isThereFood = true;
                    }
                }
                if (isThereFood) {
                    this.categories.add(categories.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setFoods(List<Food> foods) {
        this.foods.clear();
        this.foods.addAll(foods);
        notifyDataSetChanged();
    }

    public FoodListAdapter(OnFoodGridClickListener onFoodGridClickListener){
        this.onFoodGridClickListener = onFoodGridClickListener;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_category, parent, false);
        return new FoodListViewHolder(view, context, onFoodGridClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.setCategory(category, foods);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
