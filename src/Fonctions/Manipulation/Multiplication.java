package Fonctions.Manipulation;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.Valeurs;

public class Multiplication extends AbstractFonction {

    public Multiplication() {
        super(2);
    }
    /*
        Multiplie deux rationnels : a * b
     */
    public Valeurs mul(Valeurs a, Valeurs b) {
        if (a instanceof Rationnel && b instanceof Rationnel) {
            return Rationnel.mul((Rationnel) a,(Rationnel) b);
        }
        else {
            typeArgumentsNonValide();
            return null;
        }
    }
}
