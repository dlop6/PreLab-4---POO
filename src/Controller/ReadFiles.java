package Controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import Model.UserInfo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFiles {

    public static ArrayList<UserInfo> readJSON(String fileName) {
        JSONParser parser = new JSONParser();
        ArrayList<UserInfo> users = new ArrayList<>();

        try {
            Object obj = parser.parse(new FileReader("src/Model/Data/" + fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transacciones = (JSONArray) jsonObject.get("transacciones");

            for (Object transaccion : transacciones) {
                JSONObject transactionObject = (JSONObject) transaccion;

                String usuario = (String) transactionObject.get("usuario");
                Long id = null;
                if (transactionObject.get("id") instanceof Long) {
                    id = (Long) transactionObject.get("id");
                } else if (transactionObject.get("id") instanceof String) {
                    id = Long.valueOf((String) transactionObject.get("id"));
                }

                String public_key = (String) transactionObject.get("public_key");
                Double monto = (Double) transactionObject.get("monto");

                Long cuotas = null;
                if (transactionObject.get("cuotas") != null) {
                    if (transactionObject.get("cuotas") instanceof Long) {
                        cuotas = (Long) transactionObject.get("cuotas");
                    } else if (transactionObject.get("cuotas") instanceof String) {
                        cuotas = Long.valueOf((String) transactionObject.get("cuotas"));
                    }
                }

                Long numero_de_tarjeta = null;
                if (transactionObject.get("numero_de_tarjeta") != null) {
                    if (transactionObject.get("numero_de_tarjeta") instanceof Long) {
                        numero_de_tarjeta = (Long) transactionObject.get("numero_de_tarjeta");
                    } else if (transactionObject.get("numero_de_tarjeta") instanceof String) {
                        numero_de_tarjeta = Long.valueOf((String) transactionObject.get("numero_de_tarjeta"));
                    }
                }

                Long fecha_de_vencimiento = null;
                if (transactionObject.get("fecha_de_vencimiento") != null) {
                    if (transactionObject.get("fecha_de_vencimiento") instanceof Long) {
                        fecha_de_vencimiento = (Long) transactionObject.get("fecha_de_vencimiento");
                    } else if (transactionObject.get("fecha_de_vencimiento") instanceof String) {
                        fecha_de_vencimiento = Long.valueOf((String) transactionObject.get("fecha_de_vencimiento"));
                    }
                }

                Long codigo_CVV = null;
                if (transactionObject.get("codigo_CVV") != null) {
                    if (transactionObject.get("codigo_CVV") instanceof Long) {
                        codigo_CVV = (Long) transactionObject.get("codigo_CVV");
                    } else if (transactionObject.get("codigo_CVV") instanceof String) {
                        codigo_CVV = Long.valueOf((String) transactionObject.get("codigo_CVV"));
                    }
                }

                // Crear una instancia de UserInfo con los valores convertidos y agregarla a la lista de usuarios
                UserInfo user = new UserInfo(usuario, id != null ? id : 0, public_key, monto, cuotas != null ? cuotas.intValue() : 0, numero_de_tarjeta != null ? String.valueOf(numero_de_tarjeta) : "", fecha_de_vencimiento != null ? fecha_de_vencimiento.intValue() : 0, codigo_CVV != null ? codigo_CVV.intValue() : 0);
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo JSON.");
        }
        return users;
    }
}
