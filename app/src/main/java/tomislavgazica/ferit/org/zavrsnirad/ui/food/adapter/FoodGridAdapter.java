package tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder.FoodGridViewHolder;

public class FoodGridAdapter extends RecyclerView.Adapter<FoodGridViewHolder> {

    @BindView(R.id.itemFoodImage)
    ImageView itemFoodImage;
    @BindView(R.id.itemFoodNameHolder)
    ImageView itemFoodNameHolder;
    @BindView(R.id.itemFoodName)
    TextView itemFoodName;

    private List<Food> foods = new ArrayList<>();
    private OnFoodGridClickListener onFoodGridClickListener;

    public FoodGridAdapter(OnFoodGridClickListener onFoodGridClickListener) {
        this.onFoodGridClickListener = onFoodGridClickListener;
    }

    public void setFoods(List<Food> foods) {
        this.foods.clear();
        boolean isAdded;

        for (int i = 0; i < foods.size(); i++) {

            isAdded = false;

            if (foods.get(i).getGroupId() != null) {

                if (!foods.get(i).getGroupId().isEmpty() || !foods.get(i).getGroupId().equals("")) {

                    for (int j = 0; j < this.foods.size(); j++) {

                        if (this.foods.get(j).getGroupId() != null) {

                            if (!this.foods.get(j).getGroupId().isEmpty() || !this.foods.get(j).getGroupId().equals("")) {

                                if (foods.get(i).getGroupId().equals(this.foods.get(j).getGroupId())) {
                                    isAdded = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (!isAdded) {
                this.foods.add(foods.get(i));
                isAdded = false;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodGridViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.setOnFoodGridClickListener(onFoodGridClickListener);
        holder.setFood(food);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
