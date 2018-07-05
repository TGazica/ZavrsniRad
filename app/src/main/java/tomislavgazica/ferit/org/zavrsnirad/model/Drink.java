package tomislavgazica.ferit.org.zavrsnirad.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Drink implements Parcelable {

    private String id;
    private String name;
    private double size;
    private double price;
    private String categoryId;

    public Drink(){

    }

    public Drink(String name, double size, double price, String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.size = size;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Drink(String id, String name, double size, double price, String categoryId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    protected Drink(Parcel in) {
        id = in.readString();
        name = in.readString();
        size = in.readDouble();
        price = in.readDouble();
        categoryId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(size);
        dest.writeDouble(price);
        dest.writeString(categoryId);
    }

    @SuppressWarnings("unused")
    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
}
