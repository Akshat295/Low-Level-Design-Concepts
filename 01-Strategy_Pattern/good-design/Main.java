public class Main {
    public static void main(String[] args) {
        PaymentStrategy paymentStrategy = new CardPaymentStrategy();
        PaymentService paymentService = new PaymentService(paymentStrategy);
        paymentService.makePayment(1000);
        paymentStrategy = new PayPalPaymentStrategy();
        paymentService = new PaymentService(paymentStrategy);
        paymentService.makePayment(1000);
        paymentStrategy = new UPIPaymentStrategy();
        paymentService = new PaymentService(paymentStrategy);
        paymentService.makePayment(1000);   
    }
}
