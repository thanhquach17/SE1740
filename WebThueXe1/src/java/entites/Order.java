/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author ADMIN
 */
import java.sql.Timestamp;

public class Order {

    private int id;
    private int accountId;
    private int productId;
    private double price;
    private Timestamp createdAt;
    private int status;
    private String customerFullName;
    private String productName;
    private Timestamp start;
    private Timestamp end;

    public Order() {
    }

  public Order(int id, String customerFullName, String productName, double price, Timestamp createdAt, int status, Timestamp start, Timestamp end) {
    this.id = id;
    this.customerFullName = customerFullName;
    this.productName = productName;
    this.price = price;
    this.createdAt = createdAt;
    this.status = status;
    this.start = start;
    this.end = end;
}

public Order(int id, int accountId, int productId, double price, Timestamp createdAt, int status, Timestamp start, Timestamp end) {
    this.id = id;
    this.accountId = accountId;
    this.productId = productId;
    this.price = price;
    this.createdAt = createdAt;
    this.status = status;
    this.start = start;
    this.end = end;
}

public Order(int accountId, int productId, double price, Timestamp createdAt, int status, Timestamp start, Timestamp end) {
    this.accountId = accountId;
    this.productId = productId;
    this.price = price;
    this.createdAt = createdAt;
    this.status = status;
    this.start = start;
    this.end = end;
}

public Order(int accountId, int productId, double price, Timestamp createdAt, Timestamp start, Timestamp end) {
    this.accountId = accountId;
    this.productId = productId;
    this.price = price;
    this.createdAt = createdAt;
    this.start = start;
    this.end = end;
}

public Order(int accountId, int productId, double price) {
    this.accountId = accountId;
    this.productId = productId;
    this.price = price;
}


    // Getters and setters
    public int getId() {
        return id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }
    public Timestamp getStart() {
    return start;
}

public void setStart(Timestamp start) {
    this.start = start;
}

public Timestamp getEnd() {
    return end;
}

public void setEnd(Timestamp end) {
    this.end = end;
}

    public void setStatus(int status) {
        this.status = status;
    }

    // toString() method
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", accountId=" + accountId + ", productId=" + productId  + ", price=" + price + ", createdAt=" + createdAt + ", status=" + status + ", customerFullName=" + customerFullName + ", productName=" + productName + '}';
    }

}
