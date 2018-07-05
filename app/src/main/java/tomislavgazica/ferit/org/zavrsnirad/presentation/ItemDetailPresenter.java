package tomislavgazica.ferit.org.zavrsnirad.presentation;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.itemDetail.ItemDetailContract;

public class ItemDetailPresenter implements ItemDetailContract.Presenter {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private OrderManager order;

    private ItemDetailContract.View view;

    @Override
    public void getOrder() {
        view.setOrder(order.getOrder());
    }

    @Override
    public void setView(ItemDetailContract.View view) {
        order = OrderManager.getInstance();
        this.view = view;
    }

    @Override
    public void getPictureUri(Food food) {
        storageRef.child(Constants.FIREBASE_IMAGES + "/" + user.getUid() + "/" + food.getImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.v("uri", uri.toString());
                view.setImage(uri);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("fail", e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void addItemToOrder(String id) {
        order.addItemToOrder(id);
        view.setOrder(order.getOrder());
    }

    @Override
    public void removeItemFromOrder(String id) {
        order.removeIdFromOrder(id);
        view.setOrder(order.getOrder());
    }

    @Override
    public void getItemRecommendedDrinks(List<RecommendedDrinks> recommendedDrinks, List<Drink> drink, Food food) {
        List<Drink> recDrinks = new ArrayList<>();

        for (int i = 0; i < drink.size(); i++) {
            for (int j = 0; j < recommendedDrinks.size(); j++) {
                if (recommendedDrinks.get(j).getFoodId().equals(food.getId()) && recommendedDrinks.get(j).getDrinkId().equals(drink.get(i).getId())) {
                    recDrinks.add(drink.get(i));
                }
            }
        }

        if (recDrinks != null) {
            if (!recDrinks.isEmpty()) {
                view.setItemRecommendedDrinks(recDrinks);
            }
        }
    }
}
