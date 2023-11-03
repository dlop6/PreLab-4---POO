package Model;

public class American implements Interfaz {

    @Override
    public void processPayment(UserInfo paymentInfo) {
        
        throw new UnsupportedOperationException("Unimplemented method 'processPayment'");
    }

    @Override
    public void generateOutputFile(String filename) {
        
        throw new UnsupportedOperationException("Unimplemented method 'generateOutputFile'");
    }
    
}
