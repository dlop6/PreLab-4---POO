package Model;

import java.io.FileWriter;
import java.io.IOException;

public class Visa implements Interfaz {

    public interface PaymentGateway {
    void createPayment();
}

    private PaymentGateway paymentGateway;
    private UserInfo paymentInfo;

    public Visa(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public void processPayment(UserInfo paymentInfo) {
        // Validar la tarjeta, monto y otros detalles de pago
        String cardNumber = paymentInfo.getCardNumber();
        double amount = paymentInfo.getAmount();

        if (isValidCard(cardNumber) && amount > 0) {
            // Calcular comisión y IVA
            double comision = amount * 0.05;
            double ivaComision = comision * 0.12;
            double total = amount + comision - ivaComision;

            // Formatear la respuesta como XML
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

            // Guardar el archivo XML
            String fileName = "visa_payment.xml";
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(xmlContent);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo XML.");
            }

            // Enviar a la pasarela de pago
            paymentGateway.createPayment();
        } else {
            System.out.println("Tarjeta rechazada.");
        }
    }

    private boolean isValidCard(String cardNumber) {
        // Realizar validación de la tarjeta, por ejemplo, longitud y formato
        return cardNumber.length() == 16 && cardNumber.startsWith("4");
    }

    @Override
    public void generateOutputFile(String fileName) {
        // Implementation for generateOutputFile goes here
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Información de pago Visa\n");
            fileWriter.write("Usuario: " + paymentInfo.getUser() + "\n");
            fileWriter.write("ID: " + paymentInfo.getId() + "\n");
            fileWriter.write("Monto: " + paymentInfo.getAmount() + "\n");
            fileWriter.write("Cuotas: " + paymentInfo.getCuotas() + "\n");
            fileWriter.write("Número de Tarjeta: " + paymentInfo.getCardNumber() + "\n");
            fileWriter.write("Fecha de Vencimiento: " + paymentInfo.getExpirationDate() + "\n");
            fileWriter.write("CVV: " + paymentInfo.getCvv() + "\n");
            fileWriter.close();
            System.out.println("Archivo para Visa generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo para Visa.");
        }
    }
}
