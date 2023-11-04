package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.UserInfo;

public class Menu {
    public static void menu() {
        System.out.println("¡Bienvenido al procesador de pago!");
        System.out.println("Ingrese el nombre del archivo de entrada: ");
        Scanner sc = new Scanner(System.in);
        String archivo = sc.nextLine();
        ReadFiles readFiles = new ReadFiles();
        ArrayList<UserInfo> usuarios = readFiles.readJSON(archivo);
        CardType cardType = new CardType();
        cardType.cardType(usuarios);
        sc.close();
    }
}