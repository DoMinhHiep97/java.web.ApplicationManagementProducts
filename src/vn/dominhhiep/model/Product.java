package vn.dominhhiep.model;

public class Product {
    private int id;
    private String name;
    private double Price;
    private String information;
    private String Producer;

    public Product() {
    }

    public Product(int id, String name, double price, String information, String producer) {
        this.id = id;
        this.name = name;
        Price = price;
        this.information = information;
        Producer = producer;
    }

    public Integer getId() {
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
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Price=" + Price +
                ", information='" + information + '\'' +
                ", Producer='" + Producer + '\'' +
                '}';
    }
}
