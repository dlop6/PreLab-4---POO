package Controller;
import java.util.ArrayList;
import Model.*;

public class CardType {

    private PaymentGateway paymentGateway; // Assuming paymentGateway is initialized in this class or passed through a constructor

    public CardType() {
        // You can initialize the paymentGateway here, or provide another way to initialize it.
    }

    public CardType(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void cardType(ArrayList<UserInfo> users) {
        for (UserInfo user : users) {
            Visa visa = new Visa(paymentGateway, user);
            Mastercard mastercard = new Mastercard();
            American american = new American();

            String cardNumber = user.getCardNumber();
            String firstDigit = cardNumber.substring(0, 1);

            switch (firstDigit) {
                case "4":
                    visa.processPayment(user);
                    visa.generateOutputFile("visa.xml"); // Generate Visa output as XML
                    break;
                case "5":
                    mastercard.processPayment(user);
                    mastercard.generateOutputFile("mastercard.json"); // Generate Mastercard output as JSON
                    break;
                case "3":
                    american.processPayment(user);
                    american.setUsers(users); // Set user information for American Express
                    american.generateOutputFile("american.csv"); // Generate American Express output as CSV
                    break;
                default:
                    System.out.println("Tarjeta no soportada");
                    break;
            }
        }
    }
}