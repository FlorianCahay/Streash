package Fonctions;

import Fonctions.Manipulation.Addition;
import Fonctions.Manipulation.Division;
import Fonctions.Manipulation.Multiplication;
import Fonctions.Manipulation.Soustraction;
import Fonctions.SourcesStreams.StreamEntier;
import Types.Rationnel;
import Types.Valeurs;

import java.util.ArrayList;

public interface Fonctions {

    public static Fonctions obtenirFonction(String nom) {
        if (nom.equals("<add>")){
            return new Addition();
        }
        if (nom.equals("<sub>")){
            return new Soustraction();
        }
        if (nom.equals("<mul>")){
            return new Multiplication();
        }
        if (nom.equals("<div>")){
            return new Division();
        }
        throw new IllegalArgumentException("Nom de fonction non valide");
    }

    public Valeurs execution(ArrayList<Valeurs> arg);
}
