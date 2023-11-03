package Controller;

import java.util.ArrayList;

import Model.*;

public class CardType{

    public static void cardType(ArrayList<UserInfo> users){
        Visa visa = new Visa();
        Mastercard mastercard = new Mastercard();
        American american = new American();

        for (UserInfo user : users) {
            String cardNumber = user.getCardNumber();
            String firstDigit = cardNumber.substring(0, 1);
            switch (firstDigit) {
                case "4":
                    
                    visa.processPayment(user);
                    
                    break;
                case "5":
                    
                    mastercard.processPayment(user);

                    break;
                case "3":
                    
                    american.processPayment(user);
                        
                    break;
            
                default:
                    System.out.println("Tarjeta no soportada");
                    break;
            }
        }

        visa.generateOutputFile("visa.txt");
        mastercard.generateOutputFile("mastercard.txt");
        american.generateOutputFile("american.txt");
        

    }
}

