package tomislavgazica.ferit.org.zavrsnirad.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Category implements Parcelable {

    private String id;
    private String categoryType;
    private String categoryName;

    public Category() {
    }

    public Category(String categoryType, String categoryName) {
        this.id = UUID.randomUUID().toString();
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }

    public Category(String id, String categoryType, String categoryName) {
        this.id = id;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    protected Category(Parcel in) {
        id = in.readString();
        categoryType = in.readString();
        categoryName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(categoryType);
        dest.writeString(categoryName);
    }

    @SuppressWarnings("unused")
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
