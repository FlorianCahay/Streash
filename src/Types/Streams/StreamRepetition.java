package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamRepetition implements StreamType {
    private StreamType st;
    private Rationnel r;
    private boolean infini;
    private Stream s = null;

    public StreamRepetition() {

    }

    private StreamRepetition(StreamType st, Rationnel r) {
        this.st = st;
        this.r = r;
        this.infini = false;
        if (st.streamInfini()) {
            this.infini = true;
        }
    }

    @Override
    public StreamType copier() {
        return new StreamRepetition(this.st, this.r);
    }

    @Override
    public Stream getStream() {
        if (s == null) {
            float multiplicateur = Rationnel.calculValeurApprochee(r);
            double nombreArrondi = (double)Math.round(multiplicateur*100)/100;
            int valeurApresVirgule = (int) (nombreArrondi * st.copier().getStream().count());
            if (nombreArrondi >= 1) s = st.copier().getStream();
            else s = Stream.empty();
            // Répétition du stream entierement
            for (int i = 1; i < r.getNumerateur().intValue(); i++) {
                s = Stream.concat(s, st.copier().getStream());
            }
            if (!r.isEntier()) {
                // Quand le paramètre n'est pas un entier on rajoute
                s = Stream.concat(s, st.copier().getStream().limit(valeurApresVirgule));
            }
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
        return "Répétition d'un " + st.affichageDansConsole() + " : " + r.affichageDansConsole() + " fois";
    }

    @Override
    public String getType() {
        return "StreamRepetition";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        StreamType st1 = (StreamType) args.get(0);
        Rationnel r = (Rationnel) args.get(1);

        return new StreamRepetition(st1, r);
    }
}
