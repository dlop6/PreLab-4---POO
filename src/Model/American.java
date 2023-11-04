package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class American implements Interfaz {
    private ArrayList<UserInfo> users;

    public void setUsers(ArrayList<UserInfo> users) {
        this.users = users;
    }

    @Override
    public void processPayment(UserInfo paymentInfo) {
        double amount = paymentInfo.getAmount();
        int cuotas = paymentInfo.getCuotas();

        double serviceCharge = amount * 0.07;
        if (cuotas > 0) {
            serviceCharge += amount * 0.15;
        }
        amount += serviceCharge;

        // Generating the CSV file with MD5 hash
        String fileName = "american_payment.csv";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Monto,MD5\n");
            fileWriter.write(amount + "," + generateMD5Hash(String.valueOf(amount)));
            fileWriter.close();
            System.out.println("American Express file generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating the American Express file.");
        }
    }

    @Override
    public void generateOutputFile(String filename) {
        // This method is intentionally left blank as the American Express output file is generated in the processPayment method.
    }

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
            throw new RuntimeException("Error generating MD5 hash.", e);
        }
    }
}
