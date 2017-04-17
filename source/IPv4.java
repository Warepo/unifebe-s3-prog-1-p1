package p1;

public class IPv4 {

    private String ip_string; // decimal
    private String address_array[]; // decimal
    private String ip_base2_string; // binário
    private String address_base2_array[]; // binário
    private String mask;
    private String address_class;

    public IPv4(String ip)
    {
        if (IPv4.isValid(ip))
        {
            this.ip_string = ip;
        }
        else
        {
            // this.address_base2_array = ip.split("\\.");
            System.err.println("O IPv4 de entrada deve ser escrito em base decimal.");
            System.err.println("Respeitando o formato: 255.255.255.255.");
            return;
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

    public String getMask()
    {
        return this.mask;
    }

    /**
     * @return Returns the IPv4 format in string.
     */
    public static int getBase(String ip)
    {
        int base = -1;

        // Pattern regex = Pattern.compile("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$");

        if (IPv4.isValid(ip))
        {
            base = 10;
        }
        else if (IPv4.isValidBinary(ip))
        {
            base = 2;
        }

        return base;
    }

    /**
     * @return Returns if the IPv4 format is a valid IPv4 decimal.
     */
    public static boolean isValid(String ip)
    {
        return ip.matches("^((\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5])))(\\/\\d+)?$");
    }

    /**
     * @return Returns if the IPv4 format is a valid binary IPv4.
     */
    public static boolean isValidBinary(String ip)
    {
        return ip.matches("^([0-1]{8}\\.){3}[0-1]{8}$");
    }

    /**
     * Question 3.a
     */
    public String toBase2()
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
            newBase[x] = Integer.toString(Integer.parseInt(ip_array[x]), 10);
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

    public static boolean hasMask(String ip)
    {
        return IPv4.isValid(ip) && ip.matches(".+\\/\\d+$");
    }

    public String getSubNetwork(String subnet_mask)
    {
        String subnet = "";

        // if (this.hasMask())
        // {
        IPv4 subip = new IPv4(subnet_mask);
        String subip_base2_array[] = subip.toBase2().split("\\.");

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
