package wallet.main.login;

/**
 * Created by sveto on 9/28/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private String amount;
    private String description;
    private List<String> purchases;
    private List<String> charges;

    public Category() {
        purchases = new ArrayList<>();
        charges = new ArrayList<>();
        this.name = "";
        this.amount = "";
        this.description = "No Description";
    }

    public Category(String amount) {
        this.amount = amount;
    }

    public Category(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Category(String name, String amount, String description) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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