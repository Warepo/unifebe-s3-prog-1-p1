import java.util.Scanner;

public class Prompt {

    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Entre com o IP: ");

        IPv4 ip = new IPv4(reader.next());

        System.out.println();

        System.out.println("Curiosidades sobre o IP de entrada:");
        System.out.println(" - Em binário: " + ip.getBase2());
        System.out.println(" - Máscara: " + ip.getMask());
        System.out.println(" - Classe: " + ip.getIPClass());
    }

}
