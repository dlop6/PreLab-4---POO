package Model;

public class User {

    String usuario;
    long id;
    String publicKey;
    double monto;
    long cuotas;
    String numeroDeTarjeta;
    String fechaDeVencimiento;
    String codigoCVV;


    public User(String usuario, long id, String publicKey, double monto, long cuotas, String numeroDeTarjeta, String fechaDeVencimiento, String codigoCVV) {
        this.usuario = usuario;
        this.id = id;
        this.publicKey = publicKey;
        this.monto = monto;
        this.cuotas = cuotas;
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.codigoCVV = codigoCVV;
    }


    public String getUsuario() {
        return usuario;
    }


    public long getId() {
        return id;
    }


    public String getPublicKey() {
        return publicKey;
    }


    public double getMonto() {
        return monto;
    }


    public long getCuotas() {
        return cuotas;
    }


    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }


    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }


    public String getCodigoCVV() {
        return codigoCVV;
    }

    
}
