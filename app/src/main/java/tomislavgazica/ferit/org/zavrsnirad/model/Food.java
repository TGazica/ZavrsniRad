package tomislavgazica.ferit.org.zavrsnirad.model;

import java.util.UUID;

public class Food {

    private String id;
    private String groupId;
    private String name;
    private String description;
    private String sizeId;
    private String imageUrl;
    private double price;
    private String categoryId;

    public  Food(){

    }

    public Food(String name, String description, String imageUrl, double price, String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Food(String groupId, String name, String description, String sizeId, String imageUrl, double price, String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.sizeId = sizeId;
        this.imageUrl = imageUrl;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Food(String id, String groupId, String name, String description, String sizeId, String imageUrl, double price, String categoryId) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.sizeId = sizeId;
        this.imageUrl = imageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
