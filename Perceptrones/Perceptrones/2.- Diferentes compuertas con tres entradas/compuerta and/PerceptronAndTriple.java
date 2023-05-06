import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PerceptronAndTriple {

    public static final String ARCHIVO_PESOS = "pesosTripleAnd.txt";

    public static double[] obtenerPesos(double[] pesos, String archivo) {
        BufferedReader input;

        try {
            input = new BufferedReader(new FileReader(archivo));
            for (int i = 0; i < pesos.length; i++) {
                pesos[i] = Double.parseDouble(input.readLine());
            }
            input.close();
        } catch (IOException ioE) { }

        return pesos;
    }

    public static void establecerPesos(double[] pesos, String archivo) {
        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(new File(archivo)));

            for (int i = 0; i < pesos.length; i++) {
                output.println(pesos[i]);
            }

            output.close();
        } catch (IOException ioE) { }
    }

    public static void main(String[] args) {

        int[][] datos = {
            {0, 0, 0, 1}, // x, y, z, b
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {0, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };

        double[] pesos = {
            Math.random() * 10, 
            Math.random() * 10, 
            Math.random() * 10, 
            Math.random() * 10  
        };

        int[] salidas = {0, 0, 0, 0, 0, 0, 0, 0, 1};

        int salida = 0;
        boolean aprendiendo = true;
        double tasaA = 0.3;

        while (aprendiendo) {
            aprendiendo = false;

            for (int cont = 0; cont < datos.length; cont++) {
                int sumatoria = 0;
                for (int i = 0; i < pesos.length; i++) {
                    sumatoria += datos[cont][i] * pesos[i];
                }
                double salidaR = 1 / (1 + Math.exp(-sumatoria));

                if (salidaR > 0) {
                    salida = 1;
                } else {
                    salida = 0;
                }

                int error = salidas[cont] - salida;

                if (error != 0) {
                    pesos[0] += tasaA * error * datos[cont][0];
                    pesos[1] += tasaA * error * datos[cont][1];
                    pesos[2] += tasaA * error * datos[cont][2];
                    pesos[3] += tasaA * error;

                    System.out.println("Entrenando");
                    aprendiendo = true;
                }
            }
        }
        System.out.println("\n");

        for (int cont = 0; cont < datos.length; cont++) {
            int sumatoria = 0;
            for (int i = 0; i < pesos.length; i++) {
                sumatoria += datos[cont][i] * pesos[i];
            }
            double salidaR = 1 / (1 + Math.exp(-sumatoria));

            if (salidaR > 0) {
                salida = 1;
            } else {
                salida = 0;
            }

            System.out.printf("%d %d %d %d = %d, perception = %d\n",
                    datos[cont][0],
                    datos[cont][1],
                    datos[cont][2],
                    datos[cont][3],
                    salidas[cont],
                    salida);
        }
    }

}
