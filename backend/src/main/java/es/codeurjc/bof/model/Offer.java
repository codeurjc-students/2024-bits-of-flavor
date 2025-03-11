package es.codeurjc.bof.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ManyToOne
    private Product product;

    private String name;
    private LocalDate expDate;
    private int discount;
    private double newPrice;

    public Offer() {
    }

    public Offer(Product product, String name, LocalDate expDate, int discount, double newPrice) {
        this.product = product;
        this.name = name;
        this.expDate = expDate;
        this.discount = discount;
        this.newPrice = newPrice;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getExpDate() {
        return expDate;
    }
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public double getNewPrice() {
        return newPrice;
    }
    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public boolean isActive() {
        return this.expDate.isAfter(LocalDate.now());
    }
}
