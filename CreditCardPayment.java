public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payed " + amount + " $. with Credit Card.");
    }
}