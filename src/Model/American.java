package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Clase que implementa la interfaz para procesar pagos con tarjeta American Express.
 */
public class American implements Interfaz {
    private ArrayList<UserInfo> users;

    /**
     * Establece la lista de usuarios para American Express.
     *
     * @param users Lista de usuarios.
     */
    public void setUsers(ArrayList<UserInfo> users) {
        this.users = users;
    }

    /**
     * Procesa el pago mediante tarjeta American Express y genera un archivo CSV con el hash MD5.
     *
     * @param paymentInfo Información del pago a procesar.
     */
    @Override
    public void processPayment(UserInfo paymentInfo) {
        double amount = paymentInfo.getAmount();
        int cuotas = paymentInfo.getCuotas();

        double serviceCharge = amount * 0.07;
        if (cuotas > 0) {
            serviceCharge += amount * 0.15;
        }
        amount += serviceCharge;

        // Genera el archivo CSV con el hash MD5
        String fileName = "american_payment.csv";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Monto,MD5\n");
            fileWriter.write(amount + "," + generateMD5Hash(String.valueOf(amount)));
            fileWriter.close();
            System.out.println("Archivo de American Express generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo de American Express.");
        }
    }

    /**
     * Genera un archivo de salida, método dejado intencionalmente vacío.
     *
     * @param filename Nombre del archivo de salida.
     */
    @Override
    public void generateOutputFile(String filename) {
        // Este método se deja intencionalmente en blanco ya que el archivo de salida de American Express se genera en el método processPayment.
    }

    /**
     * Genera un hash MD5 a partir de una cadena de entrada.
     *
     * @param input Entrada para generar el hash.
     * @return Hash MD5 generado.
     */
    private String generateMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando el hash MD5.", e);
        }
    }
}
