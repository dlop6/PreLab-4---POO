package Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import Controller.CifradoEmperador;
import java.util.HashMap;

public class Mastercard implements Interfaz {
    private Map<String, Double> cardDatabase; // Simulación de una base de datos de tarjetas

    public Mastercard() {
        cardDatabase = new HashMap<>();
        // Agregar tarjetas a la base de datos para pruebas (en un sistema real, esta
        // información se almacenaría de manera segura)
        cardDatabase.put("1234567890123456", 1000.00);
        cardDatabase.put("9876543210987654", 750.50);
    }

    public void processPayment(UserInfo paymentInfo) {
        // Validar la tarjeta, monto y otros detalles de pago
        String cardNumber = paymentInfo.getCardNumber();
        int cvv = paymentInfo.getCvv();
        double amount = paymentInfo.getAmount();

        if (isValidCard(cardNumber) && amount > 0 && isValidCvv(cvv)) {
            // Realizar el proceso de cobro y actualizar la base de datos (en un sistema
            // real, se conectaría a una pasarela de pago)
            if (cardDatabase.containsKey(cardNumber) && cardDatabase.get(cardNumber) >= amount) {
                cardDatabase.put(cardNumber, cardDatabase.get(cardNumber) - amount);

                // Cifrar el número de tarjeta utilizando CifradoNumerico
                int desplazamiento = 3;
                String encryptedCardNumber = CifradoEmperador.cifrar(cardNumber, desplazamiento);

                // Devolver la respuesta, puedes incluir el número de tarjeta cifrado en la
                // respuesta
                System.out.println("Pago procesado.");
                System.out.println("Numero de tarjeta encriptado: " + encryptedCardNumber);
            } else {
                System.out.println("Tarjeta rechazada.");
            }
        } else {
            System.out.println("Tarjeta rechazada.");
        }
    }

    public void generateOutputFile(String filename) {
        // Implementa la generación del archivo de salida según tus requisitos

        // En este ejemplo, se generará un archivo JSON con los datos de las tarjetas
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write("{\n");
            for (String cardNumber : cardDatabase.keySet()) {
                fileWriter.write("\t\"" + cardNumber + "\": " + cardDatabase.get(cardNumber) + ",\n");
            }
            fileWriter.write("}");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo de salida.");
        }

    }

        private boolean isValidCard(String cardNumber) {
            // Realizar validación de la tarjeta, por ejemplo, longitud y formato

            // En este ejemplo, se valida que la tarjeta tenga 16 dígitos y que sean todos
            // números
            return cardNumber.length() == 16 && cardNumber.matches("[0-9]+");
        }

        private boolean isValidCvv(int cvv) {
            // Realizar validación del código CVV, por ejemplo, longitud
            return cvv >= 100 && cvv <= 999;
        }
    }
