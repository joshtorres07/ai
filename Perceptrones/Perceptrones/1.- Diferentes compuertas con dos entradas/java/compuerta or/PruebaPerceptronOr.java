import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

public class PruebaPerceptronOr {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int x1, x2, salida;
        double salidaR;
        double[] pesos = new double[3];
        pesos = App.obtenerPesos(pesos, "pesosOr.txt");

        try {
            bw.write("Dame estructura 1: ");
            bw.flush();
            x1 = Integer.parseInt(br.readLine());
            bw.write("\nDame estructura 2: ");
            bw.flush();
            x2 = Integer.parseInt(br.readLine());
            salidaR = x1 * pesos[0] + x2 * pesos[1] + pesos[2];

            if (salidaR > 0 ) {
                salida = 1;
            } else {
                salida = 0;
            }

            bw.write(String.format("\n%d, %d = %d\n", x1, x2, salida));
            bw.flush();

            br.close();
            bw.close();
        } catch(IOException ioE) {
        } catch(NumberFormatException nfE) {}
    }
}
