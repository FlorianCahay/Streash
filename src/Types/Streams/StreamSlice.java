package Types.Streams;

import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamSlice implements StreamType {
    private int debut;
    private int fin;
    private StreamType st;
    private boolean infini;
    private Stream s = null;

    public StreamSlice() {

    }

    private StreamSlice(StreamType st, int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
        this.st = st;
        this.infini = false;
    }

    @Override
    public StreamType copier() {
        return new StreamSlice(this.st, this.debut, this.fin);
    }
    @Override
    public Stream getStream() {
        if (s == null) {
            s = st.copier().getStream().skip(debut).limit(fin-debut+1);
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
        return "(Slice)" + st.affichageDansConsole() + " de l'indice " + String.valueOf(debut) + " jusqu'à l'indice " + String.valueOf(fin);
    }

    @Override
    public String getType() {
        return "StreamSlice";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        Rationnel r = (Rationnel) args.get(1);
        Rationnel r2 = (Rationnel) args.get(2);
        StreamType st = (StreamType) args.get(0);

        // Vérification que les paramètres sont bien des entiers
        if (!r.isEntier()) throw new IllegalArgumentException("Le début du slice doit etre un entier");
        if (!r2.isEntier()) throw new IllegalArgumentException("La fin du slice doit etre un entier");

        return new StreamSlice(st, r.getNumerateur().intValue(), r2.getNumerateur().intValue()) ;
    }
}
