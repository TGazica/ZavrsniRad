package tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.presentation.FoodPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter.FoodListAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail.ItemDetailFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnItemDetailClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.MainActionListener;

public class FoodFragment extends Fragment implements FoodContract.View, OnFoodGridClickListener, OnItemDetailClickListener {

    @BindView(R.id.itemsList)
    RecyclerView itemsList;

    private List<Drink> drinks;
    private List<Food> foods;
    private List<RecommendedDrinks> recommendedDrinks;
    private List<Category> categories;
    private List<ItemSize> itemSizes;
    private FoodListAdapter foodListAdapter;
    private FoodContract.Presenter presenter;
    private ItemDetailFragment itemDetailFragment;
    private MainActionListener.Main actionListener;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_items, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drinks = new ArrayList<>();
        foods = new ArrayList<>();
        recommendedDrinks = new ArrayList<>();
        categories = new ArrayList<>();
        itemSizes = new ArrayList<>();

        //Postavljanje tipa liste za prikaz

        itemsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Postavljanje adaptera

        foodListAdapter = new FoodListAdapter(this);
        foodListAdapter.setContext(getContext());
        foodListAdapter.setFoods(foods);
        foodListAdapter.setCategories(categories);

        //Postavljanje preentera

        presenter = new FoodPresenter();
        presenter.setView(this);

        //Postavljanje adaptera na pogled

        itemsList.setAdapter(foodListAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    public void onGridItemClickListener(Food food) {
        Toast.makeText(getContext(), food.getName() + "\n" + food.getGroupId(), Toast.LENGTH_SHORT).show();
        FragmentManager fm = getChildFragmentManager();
        itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setCategories(categories);
        itemDetailFragment.setDrinks(drinks);
        itemDetailFragment.setFood(food, foods);
        itemDetailFragment.setRecommendedDrinks(recommendedDrinks);
        itemDetailFragment.setSizes(itemSizes);
        itemDetailFragment.setListener(this);
        itemDetailFragment.setActionListener(actionListener);
        itemDetailFragment.show(fm, "itemDetail");
    }

    public void setActionListener(MainActionListener.Main actionListener) {
        this.actionListener = actionListener;
    }


    @Override
    public void setFoods(List<Food> foods) {
        this.foods.clear();
        this.foods.addAll(foods);
        foodListAdapter.setFoods(this.foods);
    }

    @Override
    public void setDrinks(List<Drink> drinks) {
        this.drinks.clear();
        this.drinks.addAll(drinks);
    }

    @Override
    public void setRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks) {
        this.recommendedDrinks.clear();
        this.recommendedDrinks.addAll(recommendedDrinks);
    }

    @Override
    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes.clear();
        this.itemSizes.addAll(itemSizes);
    }

    @Override
    public void setOrder(Order order) {

    }

    @Override
    public void setCategories(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        foodListAdapter.setCategories(this.categories);
    }

}
