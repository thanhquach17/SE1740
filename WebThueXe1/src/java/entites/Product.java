/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author ADMIN
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int category;

    private String image;
    private String description;
    private int status;
    private String categoryName;

    public Product(int id, String name, double price, int category, String image, String description , int status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;

        this.image = image;
        this.description = description;
        this.status = status;
    }
    public Product(int id, String name, double price, int category, String image, String description ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;

        this.image = image;
        this.description = description;

    }

    public Product(int id, String name, double price, String category, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = category;
        this.image = image;
        this.description = description;
    }
    public Product( String name, double price,  String image, int category, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.description = description;
    }

    public Product(int id, String name, double price, int category, String image, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }
    

    public Product() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // toString() method
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", image=" + image + ", description=" + description + ", status=" + status + ", categoryName=" + categoryName + '}';
    }




}
