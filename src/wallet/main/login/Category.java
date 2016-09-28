package wallet.main.login;

/**
 * Created by sveto on 9/28/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private double amount;
    private String description;
    private List<String> purchases;
    private List<String> charges;


    public Category() {
        purchases = new ArrayList<>();
        charges = new ArrayList<>();
        this.name = "";
        this.amount = 0.0;
        this.description = "No Description";
    }
    public Category(double amount) {
        this.amount = amount;
    }
    public Category(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    public Category(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Category(String name, double amount, String description) {
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<String> purchases) {
        this.purchases = purchases;
    }

    public List<String> getCharges() {
        return charges;
    }

    public void setCharges(List<String> charges) {
        this.charges = charges;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

        public void addPurchase(String purchase) {
        purchases.add(purchase);
    }

    public void deletePurchase(String purchase){
        purchases.remove(purchases.indexOf(purchase));
    }

    public void addCharge(String charge) {
        charges.add(charge);
    }

    public void deleteCharge(String charge){
        charges.remove(charges.indexOf(charge));
    }
}