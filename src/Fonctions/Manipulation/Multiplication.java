package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.Valeurs;

import java.util.ArrayList;

public class Multiplication extends AbstractFonction {

    public Multiplication() {
        super(2);
    }

    /*
        Multiplie deux rationnels : a * b
     */
    @Override
    public Valeurs execution(ArrayList<Valeurs> args) {
        //Valeurs[] arg = super.getListeArgument();
        Valeurs a = args.get(0);
        Valeurs b = args.get(1);
        if (a instanceof Rationnel && b instanceof Rationnel) {
            return Rationnel.mul((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
