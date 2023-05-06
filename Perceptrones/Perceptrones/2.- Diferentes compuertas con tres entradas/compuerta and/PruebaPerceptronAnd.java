import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PruebaPerceptronAnd {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] xs = new int[4];
        int x1, x2, x3, x4, sumatoria, salida;
        double salidaR;
        double[] pesos = new double[4];
        pesos = PerceptronAndTriple.obtenerPesos(pesos, "pesosTripleAnd.txt");

        xs[3] = 1;
        sumatoria = 0;

        try {
            bw.write("\nDame estructura 1: ");
            bw.flush();
            xs[0] = Integer.parseInt(br.readLine());
            bw.write("\nDame estructura 2: ");
            bw.flush();
            xs[1] = Integer.parseInt(br.readLine());
            bw.write("\nDame estructura 3: ");
            bw.flush();
            xs[2] = Integer.parseInt(br.readLine());
            for (int i = 0; i < pesos.length; i ++) {
                sumatoria += xs[i] * pesos[i];
            }
            salidaR = 1 / (1 + Math.exp(-sumatoria));

            if (salidaR > 0) {
                salida = 1;
            } else {
                salida = 0;
            }

            bw.write(String.format("\n%d, %d, %d = %d\n", xs[0], xs[1], xs[2],
                        salida));
            bw.flush();

            br.close();
            bw.close();
        } catch(IOException ioE) {
        } catch(NumberFormatException nfE) {}
    }
}
