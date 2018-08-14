package tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.presentation.ItemDetailPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.adapter.DrinkAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.listeners.OnDrinkClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.adapter.FoodSizesItemAdapter;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodSizesClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnItemAddedListener;

public class ItemDetailFragment extends DialogFragment implements ItemDetailContract.View, OnDrinkClickListener, OnFoodSizesClickListener {

    @BindView(R.id.itemDetailFoodImage)
    ImageView itemDetailFoodImage;

    @BindView(R.id.itemDetailFoodName)
    TextView itemDetailFoodName;

    @BindView(R.id.itemDetailFoodDescription)
    TextView itemDetailFoodDescription;

    @BindView(R.id.itemDetailFoodRecommendedDrinkViewHolder)
    RecyclerView itemDetailFoodRecommendedDrinkViewHolder;

    @BindView(R.id.itemDetailFoodMultipleSizes)
    RecyclerView itemDetailFoodMultipleSizes;

    Unbinder unbinder;

    private Food food;
    private ItemDetailContract.Presenter presenter;
    private DrinkAdapter drinkAdapter;
    private FoodSizesItemAdapter foodSizesItemAdapter;
    private OnItemAddedListener.Main itemAddedListener;

    @OnClick(R.id.itemDetailFoodCloseWindow)
    public void closeDetails(){
        dismiss();
        onDestroy();
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

        Bundle bundle = this.getArguments();
        if (bundle != null){
            food = (Food) bundle.getSerializable(Constants.ITEM_BUNDLE_KEY);
        }

        presenter = new ItemDetailPresenter();
        presenter.setView(this);

        initUi();
    }

    public void setItemAddedListener(OnItemAddedListener.Main itemAddedListener) {
        this.itemAddedListener = itemAddedListener;
    }

    @Override
    public void initUi() {
        itemDetailFoodName.setText(food.getName());
        itemDetailFoodDescription.setText(food.getDescription());
        presenter.getPictureUri(food);
        presenter.getFoodSizes(food);
        initRecommendedDrinks();
    }

    @Override
    public void initSingleItemSize() {
        itemDetailFoodMultipleSizes.setLayoutManager(new LinearLayoutManager(getContext()));
        foodSizesItemAdapter = new FoodSizesItemAdapter(food,this);
        itemDetailFoodMultipleSizes.setAdapter(foodSizesItemAdapter);
    }

    @Override
    public void initMultipleItemSizes(List<Food> foods, List<ItemSize> sizes) {
        itemDetailFoodMultipleSizes.setLayoutManager(new LinearLayoutManager(getContext()));
        foodSizesItemAdapter = new FoodSizesItemAdapter(foods,this);
        itemDetailFoodMultipleSizes.setAdapter(foodSizesItemAdapter);
        foodSizesItemAdapter.setItemSizes(sizes);
    }

    private void initRecommendedDrinks() {
        itemDetailFoodRecommendedDrinkViewHolder.setLayoutManager(new LinearLayoutManager(getContext()));
        drinkAdapter = new DrinkAdapter();
        itemDetailFoodRecommendedDrinkViewHolder.setAdapter(drinkAdapter);
        presenter.getItemRecommendedDrinks(food);
    }

    @Override
    public void initRecommendedDrinksUi(Order order, List<Drink> recDrinks) {
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
        drinkAdapter.setOrder(order);
        foodSizesItemAdapter.setOrder(order);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAddDrinkClick(String id) {
        presenter.addItemToOrder(id);
        itemAddedListener.onItemAddedToMenu();

    }

    @Override
    public void onRemoveDrinkClick(String id) {
        presenter.removeItemFromOrder(id);
        itemAddedListener.onItemAddedToMenu();
    }


    @Override
    public void addItemToOrder(Food food) {
        presenter.addItemToOrder(food.getId());
        itemAddedListener.onItemAddedToMenu();
    }

    @Override
    public void removeItemFromOrder(Food food) {
        presenter.removeItemFromOrder(food.getId());
        itemAddedListener.onItemAddedToMenu();
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
