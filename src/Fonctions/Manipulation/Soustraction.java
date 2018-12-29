package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.Valeurs;

public class Soustraction extends AbstractFonction {

    public Soustraction() {
        super(2);
    }
    /*
        Soustrait deux rationnels : a - b
     */
    public Valeurs sub(Valeurs a, Valeurs b) {
        if (a instanceof Rationnel && b instanceof Rationnel) {
            return Rationnel.sub((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
