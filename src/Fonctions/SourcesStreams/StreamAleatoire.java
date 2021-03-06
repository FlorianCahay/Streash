package Fonctions.SourcesStreams;

import Fonctions.AbstractFonction;
import Types.Streams.StreamEntierAleatoire;
import Types.TypesDonnees;

import java.util.ArrayList;

public class StreamAleatoire extends AbstractFonction {
    private static final int nombreArgument = 3;
    private String[] typesArgs = {"Rationnel", "Rationnel", "Rationnel"};

    public StreamAleatoire() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamEntierAleatoire().initialisationStream(args);
    }
}
