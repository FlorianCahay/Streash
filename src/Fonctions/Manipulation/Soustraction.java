package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Soustraction extends AbstractFonction {

    public Soustraction() {
        super(2);
    }

    /*
        Soustrait deux rationnels : a - b
     */
    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        if (args.get(0) instanceof Rationnel && args.get(1) instanceof Rationnel) {
            Rationnel a = (Rationnel) args.get(0);
            Rationnel b = (Rationnel) args.get(1);
            return Rationnel.sub((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
