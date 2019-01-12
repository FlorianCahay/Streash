package Fonctions.SourcesStreams;

import Fonctions.AbstractFonction;
import Types.Streams.StreamNombresFibonacci;
import Types.TypesDonnees;

import java.util.ArrayList;

public class StreamFibonacci extends AbstractFonction {
    private static final int nombreArgument = 3;
    private String[] typesArgs = {"Rationnel", "Rationnel", "Rationnel"};

    public StreamFibonacci() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamNombresFibonacci().initialisationStream(args);
    }
}
