public class CardPaymentStrategy implements PaymentStrategy {
   @Override
   public void pay(double amount) {
    System.out.println("Paying " + amount + " using card");
   }
}
