package p4;

public class Ordena {

    public static void DivisiónIntercambio(int v[], int linf, int lsup) {
        if (lsup - linf + 1 < 3) {
            if (lsup - linf + 1 == 2) {
                if (v[lsup] < v[linf]) {
                    int aux = v[lsup];
                    v[lsup] = v[linf];
                    v[linf] = aux;
                }
            }
        } else {
            int[] p = Divide.división(v, linf, lsup);
            DivisiónIntercambio(v, linf, p[1]);
            DivisiónIntercambio(v, p[0], lsup);
        }
    }
}