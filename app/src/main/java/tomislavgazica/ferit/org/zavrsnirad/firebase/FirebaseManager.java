package tomislavgazica.ferit.org.zavrsnirad.firebase;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.interfaces.FirebaseCallbacks;
import tomislavgazica.ferit.org.zavrsnirad.model.Category;
import tomislavgazica.ferit.org.zavrsnirad.model.Drink;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.model.ItemSize;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;
import tomislavgazica.ferit.org.zavrsnirad.model.RecommendedDrinks;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;


public class FirebaseManager implements ValueEventListener {

    private static FirebaseManager instance;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseUploadReference;
    private StorageReference storageReference;
    private StorageReference imageReference;
    private FirebaseCallbacks firebaseCallbacks;
    private FirebaseUser user;
    private String imageId;
    private String userId;

    //Dobivanje instance baze i postavljanje referenci na dio baze

    private FirebaseManager() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        storageReference = FirebaseStorage.getInstance().getReference().child(Constants.FIREBASE_IMAGES).child(userId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId);
        databaseUploadReference = FirebaseDatabase.getInstance().getReference();
        imageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://zavrsnirad-900ea.appspot.com");
    }

    public static FirebaseManager getInstance() {
        if (instance == null) {
            instance = new FirebaseManager();
        }
        return instance;
    }

    public void setListener() {
        databaseReference.addValueEventListener(this);
    }

    public void removeListener() {
        databaseReference.removeEventListener(this);
    }

    public void setFirebaseCallbacks(FirebaseCallbacks firebaseCallbacks) {
        this.firebaseCallbacks = firebaseCallbacks;
    }

    //Dohvaćanje podataka i prosljeđivanje u response model kroz presenter

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        firebaseCallbacks.onNewItem(dataSnapshot);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public Uri getImage(String imageId) {
        final Uri[] url = new Uri[1];

        imageReference.child(Constants.FIREBASE_IMAGES + "/" + userId + "/" + imageId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                url[0] = uri;
            }
        });

        return url[0];
    }

    public void uploadFood(Food food, Uri filePath) {
        uploadImage(filePath);
        food.setImageUrl(imageId);
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_FOOD).child(food.getId());
        databaseUploadReference.setValue(food);
    }

    public void uploadFood(Food food) {
        food.setImageUrl(imageId);
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_FOOD).child(food.getId());
        databaseUploadReference.setValue(food);
    }

    private void uploadImage(Uri filePath) {
        if (filePath != null) {
            imageId = UUID.randomUUID().toString();
            storageReference.child(imageId).putFile(filePath);
        }
    }

    public void uploadCategory(Category category) {
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_CATEGORY).child(category.getId());
        databaseUploadReference.setValue(category);
    }

    public void uploadDrink(Drink drink) {

        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_DRINK).child(drink.getId());
        databaseUploadReference.setValue(drink);

    }

    public void uploadItemSize(ItemSize itemSize) {

        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_SIZE).child(itemSize.getId());
        databaseUploadReference.setValue(itemSize);
    }

    public void uploadTable(Table table) {
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_TABLE).child(table.getId());
        databaseUploadReference.setValue(table);
    }

    public void uploadRecommendedDrinks(RecommendedDrinks recommendedDrinks) {
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_RECOMMENDED).child(recommendedDrinks.getId());
        databaseUploadReference.setValue(recommendedDrinks);
    }

    public void uploadOrder(Order order) {
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_ORDER).child(order.getId());
        databaseUploadReference.setValue(order);
    }

}
