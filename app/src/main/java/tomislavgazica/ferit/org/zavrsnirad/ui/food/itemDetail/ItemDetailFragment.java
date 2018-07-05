package tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.presentation.ItemDetailPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter.DrinkAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter.FoodSizesItemAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodSizesClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnItemDetailClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.MainActionListener;

public class ItemDetailFragment extends DialogFragment implements ItemDetailContract.View, OnDrinkClickListener, OnFoodSizesClickListener {


    Unbinder unbinder;
    @BindView(R.id.itemDetailFoodCloseWindow)
    ImageView itemDetailFoodCloseWindow;

    @BindView(R.id.itemDetailFoodImage)
    ImageView itemDetailFoodImage;

    @BindView(R.id.itemDetailFoodName)
    TextView itemDetailFoodName;

    @BindView(R.id.itemDetailFoodDescription)
    TextView itemDetailFoodDescription;

    @BindView(R.id.itemDetailFoodConfirmChoice)
    ImageView itemDetailFoodConfirmChoice;

    @BindView(R.id.itemDetailFoodRecommendedDrinkViewHolder)
    RecyclerView itemDetailFoodRecommendedDrinkViewHolder;

    @BindView(R.id.itemDetailFoodMultipleSizes)
    RecyclerView itemDetailFoodMultipleSizes;

    private OnItemDetailClickListener listener;
    private Food food;
    private List<Food> foods = new ArrayList<>();
    private List<RecommendedDrinks> recommendedDrinks = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Drink> recDrinks = new ArrayList<>();
    private List<ItemSize> sizes = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private Order order;
    private ItemDetailContract.Presenter presenter;
    private DrinkAdapter drinkAdapter;
    private FoodSizesItemAdapter foodSizesItemAdapter;
    private boolean areThereMultipleSizes = false;
    private MainActionListener.Main actionListener;

    public void setActionListener(MainActionListener.Main actionListener) {
        this.actionListener = actionListener;
    }

    @OnClick({R.id.itemDetailFoodConfirmChoice,R.id.itemDetailFoodCloseWindow})
    public void closeDetails(){
        dismiss();
    }

    public void setListener(OnItemDetailClickListener listener) {
        this.listener = listener;
    }

    public void setFood(Food food, List<Food> foods) {
        this.food = food;

        this.foods.clear();

        if (food.getGroupId() != null) {
            if (!food.getGroupId().isEmpty()) {
                if (!food.getGroupId().equals("")) {
                    areThereMultipleSizes = true;
                    for (int i = 0; i < foods.size(); i++) {
                        if (food.getGroupId().equals(foods.get(i).getGroupId())) {
                            this.foods.add(foods.get(i));
                        }
                    }
                }
            }
        }
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks) {
        this.recommendedDrinks.addAll(recommendedDrinks);
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks.addAll(drinks);
    }

    public void setSizes(List<ItemSize> sizes) {
        this.sizes.addAll(sizes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail_food, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter = new ItemDetailPresenter();
        presenter.setView(this);

        initUi();
    }

    private void initUi() {
        itemDetailFoodName.setText(food.getName());
        itemDetailFoodDescription.setText(food.getDescription());
        presenter.getPictureUri(food);
        initRecommendedDrinks();

        if (areThereMultipleSizes){
            initMultipleItemSizes();
        }else {
            initSingleItemSize();
        }
    }

    private void initSingleItemSize() {
        itemDetailFoodMultipleSizes.setLayoutManager(new LinearLayoutManager(getContext()));
        foodSizesItemAdapter = new FoodSizesItemAdapter(food,this);
        itemDetailFoodMultipleSizes.setAdapter(foodSizesItemAdapter);
    }

    private void initMultipleItemSizes() {
        itemDetailFoodMultipleSizes.setLayoutManager(new LinearLayoutManager(getContext()));
        foodSizesItemAdapter = new FoodSizesItemAdapter(foods,this);
        itemDetailFoodMultipleSizes.setAdapter(foodSizesItemAdapter);
        foodSizesItemAdapter.setItemSizes(sizes);
    }

    private void initRecommendedDrinks() {
        itemDetailFoodRecommendedDrinkViewHolder.setLayoutManager(new LinearLayoutManager(getContext()));
        drinkAdapter = new DrinkAdapter();
        itemDetailFoodRecommendedDrinkViewHolder.setAdapter(drinkAdapter);
        presenter.getItemRecommendedDrinks(recommendedDrinks, drinks, food);
    }

    @Override
    public void setItemRecommendedDrinks(List<Drink> drinks) {
        this.recDrinks.clear();
        this.recDrinks.addAll(drinks);
        Log.v("rec drinks", recDrinks.toString());
        initRecommendedDrinksUi();
    }

    private void initRecommendedDrinksUi() {
        drinkAdapter.setOrder(order);
        drinkAdapter.setOnDrinkClickListener(this);
        drinkAdapter.updateDrinkList(recDrinks);
    }

    @Override
    public void setImage(Uri uri) {
        Picasso.get().load(uri).resize(200, 200).centerCrop().into(itemDetailFoodImage);
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
        drinkAdapter.setOrder(this.order);
        foodSizesItemAdapter.setOrder(this.order);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAddDrinkClick(String id) {
        presenter.addItemToOrder(id);
        actionListener.OnNewItem();
    }

    @Override
    public void onRemoveDrinkClick(String id) {
        presenter.removeItemFromOrder(id);
        actionListener.OnNewItem();
    }


    @Override
    public void addItemToOrder(Food food) {
        presenter.addItemToOrder(food.getId());
        actionListener.OnNewItem();
    }

    @Override
    public void removeItemFromOrder(Food food) {
        presenter.removeItemFromOrder(food.getId());
        actionListener.OnNewItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}
