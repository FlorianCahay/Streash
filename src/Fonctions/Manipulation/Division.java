package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.Valeurs;

public class Division extends AbstractFonction {

    public Division() {
        super(2);
    }
    /*
        Divise deux rationnels : a / b
     */
    public Valeurs div(Valeurs a, Valeurs b) {
        if (a instanceof Rationnel && b instanceof Rationnel) {
            return Rationnel.div((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
