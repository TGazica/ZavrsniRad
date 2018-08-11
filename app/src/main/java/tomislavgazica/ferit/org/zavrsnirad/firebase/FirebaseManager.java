package tomislavgazica.ferit.org.zavrsnirad.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.interfaces.FirebaseCallbacks;
import tomislavgazica.ferit.org.zavrsnirad.model.Order;


public class FirebaseManager implements ValueEventListener {

    private static FirebaseManager instance;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseUploadReference;
    private FirebaseCallbacks firebaseCallbacks;
    private FirebaseUser user;
    private String userId;

    private FirebaseManager() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId);
        databaseUploadReference = FirebaseDatabase.getInstance().getReference();
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

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        firebaseCallbacks.onNewItem(dataSnapshot);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public void uploadOrder(Order order) {
        databaseUploadReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_USERS).child(userId).child(Constants.FIREBASE_ORDER).child(order.getId());
        databaseUploadReference.setValue(order);
    }

}