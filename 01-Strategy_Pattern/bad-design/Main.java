class PaymentService {
    public void pay(String paymentMethod, double amount) {
        if ( paymentMethod.equals("UPI")) {
            System.out.println("Payment of $" + amount + " is done via UPI");
        } else if ( paymentMethod.equals("Debit Card")) {
            System.out.println("Payment of $" + amount + " is done via Debit Card");
        } else if ( paymentMethod.equals("PayPal")) {
            System.out.println("Payment of $" + amount + " is done via PayPal");
        } else {
            System.out.println("Invalid Payment method! Try again.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();

        paymentService.pay("UPI", 5000.00) ;
        paymentService.pay("PayPal", 5000.00) ;
    }
}