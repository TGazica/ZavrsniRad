package tomislavgazica.ferit.org.zavrsnirad.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import tomislavgazica.ferit.org.zavrsnirad.Constants;


public class ItemSize implements Parcelable {

    private String id;
    private String type;
    private String size;
    private int sizeOrder;

    public ItemSize() {
    }

    public ItemSize(String size) {
        this.id = UUID.randomUUID().toString();
        this.type = Constants.FIREBASE_FOOD;
        this.size = size;
    }

    public ItemSize(String id, String size, int sizeOrder) {
        this.id = id;
        this.size = size;
        this.type = Constants.FIREBASE_FOOD;
        this.sizeOrder = sizeOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSizeOrder() {
        return sizeOrder;
    }

    public void setSizeOrder(int sizeOrder) {
        this.sizeOrder = sizeOrder;
    }

    protected ItemSize(Parcel in) {
        id = in.readString();
        type = in.readString();
        size = in.readString();
        sizeOrder = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(size);
        dest.writeInt(sizeOrder);
    }

    @SuppressWarnings("unused")
    public static final Creator<ItemSize> CREATOR = new Creator<ItemSize>() {
        @Override
        public ItemSize createFromParcel(Parcel in) {
            return new ItemSize(in);
        }

        @Override
        public ItemSize[] newArray(int size) {
            return new ItemSize[size];
        }
    };
}