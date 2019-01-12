package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.TypesDonnees;

import java.util.ArrayList;

public class Addition extends AbstractFonction {

    public Addition() {
        super(2);
    }

    /*
        Additionne deux rationnels : a + b
     */
    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        if (args.get(0) instanceof Rationnel && args.get(1) instanceof Rationnel) {
            Rationnel a = (Rationnel) args.get(0);
            Rationnel b = (Rationnel) args.get(1);
            return Rationnel.add((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
