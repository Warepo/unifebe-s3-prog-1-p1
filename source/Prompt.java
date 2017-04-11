import java.util.Scanner;

public class Prompt {

    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);

        IP data = new IP();

        System.out.print("Entre com o IP: ");

        String ip = reader.next();

        System.out.println();

        String a = data.parse(ip);
        System.out.println(a);
    }

}
