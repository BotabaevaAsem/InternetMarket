public abstract class Product {
    int id;
    String name;
    double price;
    String category;

    Product(String name, double price, int id, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }


    public abstract String getDescription();

    public double getPrice() {
        return price;
    }
}