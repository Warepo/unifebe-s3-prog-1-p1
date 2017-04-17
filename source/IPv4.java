public class IPv4 {

    private String ip_string; // decimal
    private String ip_array[]; // decimal
    private String ip_base2_string; // binário
    private String ip_base2_array[]; // binário
    private int base = -1;
    private String mask;
    private String ip_class;

    public IPv4(String ip)
    {
        this.base = IPv4.getBase(ip);

        if (this.base > -1) {

            if (this.base == 10) {

                this.ip_string = ip;
                this.ip_array = ip.split("\\.");

            } else if (this.base == 2) {

                // this.ip_base2_array = ip.split("\\.");
                System.err.println("O IPv4 de entrada deve ser escrito em base decimal.");
                // return false;

            }

            if (this.ip_string != null)
            {
                this.ip_class = this.getIPClass();

                if (this.hasMask())
                {
                    this.mask = this.getMask();

                    String defaultMask = IPv4.getMaskByClass(this.ip_class);

                    if (this.mask != defaultMask)
                    {
                        System.err.println("A máscara de IPv4 fornecida é inválida, a máscara correta é " + defaultMask + ".");
                    }

                }
            }
        }
    }

    /**
     * @return Returns the IPv4 format in string.
     */
    public static int getBase(String ip)
    {
        int base = -1;

        // Pattern regex = Pattern.compile("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$");

        if (ip.matches("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$")) {
            base = 10;
        } else if (ip.matches("^([0-1]{8}\\.){3}[0-1]{8}$")) {
            base = 2;
        }

        return base;
    }

    /**
     * Question 3.a
     */
    private Object toBase2()
    {
        String base2[] = new String[this.ip_array.length];

        for (int x = 0; x < this.ip_array.length; x++) {
            base2[x] = String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(this.ip_array[x]))));
        }

        return base2;
    }

    /**
     * Question 3.a
     */
    String[] getBase2()
    {
        return this.ip_base2_array;
    }

    /**
     * Question 3.b
     *
     * @return Returns the IPv4 class, A, B or C.
     */
    public String getIPClass()
    {
        // this method solves #3

        String ip_class = "";
        String classIndicators = Integer.toString(Integer.parseInt(this.ip_array[0]), 2).substring(0, 2); // primeiros dois dígitos do IPv4 binário

        switch(classIndicators) {
            // A
            case "00":
            case "01":
                ip_class = "A";
                break;

            // B
            case "10":
                ip_class = "B";
                break;

            // C
            case "11":
                ip_class = "C";
                break;
        }

        return ip_class;
    }

    public static String getMaskByClass(String ip_class)
    {
        String mask = "";

        switch(ip_class) {
            case "A":
                mask = "8";
                break;
            case "B":
                mask = "16";
                break;
            case "C":
                mask = "24";
                break;
        }

        return mask;
    }

    public boolean hasMask()
    {
        return this.ip_string.matches(".+\\/\\d+$");
    }

    public String getMask()
    {
        return this.mask != null ? this.mask : this.ip_string.split("\\/")[1];
    }

}
