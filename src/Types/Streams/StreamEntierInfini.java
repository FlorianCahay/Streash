package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamEntierInfini implements StreamType {
    private int debut;
    private boolean infini;
    private Stream s = null;

    public StreamEntierInfini() {

    }

    private StreamEntierInfini(int debut) {
        this.debut = debut;
        this.infini = true;
    }

    @Override
    public StreamType copier() {
        return new StreamEntierInfini(this.debut);
    }

    @Override
    public Stream getStream() {
        if (s == null) {
            s = Stream.iterate(debut, n -> n + 1).map(i -> new Rationnel(BigInteger.valueOf(i)));
        }
        return s;
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
        return "Stream d'entiers de " + String.valueOf(debut) + " jusqu'à plus l'infini";
    }

    @Override
    public String getType() {
        return "StreamEntierInfini";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r = (Rationnel) args.get(0);

        // Vérification que le paramètre soit bien un entier
        if (!r.isEntier()) throw new IllegalArgumentException("Le début du stream doit etre un entier");

        return new StreamEntierInfini(r.getNumerateur().intValue());
    }
}
