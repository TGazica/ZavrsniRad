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
import tomislavgazica.ferit.org.zavrsnirad.presentation.OrderPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnFirebaseDataChangeListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnItemAddedListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.adapter.OrderDrinkAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.adapter.OrderFoodAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.listeners.OnOrderItemClickListener;

public class OrderFragment extends Fragment implements OrderContract.View, OnOrderItemClickListener, OnFirebaseDataChangeListener, OnItemAddedListener.Child{

    Unbinder unbinder;
    @BindView(R.id.orderFoodHolder)
    RecyclerView orderFoodHolder;
    @BindView(R.id.orderDrinkHolder)
    RecyclerView orderDrinkHolder;

    private OrderContract.Presenter presenter;
    private OrderFoodAdapter orderFoodAdapter;
    private OrderDrinkAdapter orderDrinkAdapter;
    private OnItemAddedListener.Main onItemAddedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void setOnItemAddedListener(OnItemAddedListener.Main onItemAddedListener){
        this.onItemAddedListener = onItemAddedListener;
    }

    @OnClick(R.id.orderAddOrder)
    public void addOrder(){
        presenter.uploadOrder();
    }

    @OnClick(R.id.orderCancelOrder)
    public void cancelOrder(){
        presenter.removeAllItemsFromOrder();
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
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
        onItemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void removeFoodFromOrder(Food food) {
        presenter.removeItemFromOrder(food.getId());
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void removeFoodFromOrderAll(Food food) {
        presenter.removeAllItemIdsFromOrder(food.getId());
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void addDrinkToOrder(Drink drink) {
        presenter.addItemToOrder(drink.getId());
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void removeDrinkFromOrder(Drink drink) {
        presenter.removeItemFromOrder(drink.getId());
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void removeDrinkFromOrderAll(Drink drink) {
        presenter.removeAllItemIdsFromOrder(drink.getId());
        presenter.getOrderData();
        onItemAddedListener.onItemAddedToMenu();
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
    public void updateFirebaseData() {
        presenter.getOrderData();
    }

    @Override
    public void onItemAddedToMenu() {
        presenter.getOrderData();
    }
}
