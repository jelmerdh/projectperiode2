public class Main {

        public static void main(String[] args) throws Exception    {
            CsvWrite jack = new CsvWrite();
            for (int i=0; i<9000; i++) {
                jack.writeMexico(760013, "2021-03-02", "16:01:14", (float)24, (float)5, (float)14,(float)14,(float)35, (float)14,(float)4,"n", (float)15, 3);
            }

        }

    }

