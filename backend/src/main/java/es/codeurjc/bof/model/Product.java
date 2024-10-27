package es.codeurjc.bof.model;

import java.sql.Blob;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Ticket> tickets;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String info;
    
    private float weight;
    private float price;

    private float cal;
    private float protein;
    private float fat;
    private float carbo;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    public Product() { }

    public Product(String name, String info, float weight, float price, float cal, float protein, float carbo, float fat) {
        this.name = name;
        this.info = info;
        this.weight = weight;
        this.price = price;
        this.cal = cal;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }


}