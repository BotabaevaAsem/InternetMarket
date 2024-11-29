import java.util.*;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
        System.out.println(product.getDescription() + " added to Cart.");
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            int count = products.get(product);
            if (count > 1) {
                products.put(product, count - 1);
            } else {
                products.remove(product);
            }
            System.out.println(product.getDescription() + " removed from Cart.");
        } else {
            System.out.println("Product not in cart.");
        }
    }

    public void showCart() {
        System.out.println("Cart:");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            System.out.println(entry.getKey().getDescription() + " - " + entry.getKey().getPrice() + " $ x" + entry.getValue());
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    public List<Product> getProducts() {
        return new ArrayList<>(products.keySet());
    }



    public void clearCart() {
        products.clear();
    }


}



