package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamSlice implements StreamType {
    private int debut;
    private int fin;
    private StreamType s;
    private boolean infini;

    public StreamSlice() {

    }

    private StreamSlice(StreamType s, int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
        this.s = s;
        this.infini = false;
    }
    @Override
    public Stream getStream() {
        return s.getStream().skip(debut).limit(fin-debut+1);
    }
    @Override
    public StreamType getObject() {
        return this;
    }

    @Override
    public boolean streamInfini() {
        return infini;
    }

    @Override
    public String affichageDansConsole() {
        return "Slice d'un " + s.affichageDansConsole() + " de l'indice " + String.valueOf(debut) + " jusqu'à l'indice " + String.valueOf(fin);
    }

    @Override
    public String getType() {
        return "StreamSlice";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r;
        Rationnel r2;
        StreamType st;

        // Vérification du type des arguments
        if (args.get(0) instanceof StreamType && args.get(1) instanceof Rationnel && args.get(2) instanceof Rationnel) {
            st = (StreamType) args.get(0);
            r = (Rationnel) args.get(1);
            r2 = (Rationnel) args.get(2);
        } else {
            throw new IllegalArgumentException("Argument dans la liste de mauvais type : " +
                    "recu=" + args.get(0).getType() + ", attendu=StreamType\n " +
                    "recu=" + args.get(1).getType() + ", attendu=Rationnel\n" +
                    "recu=" + args.get(2).getType() + ", attendu=Rationnel");
        }
        // Vérification que le paramètre soit bien un entier
        if (!r.isEntier()) throw new IllegalArgumentException("Le début du slice doit etre un entier");
        if (!r2.isEntier()) throw new IllegalArgumentException("La fin du slice doit etre un entier");

        return new StreamSlice(st, r.getNumerateur().intValue(), r2.getNumerateur().intValue()) ;
    }

    // SKIP + LIMIT  iterate(this.debut, (x) -> x + 1).limit()
}
