package tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnFirebaseDataChangeListener;

public class FoodFragment extends Fragment implements FoodContract.View, OnFoodGridClickListener, OnItemDetailClickListener, OnFirebaseDataChangeListener {

    @BindView(R.id.itemsList)
    RecyclerView itemsList;

    private FoodListAdapter foodListAdapter;
    private FoodContract.Presenter presenter;
    private ItemDetailFragment itemDetailFragment;
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

        itemsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        foodListAdapter = new FoodListAdapter(this);
        foodListAdapter.setContext(getContext());

        presenter = new FoodPresenter();
        presenter.setView(this);

        itemsList.setAdapter(foodListAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGridItemClickListener(Food food) {
        Toast.makeText(getContext(), food.getName() + "\n" + food.getGroupId(), Toast.LENGTH_SHORT).show();
        FragmentManager fm = getChildFragmentManager();
        itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setListener(this);
        itemDetailFragment.show(fm, "itemDetail");
    }

    @Override
    public void setFoods(List<Food> foods) {
        foodListAdapter.setFoods(foods);
    }

    @Override
    public void setCategories(List<Category> categories) {
        foodListAdapter.setCategories(categories);
    }

    @Override
    public void updateFirebaseData() {
        presenter.setData();
    }
}
