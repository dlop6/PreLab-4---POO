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
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src\\Model\\Data\\"+fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transacciones = (JSONArray) jsonObject.get("transacciones");

            for (Object o : transacciones) {
                JSONObject transaction = (JSONObject) o;
                String usuario = (String) transaction.get("usuario");
                int id = (int) transaction.get("id");
                String publicKey = (String) transaction.get("public_key");
                double monto = (Double) transaction.get("monto");
                int cuotas = (int) transaction.get("cuotas");
                String numeroDeTarjeta = transaction.get("numero_de_tarjeta").toString();
                int fechaDeVencimiento = (int) transaction.get("fecha_de_vencimiento");
                int codigoCVV = (int) transaction.get("codigo_CVV");
                UserInfo user = new UserInfo(usuario, id, publicKey, monto, cuotas, numeroDeTarjeta, fechaDeVencimiento, codigoCVV);
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}