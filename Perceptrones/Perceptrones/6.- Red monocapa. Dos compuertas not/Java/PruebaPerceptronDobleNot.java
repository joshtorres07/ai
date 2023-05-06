import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PruebaPerceptronDobleNot {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] xs = new int[3];
        int sumatoriaP = 0;
        int sumatoriaQ = 0;
        int salidaP = 0; 
        int salidaQ = 0;
        double salidaRP = 0;
        double salidaRQ = 0;
        double[][] pesos = new double[2][3];
        pesos = PerceptronDobleNot.obtenerPesos(pesos, PerceptronDobleNot.ARCHIVO_PESOS);

        xs[2] = 1;

        try {
            bw.write("\nDame estructura 1: ");
            bw.flush();
            xs[0] = Integer.parseInt(br.readLine());
            bw.write("\nDame estructura 2: ");
            bw.flush();
            xs[1] = Integer.parseInt(br.readLine());

            for (int i = 0; i < pesos.length; i++) {
                sumatoriaP += xs[i] * (pesos[i][0] + pesos[i][2]);
                sumatoriaQ += xs[i] * (pesos[i][1] + pesos[i][2]);
            }

            salidaRP = 1 / (1 + Math.exp(-sumatoriaP));
            salidaRQ = 1 / (1 + Math.exp(-sumatoriaQ));

            if (salidaRP > 0) {
                salidaP = 1;
            } else {
                salidaP = 0;
            }

            if (salidaRQ > 0) {
                salidaQ = 1;
            } else {
                salidaQ = 0;
            }

            bw.write(String.format("\n%d, %d = %d %d\n", xs[0], xs[1],
                        salidaP, salidaQ));
            bw.flush();

            br.close();
            bw.close();
        } catch(IOException ioE) {
        } catch(NumberFormatException nfE) {}
    }
}
