package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;


public class ReadFiles {
    public void readJSON(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src\\Model\\Data\\"+fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transacciones = (JSONArray) jsonObject.get("transacciones");

            for (Object o : transacciones) {
                JSONObject transaction = (JSONObject) o;
                String usuario = (String) transaction.get("usuario");
                long id = (Long) transaction.get("id");
                String publicKey = (String) transaction.get("public_key");
                double monto = (Double) transaction.get("monto");
                long cuotas = (Long) transaction.get("cuotas");
                String numeroDeTarjeta = transaction.get("numero_de_tarjeta").toString();
                String fechaDeVencimiento = (String) transaction.get("fecha_de_vencimiento");
                String codigoCVV = (String) transaction.get("codigo_CVV");

                System.out.println("---------------------------");
                System.out.println("Usuario: " + usuario);
                System.out.println("ID: " + id);
                System.out.println("Public Key: " + publicKey);
                System.out.println("Monto: " + monto);
                System.out.println("Cuotas: " + cuotas);
                System.out.println("Numero de Tarjeta: " + numeroDeTarjeta);
                System.out.println("Fecha de Vencimiento: " + fechaDeVencimiento);
                System.out.println("Codigo CVV: " + codigoCVV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}