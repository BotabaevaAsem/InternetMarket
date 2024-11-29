public class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        return switch (type.toLowerCase()) {
            case "card" -> new CreditCardPayment();
            case "paypal" -> new PayPalPayment();
            default -> throw new IllegalArgumentException("UNKNOWN type of Payment method: " + type);
        };
    }
}

