package Fonctions.Traitement;

import Fonctions.AbstractFonction;
import Types.Streams.StreamConcatenation;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Concatenation extends AbstractFonction {
    private static final int nombreArgument = 2;
    private String[] typesArgs = {"Stream", "Stream"};

    public Concatenation() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamConcatenation().initialisationStream(args);
    }
}
