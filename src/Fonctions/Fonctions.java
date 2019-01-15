package Fonctions;

import Fonctions.Manipulation.*;
import Fonctions.Puits.*;
import Fonctions.SourcesStreams.*;
import Fonctions.Traitement.*;
import Types.TypesDonnees;

import java.util.ArrayList;

public interface Fonctions {

    static Fonctions obtenirFonction(String nom) {
        // Manipulations
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
        // Sources
        if (nom.equals("<integers>")) {
            return new StreamEntier();
        }
        if (nom.equals("<revintegers>")) {
            return new StreamEntierInverse();
        }
        if (nom.equals("<fibo>")) {
            return new StreamFibonacci();
        }
        if (nom.equals("<random>")) {
            return new StreamAleatoire();
        }
        // Traitements
        if (nom.equals("<slice>")) {
            return new Slice();
        }
        if (nom.equals("<repeat>")) {
            return new Repetition();
        }
        if (nom.equals("<concat>")) {
            return new Concatenation();
        }
        if (nom.equals("<inter>")) {
            return new Intersection();
        }
        if (nom.equals("<sorted>")) {
            return new Trier();
        }
        if (nom.equals("<shuffle>")) {
            return new Melanger();
        }
        // Puits
        if (nom.equals("<len>")) {
            return new Longueur();
        }
        if (nom.equals("<sum>")) {
            return new Somme();
        }
        if (nom.equals("<product>")) {
            return new Produit();
        }
        if (nom.equals("<average>")) {
            return new Moyenne();
        }
        if (nom.equals("<max>")) {
            return new Maximum();
        }
        if (nom.equals("<min>")) {
            return new Minimum();
        }
        if (nom.equals("<get>")) {
            return new ObtenirElement();
        }
        if (nom.equals("<print>")) {
            return new Affichage();
        }
        throw new IllegalArgumentException("Nom de fonction non valide");
    }

    TypesDonnees execution(ArrayList<TypesDonnees> args);
}
