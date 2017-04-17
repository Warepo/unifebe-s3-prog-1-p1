package p1;

import java.util.Scanner;

public class Prompt {

    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Entre com o IP: ");

        IPv4 ip = new IPv4(reader.next());

        System.out.println();

        System.out.println("Curiosidades sobre o IP de entrada:");
        System.out.println(" - Em binário: " + ip.toBase2());
        System.out.println(" - Máscara: " + ip.getMask());
        System.out.println(" - Classe: " + ip.getAddressClass());

        System.out.print("Digite sua máscara de sub-rede: ");
        String subnet_mask = reader.next();
        String subnet_ip = ip.getSubNetwork(subnet_mask);

        System.out.println(" - Sub-rede em binário: " + subnet_ip);
        System.out.println(" - Sub-rede: " + IPv4.toBase10(subnet_ip));
    }

}
