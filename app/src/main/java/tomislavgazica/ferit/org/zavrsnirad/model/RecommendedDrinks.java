package tomislavgazica.ferit.org.zavrsnirad.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class RecommendedDrinks implements Parcelable {

    private String id;
    private String foodId;
    private String drinkId;

    public RecommendedDrinks() {
    }

    public RecommendedDrinks(String foodId, String drinkId) {
        this.id = UUID.randomUUID().toString();
        this.foodId = foodId;
        this.drinkId = drinkId;
    }

    public RecommendedDrinks(String id, String foodId, String drinkId) {
        this.id = id;
        this.foodId = foodId;
        this.drinkId = drinkId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    protected RecommendedDrinks(Parcel in) {
        id = in.readString();
        foodId = in.readString();
        drinkId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(foodId);
        dest.writeString(drinkId);
    }

    @SuppressWarnings("unused")
    public static final Creator<RecommendedDrinks> CREATOR = new Creator<RecommendedDrinks>() {
        @Override
        public RecommendedDrinks createFromParcel(Parcel in) {
            return new RecommendedDrinks(in);
        }

        @Override
        public RecommendedDrinks[] newArray(int size) {
            return new RecommendedDrinks[size];
        }
    };
}