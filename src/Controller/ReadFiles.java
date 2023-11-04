package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;

import java.io.FileReader;
import Model.UserInfo;


public class ReadFiles {

    ArrayList<UserInfo> users = new ArrayList<UserInfo>();

    public ArrayList<UserInfo> readJSON(String fileName) {
        fileName = "users_data.json";

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src\\Model\\Data\\"+fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transacciones = (JSONArray) jsonObject.get("transacciones");

            for (Object o : transacciones) {
                JSONObject transaction = (JSONObject) o;
                String usuario = (String) transaction.get("usuario");
                long id = (Long) transaction.get("id"); // Change type to long
                String publicKey = (String) transaction.get("public_key");
                double monto = (Double) transaction.get("monto");
                int cuotas = (int) (long) transaction.get("cuotas"); // Cast to long first, then to int
                String numeroDeTarjeta = transaction.get("numero_de_tarjeta").toString();
                int fechaDeVencimiento = (int) (long) transaction.get("fecha_de_vencimiento"); // Cast to long first, then to int
                int codigoCVV = (int) (long) transaction.get("codigo_CVV"); // Cast to long first, then to int
                UserInfo user = new UserInfo(usuario, id, publicKey, monto, cuotas, numeroDeTarjeta, fechaDeVencimiento, codigoCVV); // Change type to long
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}