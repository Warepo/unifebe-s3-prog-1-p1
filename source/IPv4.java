public class IPv4 {

    private String ip_string = "";
    private String ip_array_base10[]; // decimal
    private String ip_array_base2[]; // binário
    private String mask_class = "";
    private int base = -1;
	private String mask;

    public IPv4(String ip)
    {
        this.base = IP.getBase(ip);

        if (this.base > -1) {
            this.ip_string = ip;

            if (this.base == 10) {
                this.ip_array_base10 = ip.split("\\.");
                this.ip_array_base2 = (String[]) this.toBase2();
            } else if (this.base == 2) {
                this.ip_array_base2 = ip.split("\\.");
//                this.ip_array_base10 = this.toBase10();
            }

            this.mask_class = this.getClass();

            if (this.hasMask()) {
                this.mask = this.getMask();

                int defaultMask = this.getMaskByClass(this.mask_class);

                if (this.mask != defaultMask) {

                    System.err.println("A máscara de IP fornecida é inválida, a máscara correta é " + defaultMask + ".");

                }

            }
        }
    }

    /**
     * @return Returns the IP format in string.
     */
    public static int getBase(String ip)
    {
        int base = -1;

        // Pattern regex = Pattern.compile("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$");

        if (ip.matches("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$")) {
            base = 2;
        } else if (ip.matches("^([0-1]{8}\\.){3}[0-1]{8}$")) {
            base = 10;
        }

        return base;
    }

    /**
     * Question 3.a
     */
    private Object toBase2()
    {
        String base2[] = new String[this.ip_array_base10.length];

        for (int x = 0; x < this.ip_array_base10.length; x++) {
            base2[x] = String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(this.ip_array_base2[x]))));
        }

        return base2;
    }

    /**
     * Question 3.a
     */
    String[] getBase2()
    {
        return this.ip_array_base2;
    }

    /**
     * Question 3.b
     *
     * @return Returns the IP class, A, B or C.
     */
    private String getClass()
    {
        // this method solves #3

        String ip_class = "";
        String classIndicators = this.ip_array_base2[0].substring(0, 2); // primeiros dois dígitos do IP binário

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

    public boolean hasMask()
    {
        return this.ip_string.matches(".+\\/\d+$");
    }

    public String getMask()
    {
        return this.ip_string.split("\\/")[1];
    }

    public int getMaskByClass(String ip_class)
    {
        int mask = -1;

        switch(ip_class) {
            case "A":
                mask = 8;
                break;
            case "B":
                mask = 16;
                break;
            case "C":
                mask = 24;
                break;
        }

        return mask;
    }

}
