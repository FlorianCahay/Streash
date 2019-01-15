package Fonctions.Traitement;

import Fonctions.AbstractFonction;
import Types.Streams.StreamIntersection;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Intersection extends AbstractFonction {
    private static final int nombreArgument = 2;
    private String[] typesArgs = {"Stream", "Stream"};

    public Intersection() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamIntersection().initialisationStream(args);
    }
}
