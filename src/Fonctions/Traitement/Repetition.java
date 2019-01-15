package Fonctions.Traitement;

import Fonctions.AbstractFonction;
import Types.Streams.StreamRepetition;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Repetition extends AbstractFonction {
    private static final int nombreArgument = 2;
    private String[] typesArgs = {"Stream", "Rationnel"};

    public Repetition() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        return new StreamRepetition().initialisationStream(args);
    }
}
