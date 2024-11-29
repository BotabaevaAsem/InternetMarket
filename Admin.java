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
    public void editProduct(int id, String newName, double newPrice, String newCategory) {
        Product productToEdit = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);

        if (productToEdit != null) {

            if (productToEdit instanceof DiscountedProduct discountedProduct) {
                Product baseProduct = new BasicProduct(id, newName, newPrice, newCategory);
                double discount = discountedProduct.getDiscount();
                products.remove(productToEdit);
                products.add(new DiscountedProduct(baseProduct, discount));
            } else {

                products.remove(productToEdit);
                products.add(new BasicProduct(id, newName, newPrice, newCategory));
            }
            System.out.println("Product with ID " + id + " updated successfully!");
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
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