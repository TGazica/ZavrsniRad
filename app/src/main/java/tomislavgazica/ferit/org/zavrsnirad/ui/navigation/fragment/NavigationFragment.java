package tomislavgazica.ferit.org.zavrsnirad.ui.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.ui.navigation.listener.NavigationOnClickListener;

public class NavigationFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.navigationFoodImage)
    ImageView navigationFoodImage;
    @BindView(R.id.navigationFood)
    TextView navigationFood;
    @BindView(R.id.navigationDrinkImage)
    ImageView navigationDrinkImage;
    @BindView(R.id.navigationDrink)
    TextView navigationDrink;
    @BindView(R.id.navigationLanguageImage)
    LinearLayout navigationLanguageImage;

    private NavigationOnClickListener listener;

    public void setListener(NavigationOnClickListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.navigationFood, R.id.navigationFoodImage})
    public void onFoodClick() {
        listener.onNavigationFoodClick();
    }

    @OnClick({R.id.navigationDrink, R.id.navigationDrinkImage})
    public void onDrinkClick() {
        listener.onNavigationDrinkClick();
    }

    @OnClick(R.id.navigationLanguageImage)
    public void onLanguageClick() {
        listener.onNavigationLanguageClick();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
