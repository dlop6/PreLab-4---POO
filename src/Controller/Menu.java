package Controller;
import java.util.ArrayList;
import java.util.Scanner;

import Model.PaymentGateway;
import Model.UserInfo;

public class Menu {
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
                // Default implementation for createPayment
                System.out.println("Default Payment Gateway implementation");
            }
        };

        CardType cardType = new CardType(paymentGateway); // Provide PaymentGateway instance
        cardType.cardType(usuarios);
        sc.close();
    }
}
