package Fonctions;

import Fonctions.Manipulation.Addition;
import Fonctions.Manipulation.Soustraction;
import Fonctions.SourcesStreams.StreamEntier;
import Types.Rationnel;
import Types.Valeurs;

import java.util.ArrayList;

public interface Fonctions {

    public static Fonctions obtenirFonction(String nom) {
        if (nom.equals("<add>")){
            System.out.println("add");
            return new Addition();
        }
        if (nom.equals("<sub>")){
            return new Soustraction();
        }
        return null;
    }

    public Valeurs execution(ArrayList<Valeurs> arg);
}
