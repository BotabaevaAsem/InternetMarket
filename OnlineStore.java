import java.util.*;

public class OnlineStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationManager notificationManager = NotificationManager.getInstance();

        User user = new User("Yesdaulet");
        notificationManager.addObserver(user);

        List<Product> products = new ArrayList<>();
        products.add(new BasicProduct(1, "Smartphone", 1000, "Electronics"));
        products.add(new DiscountedProduct(new BasicProduct(2, "Laptop", 4000, "Electronics"), 10));
        products.add(new BasicProduct(3, "Headphones", 150, "Audio"));

        Admin admin = new Admin(products);

        while (true) {
            try {
                System.out.println("\nSelect:");
                System.out.println("1. Customer");
                System.out.println("2. Admin");
                System.out.println("3. Exit");
                System.out.print("Enter: ");

                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 1 -> handleCustomer(scanner, user, products);
                    case 2 -> handleAdmin(scanner, admin);
                    case 3 -> {
                        System.out.println("Exiting the system...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number!");
                scanner.nextLine();
            }
        }
    }

    private static void handleCustomer(Scanner scanner, User user, List<Product> products) {
        Cart cart = user.getCart();

        while (true) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. View Products");
                System.out.println("2. Filter Products by Category");
                System.out.println("3. Add to Cart");
                System.out.println("4. Remove from Cart");
                System.out.println("5. View Cart");
                System.out.println("6. Purchase");
                System.out.println("7. Quit");
                System.out.print("Enter: ");

                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1 -> {
                        System.out.println("======== Products ========");
                        for (Product product : products) {
                            System.out.println(product.getId() + ". " + product.getDescription() + " - " + product.getPrice() + " $");
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter category:");
                        scanner.nextLine();
                        String category = scanner.nextLine();
                        products.stream()
                                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                                .forEach(product -> System.out.println(product.getDescription() + " - " + product.getPrice() + " $"));
                    }
                    case 3 -> {
                        System.out.println("Enter the ID of the product to add to Cart:");
                        int productId = scanner.nextInt();
                        Product selectedProduct = findProductById(products, productId);
                        if (selectedProduct != null) {
                            cart.addProduct(selectedProduct);
                            System.out.println("Added to cart: " + selectedProduct.getDescription());
                        } else {
                            System.out.println("Product with ID " + productId + " not found.");
                        }
                    }
                    case 4 -> {
                        cart.showCart();
                        System.out.println("Enter the ID of the product to remove from Cart:");
                        int productId = scanner.nextInt();
                        Product productToRemove = findProductById(cart.getProducts(), productId);
                        if (productToRemove != null) {
                            cart.removeProduct(productToRemove);
                            System.out.println("Removed from cart: " + productToRemove.getDescription());
                        } else {
                            System.out.println("Product with ID " + productId + " not found in Cart!");
                        }
                    }
                    case 5 -> cart.showCart();
                    case 6 -> {
                        cart.showCart();
                        System.out.println("Enter a payment method (card/paypal):");
                        String paymentType = scanner.next();
                        try {
                            PaymentMethod paymentMethod = PaymentFactory.getPaymentMethod(paymentType);
                            paymentMethod.pay(cart.getTotalPrice());
                            cart.clearCart();
                            System.out.println("Purchase completed successfully!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 7 -> {
                        System.out.println("Returning to main menu...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again!");
                scanner.nextLine();
            }
        }
    }

    private static void handleAdmin(Scanner scanner, Admin admin) {
        System.out.print("Enter the PASSWORD: ");
        scanner.nextLine();
        String password = scanner.nextLine();
        if (admin.authenticate(password)) {
            while (true) {
                try {
                    System.out.println("\nAdmin Menu:");
                    System.out.println("1. View Products");
                    System.out.println("2. Add New Product");
                    System.out.println("3. Add Discounted Product");
                    System.out.println("4. Edit Product");
                    System.out.println("5. Delete Product");
                    System.out.println("6. Quit Admin Mode");
                    System.out.print("Enter: ");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> admin.showProducts();
                        case 2 -> {
                            System.out.println("Enter Product ID:");
                            int id = scanner.nextInt();
                            System.out.println("Enter Product Name:");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            System.out.println("Enter Product Price:");
                            double price = scanner.nextDouble();
                            System.out.println("Enter Product Category:");
                            scanner.nextLine();
                            String category = scanner.nextLine();
                            admin.addProduct(id, name, price, category);
                        }
                        case 3 -> {
                            System.out.println("Enter Product ID:");
                            int id = scanner.nextInt();
                            System.out.println("Enter Product Name:");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            System.out.println("Enter Product Price:");
                            double price = scanner.nextDouble();
                            System.out.println("Enter Product Discount:");
                            double discount = scanner.nextDouble();
                            System.out.println("Enter Product Category:");
                            scanner.nextLine();
                            String category = scanner.nextLine();
                            admin.addDiscountedProduct(id, name, price, discount, category);
                        }
                        case 4 -> {
                            admin.showProducts();
                            System.out.println("Enter Product ID to edit:");
                            int id = scanner.nextInt();
                            System.out.println("Enter new Product Name:");
                            scanner.nextLine();
                            String newName = scanner.nextLine();
                            System.out.println("Enter new Product Price:");
                            double newPrice = scanner.nextDouble();
                            System.out.println("Enter new Product Category:");
                            scanner.nextLine();
                            String newCategory = scanner.nextLine();
                            admin.editProduct(id, newName, newPrice, newCategory);
                        }
                        case 5 -> {
                            admin.showProducts();
                            System.out.println("Enter Product ID to delete:");
                            int id = scanner.nextInt();
                            admin.removeProduct(id);
                        }
                        case 6 -> {
                            System.out.println("Exiting Admin Mode...");
                            return;
                        }
                        default -> System.out.println("Invalid choice. Try again!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again!");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Invalid password!");
        }
    }

    private static Product findProductById(List<Product> products, int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }
}
