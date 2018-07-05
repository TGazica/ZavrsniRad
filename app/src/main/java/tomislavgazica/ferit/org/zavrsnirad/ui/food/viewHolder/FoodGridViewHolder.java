package tomislavgazica.ferit.org.zavrsnirad.ui.food.viewHolder;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tomislavgazica.ferit.org.zavrsnirad.Constants;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.firebase.FirebaseManager;
import tomislavgazica.ferit.org.zavrsnirad.model.Food;
import tomislavgazica.ferit.org.zavrsnirad.ui.food.listeners.OnFoodGridClickListener;

public class FoodGridViewHolder extends RecyclerView.ViewHolder{


    @BindView(R.id.itemFoodImage)
    ImageView itemFoodImage;
    @BindView(R.id.itemFoodName)
    TextView itemFoodName;

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();

    private Food food;
    private OnFoodGridClickListener onFoodGridClickListener;

    public FoodGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setOnFoodGridClickListener(OnFoodGridClickListener onFoodGridClickListener) {
        this.onFoodGridClickListener = onFoodGridClickListener;
    }

    public void setFood(Food food){
        this.food = food;

        if (this.food != null){
            itemFoodName.setText(this.food.getName());
            onImageDownloaded();
        }
    }

    @OnClick({R.id.itemFoodImage, R.id.itemFoodName, R.id.itemFoodNameHolder})
    public void onItemClick(){
        onFoodGridClickListener.onGridItemClickListener(this.food);
    }

    private void onImageDownloaded(){

        storageRef.child(Constants.FIREBASE_IMAGES + "/" + user.getUid() + "/" + food.getImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.v("uri", uri.toString());
                Picasso.get().load(uri).resize(180, 180).centerCrop().into(itemFoodImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("fail", e.getLocalizedMessage());
            }
        });

    }
}
