package Model;

public class CifradoEmperador {
    public static String cifrar(String mensaje, int desplazamiento) {
        StringBuilder mensajeCifrado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            if (Character.isDigit(caracter)) {
                int numeroOriginal = Character.getNumericValue(caracter);
                int numeroCifrado = (numeroOriginal + desplazamiento) % 10;
                mensajeCifrado.append(numeroCifrado);
            } else {
                mensajeCifrado.append(caracter);
            }
        }
        return mensajeCifrado.toString();
    }

    public static String descifrar(String mensajeCifrado, int desplazamiento) {
        return cifrar(mensajeCifrado, 10 - desplazamiento);
    }

    public static void main(String[] args) {
        String mensajeOriginal = "12345, 6789, 9876";
        int desplazamiento = 3;

        String mensajeCifrado = cifrar(mensajeOriginal, desplazamiento);
        String mensajeDescifrado = descifrar(mensajeCifrado, desplazamiento);

        System.out.println("Mensaje Original: " + mensajeOriginal);
        System.out.println("Mensaje Cifrado: " + mensajeCifrado);
        System.out.println("Mensaje Descifrado: " + mensajeDescifrado);
    }
}