public class Machine {

  private String number_ip;
  public Machine()
  {
    this.number_ip = number_ip;
  }


  public int convert(String ip){
    String aux[] = ip.split(".");

    int parte1 = String.valueOf(aux[0],2);

    this.number_ip = parte1;

  }

}
