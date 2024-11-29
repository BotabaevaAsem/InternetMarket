class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Pay " + amount + " $ from PayPal.");
    }
}