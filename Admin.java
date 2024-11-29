import java.util.*;

public class Admin {
    private List<Product> products;
    private static final String ADMIN_PASSWORD = "abc123admin";

    public Admin(List<Product> products) {
        this.products = products;
    }

    public boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    public void addProduct(int id, String name, double price, String category) {
        products.add(new BasicProduct(id, name, price, category));
        System.out.println("Product \"" + name + "\" in category \"" + category + "\" added.");
    }

    public void addDiscountedProduct(int id, String name, double price, double discount, String category) {
        products.add(new DiscountedProduct(new BasicProduct(id, name, price, category), discount));
        System.out.println("Discounted Product \"" + name + "\" in category \"" + category + "\" added.");
    }
    public void editProduct(int id, String newName, double newPrice) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.name = newName;
                product.price = newPrice;
                System.out.println("Product with ID " + id + " updated.");
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found.");
    }



    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            System.out.println("Product \"" + products.get(index).getDescription() + "\" removed.");
            products.remove(index);
        } else {
            System.out.println("Invalid.");
        }
    }


    public void showProducts() {
        System.out.println("List of Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getDescription() + " - " + products.get(i).getPrice() + " $");
        }
    }
}