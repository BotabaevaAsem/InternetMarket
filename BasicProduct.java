public class BasicProduct extends Product {
    BasicProduct(int id,String name, double price,String category) {super(name, price,id,category);
    }

    @Override
    public String getDescription() {
        return name;
    }
}
