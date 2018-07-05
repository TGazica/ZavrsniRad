package tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import tomislavgazica.ferit.org.zavrsnirad.presentation.OrderPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.MainActionListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.adapter.OrderDrinkAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.adapter.OrderFoodAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderFragment extends Fragment implements OrderContract.View, OnOrderItemClickListener, MainActionListener.Order{

    Unbinder unbinder;
    @BindView(R.id.orderFoodHolder)
    RecyclerView orderFoodHolder;
    @BindView(R.id.orderDrinkHolder)
    RecyclerView orderDrinkHolder;
    @BindView(R.id.orderCancelOrder)
    Button orderCancelOrder;
    @BindView(R.id.orderAddOrder)
    Button orderAddOrder;

    private MainActionListener.Main actionListener;

    private OrderContract.Presenter presenter;
    private OrderFoodAdapter orderFoodAdapter;
    private OrderDrinkAdapter orderDrinkAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.orderAddOrder)
    public void addOrder(){
        presenter.uploadOrder();
        actionListener.OnNewItem();
    }

    @OnClick(R.id.orderCancelOrder)
    public void cancelOrder(){
        presenter.removeAllItemsFromOrder();
        actionListener.OnNewItem();
    }


    public void setActionListener(MainActionListener.Main actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        orderDrinkAdapter = new OrderDrinkAdapter(this);
        orderFoodAdapter = new OrderFoodAdapter(this);

        initUi();

        presenter = new OrderPresenter();
        presenter.setView(this);


    }

    private void initUi() {

        orderFoodHolder.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        orderDrinkHolder.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        orderFoodHolder.setAdapter(orderFoodAdapter);
        orderDrinkHolder.setAdapter(orderDrinkAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void addFoodToOrder(Food food) {
        presenter.addItemToOrder(food.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void removeFoodFromOrder(Food food) {
        presenter.removeItemFromOrder(food.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void removeFoodFromOrderAll(Food food) {
        presenter.removeAllItemIdsFromOrder(food.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void addDrinkToOrder(Drink drink) {
        presenter.addItemToOrder(drink.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void removeDrinkFromOrder(Drink drink) {
        presenter.removeItemFromOrder(drink.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void removeDrinkFromOrderAll(Drink drink) {
        presenter.removeAllItemIdsFromOrder(drink.getId());
        presenter.getOrderData();
        actionListener.OnNewItem();
    }

    @Override
    public void setFoods(List<Food> foods) {
        orderFoodAdapter.setFoods(foods);
    }

    @Override
    public void setDrinks(List<Drink> drinks) {
        orderDrinkAdapter.setDrinks(drinks);
    }

    @Override
    public void setRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks) {

    }

    @Override
    public void setItemSizes(List<ItemSize> itemSizes) {
        orderFoodAdapter.setItemSizes(itemSizes);
    }

    @Override
    public void setOrder(Order order) {
        orderFoodAdapter.setOrder(order);
        orderDrinkAdapter.setOrder(order);
    }

    @Override
    public void setCategories(List<Category> categories) {

    }

    @Override
    public void OnNewItem() {
        presenter.getOrderData();
    }
}
