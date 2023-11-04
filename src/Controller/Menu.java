package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import Model.PaymentGateway;
import Model.UserInfo;

/**
 * Clase que gestiona el menú del procesador de pagos.
 */
public class Menu {
    /**
     * Método que muestra el menú de procesamiento de pagos.
     */
    public static void menu() {
        System.out.println("¡Bienvenido al procesador de pago!");
        System.out.println("Ingrese el nombre del archivo de entrada: ");
        Scanner sc = new Scanner(System.in);
        String archivo = sc.nextLine();
        ReadFiles readFiles = new ReadFiles();
        ArrayList<UserInfo> usuarios = ReadFiles.readJSON(archivo);
        PaymentGateway paymentGateway = new PaymentGateway() {
            @Override
            public void createPayment() {
                // Implementación predeterminada para createPayment
                System.out.println("Implementación predeterminada de Payment Gateway");
            }
        };

        CardType cardType = new CardType(paymentGateway); // Proporcionar una instancia de PaymentGateway
        cardType.cardType(usuarios);
        sc.close();
    }
}

