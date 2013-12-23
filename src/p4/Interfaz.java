package p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interfaz {

    private final String NO_INT_ERROR = "Entrada errónea.";

    private ArrayList<Integer> lista;
    private int umbral;
    private static Interfaz instance;

    private Interfaz() {
        lista = new ArrayList<Integer>();
        umbral = 0;
    }

    public static Interfaz getInstance() {
        if (instance == null)
            instance = new Interfaz();

        return instance;
    }

    public void initilize() {
        imprimeMenu();
        while (!tratarOpción());
    }

    private static void imprimeMenu() {
        System.out.println("x-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-x");
        System.out.println("| i - Insertar valores          |");
        System.out.println("| r - Insertar valores (rand)   |");
        System.out.println("| u - Insertar umbral           |   _____        ____  ");
        System.out.println("| m - Mostrar vector            |  |  __ \\      |___ \\ ");
        System.out.println("| n - Mostrar umbral            |  | |__) | __    __) |");
        System.out.println("| o - Vector ordenado/umbral    |  |  ___// __|  |__ < ");
        System.out.println("| l - Limpiar vector            |  | |   | |     ___) |");
        System.out.println("|                               |  |_|   |_|    |____/ ");
        System.out.println("| s - Salir                     |                     2013/14");
        System.out.println("x-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-x");
    }

    private boolean tratarOpción() {
        System.out.print("Elige opción: ");
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        char[] opción = new char[1];

        try {
            read.read(opción);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (Character.toUpperCase(opción[0])) {
            case 'I':
                insertarEnLista();
                break;
            case 'R':
                insertarEnListaRand();
                break;
            case 'U':
                insertarUmbral();
                break;
            case 'M':
                muestraVector();
                System.out.println("\nFin del vector.");
                break;
            case 'N':
                System.out.println("\nUmbral: " + umbral);
                break;
            case 'O':
                ordenarVectorUmbral();
                break;
            case 'L':
                lista.clear();
                System.out.println("\nVector limpio.");
                break;
            case 'S':
                System.out.println("\nBye.");
                return true;
            default:
                System.out.println("\nOpción inválida.");
                System.out.println("Elija una de las siguientes opciones:");

        }
        System.out.println("");
        imprimeMenu();
        return false;
    }

    private void insertarEnLista() {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n¿Cuántos números vas a introducir?: ");
        int num = 0;
        try {
            num = Integer.parseInt(read.readLine());
        } catch (NumberFormatException ex) {
            System.out.println(NO_INT_ERROR);
        } catch (IOException e) {
        }

        Integer entero;
        boolean error;
        for (int i = 0; i < num; i++) {
            error = true;
            while (error) {
                System.out.print("Dame un número entero: ");
                try {
                    entero = new Integer(Integer.parseInt(read.readLine()));
                    lista.add(entero);
                    error = false;
                } catch (NumberFormatException ex) {
                    System.out.println(NO_INT_ERROR);
                } catch (IOException e) {
                }
            }
        }
    }

    private void muestraVector() {
        System.out.println("\nImprimiendo vector...\n");
        int i = -1;
        for (Integer integer : lista)
            System.out.println("Vector[" + ++i + "]: " + integer);
    }

    private void insertarUmbral() {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\nValor del umbral: ");
        try {
            umbral = Integer.parseInt(read.readLine());
        } catch (NumberFormatException ex) {
            System.out.println(NO_INT_ERROR);
        } catch (IOException e) {
        }
    }

    private void ordenarVectorUmbral() {
        int[] v = new int[lista.size()];
        int i = 0;

        for (Integer integer : lista)
            v[i++] = integer;

        Busca.mayores(v, 0, v.length - 1, umbral);
        for (i = v.length - 1; i >= 0 && v[i] > umbral; i--);
        Ordena.DivisiónIntercambio(v, ++i, v.length - 1);

        System.out.println("\nSubvector ordenador mayor que el umbral (" + umbral + "):");
        int offset = i;
        for (; i < v.length; i++)
            System.out.println("v[" + (i - offset) + "]: " + v[i]);

    }

    private void insertarEnListaRand() {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n¿Cuántos números aleatorios quieres?: ");
        int num = 0;
        try {
            num = Integer.parseInt(read.readLine());
        } catch (NumberFormatException ex) {
            System.out.println(NO_INT_ERROR);
        } catch (IOException e) {
        }

        System.out.print("¿Límite superior?: ");
        int lsup = 0;
        try {
            lsup = Integer.parseInt(read.readLine());
        } catch (NumberFormatException ex) {
            System.out.println(NO_INT_ERROR);
        } catch (IOException e) {
        }

        System.out.print("¿Límite inferior?: ");
        int linf = 0;
        try {
            linf = Integer.parseInt(read.readLine());
        } catch (NumberFormatException ex) {
            System.out.println(NO_INT_ERROR);
        } catch (IOException e) {
        }

        for (int i = 0; i < num; i++)
            lista.add(new Integer((int) (Math.random() * (lsup - linf + 1) + linf)));
    }
}
