package tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.presentation.MainPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.fragment.NavigationFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.listener.NavigationOnClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity implements NavigationOnClickListener, MainContract.View {

    private NavigationFragment navigationFragment;
    private FoodFragment foodFragment;
    private DrinkFragment drinkFragment;
    private OrderFragment orderFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String currentMenu;
    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.setView(this);

        initNavigationFragment();
        initOrderFragment();
    }

    private void initOrderFragment() {
        orderFragment = new OrderFragment();

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
        if (currentMenu == null){
            currentMenu = Constants.FIREBASE_FOOD;
            initFoodMenuFragment();
        } else if (!currentMenu.equals(Constants.FIREBASE_FOOD)) {
            currentMenu = Constants.FIREBASE_FOOD;
            initFoodMenuFragment();
        }
    }

    private void initFoodMenuFragment() {
        foodFragment = new FoodFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, foodFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onNavigationDrinkClick() {
        if (currentMenu == null) {
            currentMenu = Constants.FIREBASE_DRINK;
            initDrinkFragment();
        }else if (!currentMenu.equals(Constants.FIREBASE_DRINK)) {
            currentMenu = Constants.FIREBASE_DRINK;
            initDrinkFragment();
        }
    }

    private void initDrinkFragment() {
        currentMenu = Constants.FIREBASE_DRINK;
        drinkFragment = new DrinkFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, drinkFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDataUpdated() {
        if (currentMenu != null){
            if (currentMenu == Constants.FIREBASE_FOOD){



            } else if (currentMenu == Constants.FIREBASE_DRINK){



            }
        }



    }
}
