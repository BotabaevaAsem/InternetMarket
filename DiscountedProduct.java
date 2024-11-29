public class DiscountedProduct extends Product {
    private Product product;
    private double discount;


    DiscountedProduct(Product product, double discount) {
        super(product.name, product.price, product.id, product.category);

        this.product = product;
        this.discount = discount;
    }

    @Override
    public String getDescription() {
        return product.getDescription() + " (Discount: " + (int)(discount) + "%)";
    }

    @Override
    public double getPrice() {
        return product.getPrice() * ((100 - discount)/100);
    }
}
