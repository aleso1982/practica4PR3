package p4;

public class Busca {

    public static void mayores(int[] v, int linf, int lsup, int umbral) {
        if (lsup < linf || lsup > v.length || linf < 0) {
            return;
        }
        if (linf == lsup) {
            return;
        }

        int[] p = Divide.división(v, linf, lsup);

        if (v[p[0] - 1] == umbral) {
            return;
        }

        if (v[p[0] - 1] > umbral) {
            mayores(v, linf, p[1], umbral);
        } else {
            mayores(v, p[0], lsup, umbral);
        }
    }
}