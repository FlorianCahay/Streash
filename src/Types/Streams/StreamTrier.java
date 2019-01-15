package Types.Streams;

import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamTrier implements StreamType {
    private boolean infini;
    private StreamType st;
    private Stream s = null;

    public StreamTrier() {

    }

    private StreamTrier(StreamType st) {
        this.st = st;
        this.infini = false;
        if (st.streamInfini()) {
            this.infini = true;
        }
    }

    @Override
    public StreamType copier() {
        return new StreamTrier(this.st);
    }

    @Override
    public Stream getStream() {
        if (s == null) {
            s = st.copier().getStream().sorted();
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
        return "(Stream trié)" + this.st.affichageDansConsole();
    }

    @Override
    public String getType() {
        return "StreamTrier";
    }

    @Override
    public StreamType initialisationStream(ArrayList<TypesDonnees> args) {
        StreamType st = (StreamType) args.get(0);
        return new StreamTrier(st);
    }
}
