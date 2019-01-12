package Fonctions.Traitement;

import Fonctions.AbstractFonction;
import Types.Streams.StreamSlice;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Slice extends AbstractFonction {
    private static final int nombreArgument = 3;
    private String[] typesArgs = {"Stream", "Rationnel", "Rationnel"};

    public Slice() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamSlice().initialisationStream(args);
    }
}
