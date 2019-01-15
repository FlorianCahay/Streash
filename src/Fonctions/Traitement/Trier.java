package Fonctions.Traitement;

import Fonctions.AbstractFonction;
import Types.Streams.StreamTrier;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Trier  extends AbstractFonction {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Stream"};

    public Trier() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamTrier().initialisationStream(args);
    }
}
