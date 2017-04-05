
import java.util.ArrayList;
public class machine {

  private String number_ip;
  private ArrayList sequence = new ArrayList();
  public machine()
  {
    this.sequence = sequence;
    this.number_ip = number_ip;
  }


  public void convert(String ip){
    this.sequence = ip.split(".");
    System.out.println(" Parte 1: "+this.sequence[0]+"\n");
  }

}
