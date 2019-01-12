package Fonctions.SourcesStreams;

import Fonctions.AbstractFonction;
import Types.Streams.StreamEntierInfini;
import Types.TypesDonnees;

import java.util.ArrayList;

public class StreamEntier extends AbstractFonction  {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Rationnel"};

    public StreamEntier() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);
        return new StreamEntierInfini().initialisationStream(args);
    }

}
