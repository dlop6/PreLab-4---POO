package Controller;

import java.util.ArrayList;
import Model.*;

public class CardType {

    private PaymentGateway paymentGateway; // Se asume que paymentGateway se inicializa en esta clase o se pasa a través de un constructor

    /**
     * Constructor de CardType.
     */
    public CardType() {
        // Aquí se puede inicializar paymentGateway, o proporcionar otro método para hacerlo.
    }

    /**
     * Constructor de CardType con PaymentGateway.
     *
     * @param paymentGateway La pasarela de pagos para procesar los pagos.
     */
    public CardType(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * Método para identificar y procesar el tipo de tarjeta.
     *
     * @param users Lista de información de usuarios y sus pagos.
     */
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
                    visa.generateOutputFile("visa.xml"); // Generar el archivo de salida Visa como XML
                    break;
                case "5":
                    mastercard.processPayment(user);
                    mastercard.generateOutputFile("mastercard.json"); // Generar el archivo de salida Mastercard como JSON
                    break;
                case "3":
                    american.processPayment(user);
                    american.setUsers(users); // Establecer la información de usuario para American Express
                    american.generateOutputFile("american.csv"); // Generar el archivo de salida de American Express como CSV
                    break;
                default:
                    System.out.println("Tarjeta no soportada");
                    break;
            }
        }
    }
}
