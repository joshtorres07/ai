import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PerceptronDobleNot {

    public static final String ARCHIVO_PESOS = "pesosDobleNot.txt";

    public static double[][] obtenerPesos(double[][] pesos, String archivo) {
        BufferedReader input;

        try {
            input = new BufferedReader(new FileReader(archivo));
              for (int i = 0; i < pesos.length; i++) {
                for (int j = 0; j < pesos[0].length; j++) {
                    pesos[i][j] = Double.parseDouble(input.readLine());
                }
            }
            input.close();
        } catch (IOException ioE) {}
        return pesos;
    }

    public static void establecerPesos(double[][] pesos, String archivo) {
        PrintWriter output;
        
        try {
            output = new PrintWriter(new FileWriter(archivo));

            for (int i = 0; i < pesos.length; i++) {
                for (int j = 0; j < pesos[0].length; j++) {
                    output.println(pesos[i][j]);
                }
            }
            
            output.close();
        } catch (IOException ioE) {}
    }

    public static void main(String[] args) {
        int[][] datos = {
            {0, 0, 1}, // p, q, b
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        double[][] pesos = {
            {Math.random() * 10, Math.random() * 10, Math.random() * 10}, 
            {Math.random() * 10, Math.random() * 10, Math.random() * 10} 
        };

        int[][] salidas = {
            {1, 1},
            {1, 0},
            {0, 1},
            {0, 0}
        };

        int salidaP = 0;
        int salidaQ = 0;
        boolean aprendiendo = true;
        double tasaA = 0.3;

        while (aprendiendo) {
            aprendiendo = false;

            for (int cont = 0; cont < datos.length; cont++) {
                int sumatoriaP = 0;
                int sumatoriaQ = 0;
                for (int i = 0; i < pesos.length; i++) {
                    sumatoriaP += datos[cont][i] * (pesos[i][0] + pesos[i][2]);
                    sumatoriaQ += datos[cont][i] * (pesos[i][1] + pesos[i][2]);
                }
                double salidaRP = 1 / (1 + Math.exp(-sumatoriaP));
                double salidaRQ = 1 / (1 + Math.exp(-sumatoriaQ));

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

                int errorP = salidas[cont][0] - salidaP;
                int errorQ = salidas[cont][1] - salidaQ;

                if (errorP != 0) {
                    pesos[0][0] += tasaA * errorP * datos[cont][0];
                    pesos[1][0] += tasaA * errorP * datos[cont][1];
                    pesos[0][2] += tasaA * errorP;
                    pesos[1][2] += tasaA * errorP;
                    System.out.println("Entrenando Neurona 1");
                    aprendiendo = true;
                }

                if (errorQ != 0) {
                    pesos[0][1] += tasaA * errorQ * datos[cont][0];
                    pesos[1][1] += tasaA * errorQ * datos[cont][1];
                    pesos[0][2] += tasaA * errorQ;
                    pesos[0][2] += tasaA * errorQ;
                    System.out.println("Entrenando Neurona 2");
                    aprendiendo = true;
                }

            }
        }
        System.out.println("\n");

        for (int cont = 0; cont < datos.length; cont++) {
            int sumatoriaP = 0;
            int sumatoriaQ = 0;
            for (int i = 0; i < pesos.length; i++) {
                sumatoriaP += datos[cont][i] * (pesos[i][0] + pesos[i][2]);
                sumatoriaQ += datos[cont][i] * (pesos[i][1] + pesos[i][2]);
            }
            double salidaRP = 1 / (1 + Math.exp(-sumatoriaP));
            double salidaRQ = 1 / (1 + Math.exp(-sumatoriaQ));

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

            System.out.printf("%d %d = %d %d, perception p = %d q = %d\n",
                    datos[cont][0],
                    datos[cont][1],
                    salidas[cont][0],
                    salidas[cont][1],
                    salidaP,
                    salidaQ);
        }
        establecerPesos(pesos, ARCHIVO_PESOS);
    }
}
