package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamEntierInfiniInverse implements StreamType {
    private int debut;
    private boolean infini;

    public StreamEntierInfiniInverse() {

    }

    private StreamEntierInfiniInverse(int debut) {
        this.debut = debut;
        this.infini = true;
    }

    @Override
    public Stream getStream() {
        return Stream.iterate(debut, n -> n - 1).map(i -> new Rationnel(BigInteger.valueOf(i)));
    }

    @Override
    public boolean streamInfini() {
        return infini;
    }

    @Override
    public StreamType getObject() {
        return this;
    }

    @Override
    public String affichageDansConsole() {
        return "Stream d'entiers de " + String.valueOf(debut) + " jusqu'à moins l'infini.";
    }

    @Override
    public String getType() {
        return "StreamEntierInfiniInverse";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r = (Rationnel) args.get(0);

        // Vérification que le paramètre soit bien un entier
        if (!r.isEntier()) throw new IllegalArgumentException("Le début du stream doit etre un entier");

        return new StreamEntierInfiniInverse(r.getNumerateur().intValue());
    }
}
