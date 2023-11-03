package Model;

public class UserInfo {
    private String user;
    private int id;
    private String publicKey;
    private double amount;
    private int cuotas;
    private String cardNumber;
    private int expirationDate;
    private int cvv;


    public UserInfo(String user, int id, String publicKey, double amount, int cuotas, String cardNumber, int expirationDate, int cvv) {
        this.user = user;
        this.id = id;
        this.publicKey = publicKey;
        this.amount = amount;
        this.cuotas = cuotas;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int installments) {
        this.cuotas = installments;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}