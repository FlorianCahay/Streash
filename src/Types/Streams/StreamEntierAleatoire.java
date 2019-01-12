package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class StreamEntierAleatoire implements StreamType {
    private long seed;
    private long mini;
    private long maxi;
    private boolean infini;

    public StreamEntierAleatoire() {

    }

    private StreamEntierAleatoire(Rationnel a, Rationnel b, Rationnel seed) {
        this.mini = a.getNumerateur().longValue();
        this.maxi = b.getNumerateur().longValue();
        this.seed = seed.getNumerateur().longValue();
        if (seed.getNumerateur().longValue() == -1) {
            this.seed = new Random().nextLong();
        }
        this.infini = true;
    }

    @Override
    public Stream getStream() {
        return new Random(this.seed).ints((int) mini, (int) maxi).mapToObj(x -> new Rationnel(BigInteger.valueOf(x)));
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
        return "Stream d'entiers pseudo-aléatoires compris entre " + String.valueOf(mini) + " et " + String.valueOf(maxi) + " (seed=" + String.valueOf(seed) + ")";
    }

    @Override
    public String getType() {
        return "StreamEntierAleatoire";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r1 = (Rationnel) args.get(0);
        Rationnel r2 = (Rationnel) args.get(1);
        Rationnel r3 = (Rationnel) args.get(2);

        // Vérification que les paramètres sont bien des entiers
        if (!r1.isEntier()) throw new IllegalArgumentException("Le début du stream doit etre un entier");
        if (!r2.isEntier()) throw new IllegalArgumentException("La fin du stream doit etre un entier");
        if (!r3.isEntier()) throw new IllegalArgumentException("Le seed du random doit etre un entier");

        return new StreamEntierAleatoire(r1, r2, r3);
    }
}
