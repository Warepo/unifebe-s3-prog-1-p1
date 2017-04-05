import java.util.Scanner;
public class prompt{
  public static void main (String[] args){
    Scanner read = new Scanner(System.in);
    String ip;
    machine data = new machine();

    System.out.println("Entre com o IP :\n");
    ip = read.next();

    data.convert(ip);


  }
}
