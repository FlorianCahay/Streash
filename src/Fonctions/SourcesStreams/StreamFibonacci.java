package Fonctions.SourcesStreams;

import java.util.stream.Stream;

public class StreamFibonacci {
    private Stream stream;
    //private final int debut;

    public StreamFibonacci(int a, int b) {

        this.stream = Stream.iterate(new long[]{a, b}, p -> new long[]{p[1], p[0]+p[1]});
    }

    public Stream getStream() {
        return stream;
    }

}
