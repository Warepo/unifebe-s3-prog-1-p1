import java.util.Scanner;
public class Prompt{

  public static void main (String[] args){
    Scanner read = new Scanner(System.in);
    Machine data = new Machine();

    System.out.println("Entre com o IP :\n");

    String ip = read.next();

    int result = data.convert(ip);
    System.out.println(result);

  }

}
