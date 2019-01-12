package Fonctions.SourcesStreams;

import Fonctions.AbstractFonction;
import Types.Streams.StreamEntierInfiniInverse;
import Types.TypesDonnees;

import java.util.ArrayList;

public class StreamEntierInverse extends AbstractFonction {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Rationnel"};

    public StreamEntierInverse() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);
        return new StreamEntierInfiniInverse().initialisationStream(args);
    }
}
