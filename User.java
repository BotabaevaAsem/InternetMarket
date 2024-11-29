public class User implements Observer {
    private String name;
    private Cart cart;

    User(String name) {
        this.name = name;
        this.cart = new Cart();
    }
    public Cart getCart() {
        return cart;
    }

    public String getName() {
        return name;
    }


    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}