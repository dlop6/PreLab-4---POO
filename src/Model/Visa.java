package Model;

public class Visa implements Interfaz{

    @Override
    public void processPayment(UserInfo paymentInfo) {
        System.out.println("Procesando pago con Lingna Visa");
    }

    @Override
    public void generateOutputFile(String fileName) {
        // Implementation for generateOutputFile goes here
    }
}
