package es.codeurjc.bof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String name;
    private String info;
    private float weight;
    private float price;

    private float cal;
    private float protein;
    private float fat;
    private float carbo;

    public Product() { }

    public Product(String name, String info, float weight, float price, float cal, float protein, float fat, float carbo) {
        this.name = name;
        this.info = info;
        this.weight = weight;
        this.price = price;
        this.cal = cal;
        this.protein = protein;
        this.fat = fat;
        this.carbo = carbo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getCal() {
        return cal;
    }
    public void setCal(float cal) {
        this.cal = cal;
    }
    public float getProtein() {
        return protein;
    }
    public void setProtein(float protein) {
        this.protein = protein;
    }
    public float getFat() {
        return fat;
    }
    public void setFat(float fat) {
        this.fat = fat;
    }
    public float getCarbo() {
        return carbo;
    }
    public void setCarbo(float carbo) {
        this.carbo = carbo;
    }    
}