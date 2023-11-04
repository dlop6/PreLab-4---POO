package Model;

import java.io.FileWriter;
import java.io.IOException;

public class Visa implements Interfaz {
    private UserInfo paymentInfo;
    private PaymentGateway paymentGateway;

    public Visa(PaymentGateway paymentGateway, UserInfo paymentInfo) {
        this.paymentGateway = paymentGateway;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public void processPayment(UserInfo paymentInfo) {
        // Validating the card number and amount
        String cardNumber = paymentInfo.getCardNumber();
        double amount = paymentInfo.getAmount();

        if (isValidCard(cardNumber) && amount > 0) {
            // Commission and IVA calculation
            double commission = amount * 0.05;
            double ivaCommission = commission * 0.12;
            double total = amount + commission - ivaCommission;

            // Creating the XML content
            String xmlContent = "<PaymentInfo>\n" +
                    "\t<User>" + paymentInfo.getUser() + "</User>\n" +
                    "\t<Id>" + paymentInfo.getId() + "</Id>\n" +
                    "\t<PublicKey>" + paymentInfo.getPublicKey() + "</PublicKey>\n" +
                    "\t<Amount>" + total + "</Amount>\n" +
                    "\t<Cuotas>" + paymentInfo.getCuotas() + "</Cuotas>\n" +
                    "\t<CardNumber>" + cardNumber + "</CardNumber>\n" +
                    "\t<ExpirationDate>" + paymentInfo.getExpirationDate() + "</ExpirationDate>\n" +
                    "\t<CVV>" + paymentInfo.getCvv() + "</CVV>\n" +
                    "</PaymentInfo>";

            // Generating and saving the XML file
            String fileName = "visa_payment.xml";
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(xmlContent);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error saving the Visa XML file.");
            }

            // Initiating payment through the gateway
            paymentGateway.createPayment();
        } else {
            System.out.println("Rejected Card.");
        }
    }

    @Override
    public void generateOutputFile(String filename) {
        // This method is intentionally left blank as the Visa output file is generated in the processPayment method.
    }

    private boolean isValidCard(String cardNumber) {
        return cardNumber.length() == 16 && cardNumber.startsWith("4");
    }
}

