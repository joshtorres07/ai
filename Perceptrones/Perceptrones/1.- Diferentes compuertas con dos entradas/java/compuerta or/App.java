import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class App {
    public static final String ARCHIVO_PESOS = "pesosOr.txt";
    public static double[] obtenerPesos(double[] pesos, String archivo) {
        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(archivo));
            for (int i = 0; i < pesos.length; i++) {
                pesos[i] = Double.parseDouble(input.readLine());
            }
            input.close();
        } catch(IOException ioE) { }
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
        } catch(IOException ioE) { }
    }

    public static void main(String[] args) {
        int[][] datos = {
            {0, 0, 0}, 
            {0, 1, 1}, 
            {1, 0, 1}, 
            {1, 1, 1}
        };

        double[] pesos = {
            Math.random() * 10,
            Math.random() * 10,
            Math.random() * 10
        };
        int salida = 0;
        boolean aprendiendo = true;
        double tasaA = 0.3;

        while(aprendiendo) {
            aprendiendo = false;
  
            for (int cont = 0; cont < datos.length; cont++) {
                double salidaR = datos[cont][0] * pesos[0] +
                    datos[cont][1] * pesos[1] +
                    pesos[2]; 

                if (salidaR > 0) {
                    salida = 1;
                } else {
                    salida = 0;
                }


                int error = datos[cont][2] - salida;

                if (error != 0) {
                    pesos[0] += tasaA * error * datos[cont][0];
                    pesos[1] += tasaA * error * datos[cont][1];
                    pesos[2] += tasaA * error;

                    System.out.println("Entrenando");
                    aprendiendo = true;
                }
            }
        }
        System.out.println("\n");

        for (int cont = 0; cont < datos.length; cont++) {
            double salidaR = datos[cont][0] * pesos[0] +
                (datos[cont][1] * pesos[1]) +
                pesos[2];


            if (salidaR > 0) {
                salida = 1;
            } else {
                salida = 0;
            }

            System.out.printf("%d %d = %d, perception = %d\n",
                    datos[cont][0],
                    datos[cont][1],
                    datos[cont][2],
                    salida);
        }
        establecerPesos(pesos, ARCHIVO_PESOS);
    }
}
