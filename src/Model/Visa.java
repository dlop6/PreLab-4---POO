package Model;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase que implementa la interfaz para procesar pagos con tarjeta Visa.
 */
public class Visa implements Interfaz {
    private UserInfo paymentInfo;
    private PaymentGateway paymentGateway;

    /**
     * Constructor de Visa.
     * @param paymentGateway  Pasarela de pago utilizada.
     * @param paymentInfo     Información del pago.
     */
    public Visa(PaymentGateway paymentGateway, UserInfo paymentInfo) {
        this.paymentGateway = paymentGateway;
        this.paymentInfo = paymentInfo;
    }

    /**
     * Procesa el pago mediante tarjeta Visa.
     *
     * @param paymentInfo Información del pago a procesar.
     */
    @Override
    public void processPayment(UserInfo paymentInfo) {
        // Validando el numero de tarjeta
        String cardNumber = paymentInfo.getCardNumber();
        double amount = paymentInfo.getAmount();

        if (isValidCard(cardNumber) && amount > 0) {
            // Cálculo de comisión e IVA
            double commission = amount * 0.05;
            double ivaCommission = commission * 0.12;
            double total = amount + commission - ivaCommission;

            // Creando el contenido XML
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

            // Generar y guardar el archivo XML
            String fileName = "visa_payment.xml";
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(xmlContent);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo XML de Visa.");
            }

            // Iniciar el pago a través de la pasarela de pago
            paymentGateway.createPayment();
        } else {
            System.out.println("Tarjeta rechazada.");
        }
    }

    /**
     * Método para generar un archivo de salida. En Visa, el archivo se genera en el método processPayment.
     *
     * @param filename Nombre del archivo.
     */
    @Override
    public void generateOutputFile(String filename) {
        // Este método se deja vacío intencionalmente, ya que el archivo de salida de Visa se genera en el método processPayment.
    }

    /**
     * Verifica si el número de tarjeta es válido y comienza con '4' (cifra característica de las tarjetas Visa).
     *
     * @param cardNumber Número de tarjeta a verificar.
     * @return Devuelve true si la tarjeta es válida, de lo contrario, false.
     */
    private boolean isValidCard(String cardNumber) {
        return cardNumber.length() == 16 && cardNumber.startsWith("4");
    }
}
