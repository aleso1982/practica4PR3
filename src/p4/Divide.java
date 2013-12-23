package p4;

public class Divide {

    public static int[] división(int[] v, int linf, int lsup) {
        int piv = (int) (Math.random() * (lsup - linf + 1) + linf);
        int pini = linf;
        int pfin = lsup;

        while (pini <= pfin) {

            while (pini <= lsup && v[pini] <= v[piv]) {
                pini++;
            }
            while (pfin >= linf && v[pfin] >= v[piv]) {
                pfin--;
            }

            if (pini < pfin) {
                int aux = v[pini];
                v[pini] = v[pfin];
                v[pfin] = aux;
                pini++;
                pfin--;
            }
        }

        if (pini < piv) {
            int aux = v[pini];
            v[pini] = v[piv];
            v[piv] = aux;
            pini++;
        } else {
            if (pfin >= piv) {
                int aux = v[piv];
                v[piv] = v[pfin];
                v[pfin] = aux;
                pfin--;
            }
        }

        return new int[]{pini, pfin};
    }
}
