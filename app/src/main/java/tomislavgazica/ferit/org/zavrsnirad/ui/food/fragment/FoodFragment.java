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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.presentation.FoodPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter.FoodListAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail.ItemDetailFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnFirebaseDataChangeListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnItemAddedListener;

public class FoodFragment extends Fragment implements FoodContract.View, OnFoodGridClickListener, OnFirebaseDataChangeListener {

    @BindView(R.id.itemsList)
    RecyclerView itemsList;

    private FoodListAdapter foodListAdapter;
    private FoodContract.Presenter presenter;
    private ItemDetailFragment itemDetailFragment;
    private OnItemAddedListener.Main itemAddedListener;
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

    public void setOnItemAddedListener(OnItemAddedListener.Main itemAddedListener){
        this.itemAddedListener = itemAddedListener;
    }

    @Override
    public void onGridItemClickListener(Food food) {
        FragmentManager fm = getChildFragmentManager();
        itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setItemAddedListener(itemAddedListener);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ITEM_BUNDLE_KEY, food);
        itemDetailFragment.setArguments(bundle);
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
