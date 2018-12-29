package Fonctions.SourcesStreams;

import java.util.stream.Stream;

public class StreamEntierInverse {
    private Stream stream;
    private final int debut;

    public StreamEntierInverse(int debut) {
        this.debut = debut;
        this.stream = Stream.iterate(debut, (x) -> x-1);
    }

    public Stream getStream() {
        return stream;
    }
}
