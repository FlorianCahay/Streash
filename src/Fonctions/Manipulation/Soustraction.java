package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.Valeurs;

import java.util.ArrayList;

public class Soustraction extends AbstractFonction {

    public Soustraction() {
        super(2);
    }

    /*
        Soustrait deux rationnels : a - b
     */
    @Override
    public Valeurs execution(ArrayList<Valeurs> args) {
        //Valeurs[] arg = super.getListeArgument();
        Valeurs a = args.get(0);
        Valeurs b = args.get(1);
        if (a instanceof Rationnel && b instanceof Rationnel) {
            return Rationnel.sub((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
