package tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import tomislavgazica.ferit.org.zavrsnirad.App;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;
import tomislavgazica.ferit.org.zavrsnirad.presentation.MainPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.drink.fragment.DrinkFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.fragment.FoodFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.fragment.NavigationFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.listener.NavigationOnClickListener;
import tomislavgazica.ferit.org.zavrsnirad.ui.order.fragment.OrderFragment;
import tomislavgazica.ferit.org.zavrsnirad.ui.table.TablePicker;

public class MainActivity extends AppCompatActivity implements NavigationOnClickListener, MainContract.View, OnItemAddedListener.Main, OnTableListener {

    private NavigationFragment navigationFragment;
    private FoodFragment foodFragment;
    private DrinkFragment drinkFragment;
    private OrderFragment orderFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String currentMenu;
    private MainPresenter presenter;
    private TablePicker tablePicker;
    private OnFirebaseDataChangeListener changeListener;
    private OnItemAddedListener.Child orderListener;
    private OnItemAddedListener.Child drinkListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad  = App.getLang();
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.setView(this);

        initNavigationFragment();
        initOrderFragment();
    }

    private void initOrderFragment() {
        orderFragment = new OrderFragment();

        orderFragment.setOnItemAddedListener(this);
        orderListener = orderFragment;
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

        foodFragment.setOnItemAddedListener(this);

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

    @Override
    public void onNavigationLanguageClick() {
        if (App.getLang().equals(Constants.CROATIAN_LANGUAGE)) {
            App.setLang(Constants.ENGLIS_LANGUAGE);
            recreate();
        } else if (App.getLang().equals(Constants.ENGLIS_LANGUAGE)) {
            App.setLang(Constants.CROATIAN_LANGUAGE);
            recreate();
        }
    }

    private void initDrinkFragment() {
        currentMenu = Constants.FIREBASE_DRINK;
        drinkFragment = new DrinkFragment();

        drinkFragment.setOnItemAddedListener(this);
        drinkListener = drinkFragment;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, drinkFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDataUpdated() {

        if (!App.isTableSetUp()){
            if (!App.isTableSetUp()){
                presenter.getTables();
                App.setTableSetUp(!App.isTableSetUp());
            }
        }

        if (currentMenu != null){
            if (currentMenu.equals(Constants.FIREBASE_FOOD)){
                changeListener = foodFragment;
                changeListener.updateFirebaseData();

            } else if (currentMenu.equals(Constants.FIREBASE_DRINK)){
                changeListener = drinkFragment;
                changeListener.updateFirebaseData();
            }
        }

        changeListener = orderFragment;
        changeListener.updateFirebaseData();

    }

    @Override
    public void setTables(List<Table> tables) {
        fragmentManager = getSupportFragmentManager();
        tablePicker = new TablePicker();
        tablePicker.setData(tables, getApplicationContext(), this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFrameLayout, tablePicker);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onItemAddedToMenu() {

        if (currentMenu != null){
            if (currentMenu.equals(Constants.FIREBASE_DRINK)){
                drinkListener.onItemAddedToMenu();
            }
        }

        orderListener.onItemAddedToMenu();

    }

    @Override
    public void tableSet() {
        getSupportFragmentManager().beginTransaction().remove(tablePicker).commit();
    }
}
