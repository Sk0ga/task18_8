package cs.vsu.ru.skogorev_m_a.gr12;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;

public class Main {


    public static class InputArgs {
        public String inputFile;
        public String outputFile;
        public boolean win;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static InputArgs parseCmdArgs (String[] args) {
        InputArgs params = new InputArgs();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }

            if ((args[0].equals("-i") || args[0].equals("--input-file")) && (args[2].equals("--output-file") ||
                    args[2].equals("-o")) && args[1].contains(".txt") && args[3].contains(".txt")) {
                params.win = true;
                params.inputFile = "" + args[1];
                params.outputFile = "" + args[3];
            } else {
                params.error = true;
                params.help = true;
            }
            return params;

        }
        else {
            params.help = true;
            params.error = true;
        }


        return params;
    }

    public static void winMain() {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }


    public static void main(String[] args) throws Exception {
        InputArgs params = parseCmdArgs(args);

        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println(" -i input-file -o output-file // ");
            out.println(" <cmd> —help");
            out.println(" <cmd> —window // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            winMain();
        } else {
            int[][] arr2 = ArrayUtils.readIntArray2FromFile(params.inputFile);
            if (arr2 == null || arr2.length == 0 || arr2[0].length == 0 ) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            } else {
                String out = params.outputFile;
                PrintWriter pw = new PrintWriter(out);
                arr2 = Logic.process(arr2);
                for (int[] ints : arr2) {
                    for (int j = 0; j < arr2[0].length; j++) {
                        pw.print(ints[j]);
                        pw.print(" ");
                    }
                    pw.println();
                }
                pw.close();
            }
        }
    }

}
