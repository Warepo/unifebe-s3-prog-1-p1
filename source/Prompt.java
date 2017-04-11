import java.util.Scanner;

public class Prompt {

    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Entre com o IP: ");

        IP ip = new IP(reader.next());

        System.out.println();

        System.out.println("Este IP em binário é: " + ip.getBase2());
    }

}
