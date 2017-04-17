public class IPv4 {

    private String ip_string; // decimal
    private String address_array[]; // decimal
    private String ip_base2_string; // binário
    private String address_base2_array[]; // binário
    private int base = -1;
    private String mask;
    private String address_class;

    public IPv4(String ip)
    {
        this.base = IPv4.getBase(ip);

        if (this.base > -1)
        {
            if (this.base == 10)
            {
                this.ip_string = ip;
            }
            else if (this.base == 2)
            {
                // this.address_base2_array = ip.split("\\.");
                System.err.println("O IPv4 de entrada deve ser escrito em base decimal.");
                // return false;
            }

            if (this.ip_string != null)
            {
                if (this.hasMask())
                {
                    String ipSplit[] = ip.split("\\/");

                    this.address_array = ipSplit[0].split("\\.");
                    this.mask = ipSplit[1];
                    this.address_class = this.getAddressClass();

                    String defaultMask = IPv4.getMaskByClass(this.address_class);

                    if (this.mask != defaultMask)
                    {
                        System.err.println("A máscara de IPv4 fornecida (" + this.mask + ") é inválida, a máscara correta é " + defaultMask + ".");
                    }
                }
                else
                {
                    this.address_array = ip.split("\\.");
                    this.address_class = this.getAddressClass();
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

        if (ip.matches("^((\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5])))(\\/\\d+)?$")) {
            base = 10;
        } else if (ip.matches("^([0-1]{8}\\.){3}[0-1]{8}$")) {
            base = 2;
        }

        return base;
    }

    /**
     * @return Returns the IPv4 format in string.
     */
    public int getBase()
    {
        if (this.base == -1)
        {
            // Pattern regex = Pattern.compile("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$");

            if (this.ip_string.matches("^((\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5])))(\\/\\d+)?$"))
            {
                this.base = 10;
            }
            else if (this.ip_string.matches("^(([0-1]{8}\\.){3}[0-1]{8})(\\/\\d+)?$"))
            {
                this.base = 2;
            }
        }

        return this.base;
    }

    /**
     * Question 3.a
     */
    public String getBase2()
    {
        if (this.ip_base2_string == null)
        {
            String base2[] = new String[this.address_array.length];

            for (int x = 0; x < this.address_array.length; x++)
            {
                base2[x] = String.format("%08d", Integer.parseInt(Integer.toString(Integer.parseInt(this.address_array[x]), 2)));
            }

            this.address_base2_array = base2;
            this.ip_base2_string = String.join(".", base2);
        }

        return this.ip_base2_string;
    }

    /**
     * Question 5.b
     */
    public static String toBase10(String ip)
    {
        String ip_array[] = ip.split("\\.");
        String newBase[] = new String[ip_array.length];

        for (int x = 0; x < ip_array.length; x++)
        {
            newBase[x] = Integer.toString(Integer.valueOf(ip_array[x], 2));
        }

        return String.join(".", newBase);
    }

    /**
     * Question 3.b
     *
     * @return Returns the IPv4 class, A, B or C.
     */
    public String getAddressClass()
    {
        // this method solves #3

        String address_class = "";
        String classIndicators = String.format("%08d", Integer.parseInt(Integer.toString(Integer.parseInt(this.address_array[0]), 2))).substring(0, 2); // primeiros dois dígitos do IPv4 binário

        switch(classIndicators) {
            // A
            case "00":
            case "01":
                address_class = "A";
                break;

            // B
            case "10":
                address_class = "B";
                break;

            // C
            case "11":
                address_class = "C";
                break;
        }

        return address_class;
    }

    public static String getMaskByClass(String address_class)
    {
        String mask = "";

        switch(address_class) {
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
        return this.mask;
    }

    public String getSubNetwork(String subnet_mask)
    {
        String subnet = "";

        // if (this.hasMask())
        // {
        IPv4 subip = new IPv4(subnet_mask);
        String subip_base2_array[] = subip.getBase2().split("\\.");

        for (int x = 0; x < this.address_base2_array.length; ++x)
        {
            for (int y = 0; y < this.address_base2_array[x].length(); ++y)
            {
                subnet += "" + (Integer.parseInt(this.address_base2_array[x].substring(y, y + 1)) * Integer.parseInt(subip_base2_array[x].substring(y, y + 1)));
            }

            subnet += ".";
        }

        // }
        // else
        // {
        //     System.err.println("Não foi possível descobrir a sub-rede, pois este IP não possui máscara.");
        // }

        return subnet.replaceAll("\\.$", "");
    }
}
