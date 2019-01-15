package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMelange implements StreamType {
    private long seed;
    private StreamType st;
    private boolean infini;
    private Stream s = null;

    public StreamMelange() {

    }

    private StreamMelange(StreamType st, Rationnel seed) {
        this.st = st;
        this.seed = seed.getNumerateur().longValue();
        if (seed.getNumerateur().longValue() == -1) {
            this.seed = new Random().nextLong();
        }
        this.infini = false;
        if (st.streamInfini()) {
            this.infini = true;
        }
    }

    @Override
    public StreamType copier() {
        return new StreamMelange(this.st, new Rationnel(this.seed));
    }

    @Override
    public Stream getStream() {
        if (s == null) {
            ArrayList<TypesDonnees> liste = (ArrayList)st.copier().getStream().collect(Collectors.toList());
            Collections.shuffle(liste);
            s = liste.stream();
        }
        return s;
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
        return "(StreamMelange)" + st.affichageDansConsole() + " (seed=" + String.valueOf(seed) + ")";
    }

    @Override
    public String getType() {
        return "StreamMelange";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        StreamType st = (StreamType) args.get(0);
        Rationnel r = (Rationnel) args.get(1);

        // Vérification que le paramètre soit bien un entier
        if (!r.isEntier()) throw new IllegalArgumentException("Le seed du random doit etre un entier");

        return new StreamMelange(st, r) ;
    }
}
