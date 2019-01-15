package Types.Streams;

import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamConcatenation implements StreamType {
    private StreamType s1;
    private StreamType s2;
    private boolean infini;
    private Stream st = null;

    public StreamConcatenation() {

    }

    private StreamConcatenation(StreamType s1, StreamType s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.infini = false;
        if (s1.streamInfini() || s2.streamInfini()) {
            this.infini = true;
        }
    }

    @Override
    public StreamType copier() {
        return new StreamConcatenation(this.s1, this.s2);
    }

    @Override
    public Stream getStream() {
        if (st == null) {
            st = Stream.concat(s1.copier().getStream(), s2.copier().getStream());
        }
        return st;
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
        return "Concaténation d'un " + s1.affichageDansConsole() + " et d'un " + s2.affichageDansConsole();
    }

    @Override
    public String getType() {
        return "StreamConcatenation";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        StreamType st1 = (StreamType) args.get(0);
        StreamType st2 = (StreamType) args.get(1);

        return new StreamConcatenation(st1,st2);
    }
}
