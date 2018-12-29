package Fonctions;

import Types.Valeurs;
import com.sun.jdi.Value;

import java.util.ArrayList;

public abstract class AbstractFonction implements Fonctions {
    private final int nbreArgument;
    private ArrayList<Valeurs> listeArgument;

    public AbstractFonction(int nbreArgument) {
        this.nbreArgument = nbreArgument;
        this.listeArgument = new ArrayList<Valeurs>();
    }

    public void typeArgumentsNonValide() {
        throw new IllegalArgumentException("Les arguments ne sont pas du même type.");
    }

    @Override
    public Valeurs execution(ArrayList<Valeurs> arg) {
        return null;
    }

    public Valeurs[] getListeArgument() {
        Valeurs[] tableau = new Valeurs[nbreArgument];
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = listeArgument.get(i);
        }
        return tableau;
    }
}
