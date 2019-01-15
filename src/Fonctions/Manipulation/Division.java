package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Division extends AbstractFonction {
    private static final int nombreArgument = 2;

    public Division() {
        super(nombreArgument);
    }

    /*
        Divise deux rationnels : a / b
     */
    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        verifNombreArgument(args);
        if (args.get(0) instanceof Rationnel && args.get(1) instanceof Rationnel) {
            Rationnel a = (Rationnel) args.get(0);
            Rationnel b = (Rationnel) args.get(1);
            return Rationnel.div((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
