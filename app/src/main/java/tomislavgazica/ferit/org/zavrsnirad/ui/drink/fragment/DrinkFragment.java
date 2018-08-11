package tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.presentation.DrinkPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter.DrinkListAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;

public class DrinkFragment extends Fragment implements OnDrinkClickListener, DrinkContract.View{

    @BindView(R.id.itemsList)
    RecyclerView itemsList;
    Unbinder unbinder;

    private DrinkListAdapter drinkListAdapter;
    private DrinkContract.Presenter presenter;

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

        drinkListAdapter = new DrinkListAdapter();
        drinkListAdapter.setActivity(getActivity());
        drinkListAdapter.setOnDrinkClickListener(this);

        presenter = new DrinkPresenter();
        presenter.setView(this);

        itemsList.setAdapter(drinkListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAddDrinkClick(String drinkId) {
        presenter.addDrinkToOrder(drinkId);
        presenter.getDrinkData();
    }

    @Override
    public void onRemoveDrinkClick(String drinkId) {
        presenter.removeDrinkFromOrder(drinkId);
        presenter.getDrinkData();
    }

    @Override
    public void setDrinks(List<Drink> drinks) {
        drinkListAdapter.setDrinks(drinks);
    }

    @Override
    public void setOrder(Order order) {
        drinkListAdapter.setOrders(order);
    }

    @Override
    public void setCategories(List<Category> categories) {
        drinkListAdapter.setCategories(categories);
    }
}
