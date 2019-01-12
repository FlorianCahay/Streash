package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamNombresFibonacci implements StreamType {
    private int premier;
    private int deuxieme;
    private boolean infini;

    public StreamNombresFibonacci() {

    }

    private StreamNombresFibonacci(int premier, int deuxieme) {
        this.premier = premier;
        this.deuxieme = deuxieme;
        this.infini = true;
    }

    @Override
    public Stream getStream() {
        return Stream.iterate(new long[]{premier, deuxieme}, p -> new long[]{p[1], p[0]+p[1]});
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
        return "Stream des nombres de Fibonacci avec " + String.valueOf(premier) + " et " + String.valueOf(deuxieme) + " comme deux premiers termes.";
    }

    @Override
    public String getType() {
        return "StreamNombresFibonacci";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r1 = (Rationnel) args.get(0);
        Rationnel r2 = (Rationnel) args.get(1);

        // Vérification que les paramètres sont bien des entiers
        if (!r1.isEntier()) throw new IllegalArgumentException("Le premier terme du stream doit etre un entier");
        if (!r2.isEntier()) throw new IllegalArgumentException("Le deuxieme terme du stream doit etre un entier");

        return new StreamNombresFibonacci(r1.getNumerateur().intValue(), r2.getNumerateur().intValue());
    }
}
