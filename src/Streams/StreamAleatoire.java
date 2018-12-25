package Streams;

public class StreamAleatoire {
    private final long seed;
    private final long mini;
    private final long maxi;

    public StreamAleatoire(long a, long b, long seed) {
        // Verifier si seed == -1
        // et si a > b
        this.mini = a;
        this.maxi = b;
        this.seed = seed;
    }
}
