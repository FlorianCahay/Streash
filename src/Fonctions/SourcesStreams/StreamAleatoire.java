package Fonctions.SourcesStreams;

import Types.Rationnel;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.Stream;

public class StreamAleatoire {
    private long seed;
    private final long mini;
    private final long maxi;
    private Stream stream;

    public StreamAleatoire(long a, long b, long seed) {
        // Verifier si seed == -1
        // et si a > b
        this.mini = a;
        this.maxi = b;
        this.seed = seed;
        if (seed == -1) {
            this.seed = new Random().nextLong();
        }
        this.stream = new Random(this.seed).ints((int) mini, (int) maxi).mapToObj(x -> new Rationnel(BigInteger.valueOf(x)));
    }
}
