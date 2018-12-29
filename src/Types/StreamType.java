// EN COURS DE MODIF

package Types;

import java.util.stream.Stream;

public class StreamType<Valeurs> {
    private String typeValeur;
    private Valeurs val;
    private Stream contenu;

    public StreamType() {
        this.typeValeur = null;
        this.val = null;
    }

    public StreamType(Valeurs val) {
        this.val = val;
        if (val instanceof Rationnel) {
            this.typeValeur = ((Rationnel) val).getType();
        }
    }

    /*
        Retourne un Stream d'entiers de debut jusqu'à plus l'infini
     */
    public static Stream integers(int debut) {
        //return new StreamType<Rationnel>();
        return Stream.iterate(debut, (x) -> x+1).limit(100);
    }

    /*
        Retourne un Stream d'entiers de debut jusqu'à moins l'infini
     */
    public Stream revintegers(int debut) {
        return Stream.iterate(debut, (x) -> x-1);
    }

    /*
        Retourne un Stream d'entiers qui correspond à la suite de Ficonacci
        avec a et b comme premiers termes
     */
    public Stream fibo(int a, int b) {
        return Stream.iterate(new long[]{a, b}, p -> new long[]{p[1], p[0]+p[1]});
    }

//    public Stream random(int a, int b, long seed) {
//
//    }

}
