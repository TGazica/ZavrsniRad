package tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.fragment.NavigationFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.listener.NavigationOnClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity implements NavigationOnClickListener, MainActionListener.Main {

    @BindView(R.id.navigationFrameLayout)
    FrameLayout navigationFrameLayout;
    @BindView(R.id.menuFrameLayout)
    FrameLayout menuFrameLayout;
    @BindView(R.id.orderFrameLayout)
    FrameLayout orderFrameLayout;

    private NavigationFragment navigationFragment;
    private FoodFragment foodFragment;
    private DrinkFragment drinkFragment;
    private OrderFragment orderFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String currentMenu;
    private boolean isSafe = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initNavigationFragment();
        initOrderFragment();
    }

    private void initOrderFragment() {
        orderFragment = new OrderFragment();
        orderFragment.setActionListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.orderFrameLayout, orderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initNavigationFragment() {
        navigationFragment = new NavigationFragment();
        navigationFragment.setListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.navigationFrameLayout, navigationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onNavigationFoodClick() {
        if (!currentMenu.equals(Constants.FIREBASE_FOOD)) {
            initFoodMenuFragment();
        }
    }

    private void initFoodMenuFragment() {
        currentMenu = Constants.FIREBASE_FOOD;
        foodFragment = new FoodFragment();
        foodFragment.setActionListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, foodFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onNavigationDrinkClick() {
        if (!currentMenu.equals(Constants.FIREBASE_DRINK)) {
            initDrinkFragment();
        }
    }

    private void initDrinkFragment() {
        currentMenu = Constants.FIREBASE_DRINK;
        drinkFragment = new DrinkFragment();
        drinkFragment.setActionListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, drinkFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        isSafe = true;
    }

    @Override
    public void OnNewItem() {
        if (isSafe) {
            if (currentMenu.equals(Constants.FIREBASE_DRINK)) {
                drinkFragment.OnNewItem();
            }
        }
        orderFragment.OnNewItem();

    }
}
