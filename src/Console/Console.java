package Console;

import Fonctions.Fonctions;
import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class Console {
    private Scanner scanner;
    private String[] arguments;
    private Deque<String> pile;
    private SortedMap<String, Entry<TypesDonnees, Deque<String>>> variables;

    public Console() {
        this.scanner = new Scanner(System.in);
        this.pile = new ArrayDeque<>();
        this.variables = new TreeMap<String, Entry<TypesDonnees, Deque<String>>>();
    }

    /*
        Boucle principale du programme, la boucle lit la console
     */
    public void boucle() {
        while(true) {
            try {
                if (scanner.hasNextLine()) {
                    pile = new ArrayDeque<>();
                    String ligne = scanner.nextLine();

                    this.arguments = ligne.split(" ");
                    if (arguments[0].contains("=")) {
                        throw new IllegalArgumentException("Merci de mettre des espaces autour du égal");
                    }

                    if (ligne.startsWith("/")) {
                        // Exécution d'une méta-instruction
                        analyseMetaInstruction();
                    } else if (ligne.contains("=")) {
                        // Enregistrement de la ligne avec affectation d'une variable définie
                        String resultat = constructionPile();
                        String v = ajoutVariable(resultat);
                        afficheUneVariable(v);
                    } else {
                        // Enregistrement de la ligne avec affectation d'une variable temporaire
                        String resultat = constructionPileTemporaire();
                        String v = ajoutVariableTemporaire(resultat);
                        afficheUneVariable(v);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
        Ajoute dans la map des variables une nouvelle variable définie par un nom
     */
    private String ajoutVariable(String resultat) {
        Rationnel r = new Rationnel(BigInteger.valueOf(1));
        if (resultat != null) {
            if (resultat.matches("-?\\d+/-?\\d+")) {
                String[] resultat_split = resultat.split("/");
                r = new Rationnel(new BigInteger(resultat_split[0]), new BigInteger(resultat_split[1]));
            } else {
                r = new Rationnel(new BigInteger(resultat));
            }
            variables.put(arguments[0], new SimpleEntry(r, pile));
        } else {
            variables.put(arguments[0], new SimpleEntry(r, pile));
        }
        return arguments[0];
    }
    /*
        Ajoute dans la map des variables une nouvelle variable temporaire
     */
    private String ajoutVariableTemporaire(String resultat) {
        int i = 1;
        String nomVariable = "";
        while(true) {
            if (variables.containsKey("tmp"+i)) {
                i++;
            }
            else {
                nomVariable = "tmp"+i;
                break;
            }
        }
        Rationnel r = new Rationnel(BigInteger.valueOf(1));
        if (resultat != null) {
            if (resultat.matches("-?\\d+/-?\\d+")) {
                String[] resultat_split = resultat.split("/");
                r = new Rationnel(new BigInteger(resultat_split[0]), new BigInteger(resultat_split[1]));
            } else {
                r = new Rationnel(new BigInteger(resultat));
            }
            variables.put(nomVariable, new SimpleEntry(r, pile));
        } else {
            variables.put(nomVariable, new SimpleEntry(r, pile));
        }
        return nomVariable;
    }

    /*
        Construit une pile d'instructions avec la ligne rentrée par l'utilisateur
        Renvoie la valeur de la variable si elle ne contient qu'un rationnel
     */
    private String constructionPile() {
        for (String action : arguments) {
            // On n'insère pas le nom de la variable ni le = dans la pile
            if (action.equals("=") || action.equals(arguments[0])) {
                continue;
            }
            pile.addLast(action);
        }
        // Si la variable contient seulement un rationnel on renvoit la valeur
        return arguments.length == 3 ? arguments[2] : null;
    }

    /*
        Construit une pile d'instructions avec la ligne rentrée par l'utilisateur
        Renvoie la valeur de la variable si elle ne contient qu'un rationnel
     */
    private String constructionPileTemporaire() {
        for (String action : arguments) {
            pile.addLast(action);
        }
        // Si la variable contient seulement un rationnel on renvoit la valeur
        return arguments.length == 1 ? arguments[0] : null;
    }

    /*
        Exécute les instructions d'une pile et l'affiche
     */
    private void executionPile(Deque<String> pileAExecuter, String nomVariable) {
        // Liste des arguments pour les fonctions
        ArrayList<TypesDonnees> arg = new ArrayList<TypesDonnees>();
        ArrayList liste_copie = new ArrayList(pileAExecuter);

        // Tant qu'il y a des éléments dans la pile d'instruction
        while (pileAExecuter.size() > 0) {
            String element = pileAExecuter.removeFirst();
            // Si c'est un nombre on l'ajoute à la liste d'arguments
            if (element.matches("-?\\d+")) {
                arg.add(new Rationnel(new BigInteger(element)));
            }
            else if (element.matches("-?\\d+/-?\\d+")) {
                String[] resultat_split = element.split("/");
                arg.add(new Rationnel(new BigInteger(resultat_split[0]), new BigInteger(resultat_split[1])));
            }
            // Si on fait appel à une variable déjà enregistrée
            else if (variables.containsKey(element)) {
                Entry<TypesDonnees, Deque<String>> contenuVariable = variables.get(element);
                // On ajoute la valeur de la variable à la liste des arguments
                arg.add(contenuVariable.getKey());
            }
            // Si c'est un appel à une fonction
            else if (element.matches("<.*>")) {
                // On récupère la fonction qui correspond à l'élément
                Fonctions fonction_correspondante = Fonctions.obtenirFonction(element);

                // On exécute la fonction avec les arguments
                TypesDonnees x = fonction_correspondante.execution(arg);
                if (!element.matches("<print>")) {
                    if (pileAExecuter.size() < 1) {
                        // On affiche le contenu de la variable dans la console
                        System.out.println(nomVariable + " = " + x.affichageDansConsole());
                    }
                    // On met à jour la valeur dans la map
                    variables.replace(nomVariable, new SimpleEntry(x, new ArrayDeque(liste_copie)));

                    // On réinitialise la liste des arguments et on ajoute le résultat de la fonction
                    arg = new ArrayList<TypesDonnees>();
                    arg.add(x);
                }
                else {
                    System.out.println("Nombre d'éléments affichés : " + x.affichageDansConsole());
                }
            }
        }
    }

    /*
        Analyse la meta-instruction tapé par l'utilisateur et appelle la méthode qui correspondante
     */
    private void analyseMetaInstruction() {
        if (arguments[0].equals("/quit") && arguments.length <= 1) {
            System.exit(0);
        }
        else if (arguments[0].equals("/printvars")) {
            if (arguments.length <= 1) {
                System.out.println("Affichage de toutes les variables");
                for (String variable : variables.keySet()) {
                    afficheUneVariable(variable);
                }
            }
            else if (arguments.length == 2 && arguments[1].equals("alpha")) {
                System.out.println("Affichage de toutes les variables par ordre lexicographique de leur nom");
                for (String variable : variables.keySet()) {
                    afficheUneVariable(variable);
                }
            }
            else {
                throw new IllegalArgumentException("Nombre d'argument incorrect");
            }

        }
        else if (arguments[0].equals("/printvar")) {
            if (arguments.length == 2) {
                afficheUneVariable(arguments[1]);
            }
            else {
                throw new IllegalArgumentException("Nombre d'argument incorrect");
            }
        }
    }

    /*
        Affiche le contenu d'une variable, si c'est un rationnel l'affiche directement sinon
        on exécute la pile d'instruction qui correspond à la variable pour obtenir une valeur
     */
    private void afficheUneVariable(String variable) {
        if (variables.containsKey(variable)) {
            Entry<TypesDonnees, Deque<String>> contenuVariable = variables.get(variable);

            // Si la pile a déjà été exécuté on récupère la valeur

            if (contenuVariable.getValue().size() == 1 && contenuVariable.getKey() instanceof Rationnel) {
                // Si la variable contient seulement un rationnel
                System.out.println(variable + " = " + ((Rationnel) contenuVariable.getKey()).affichageDansConsole());
            }
            else if (contenuVariable.getKey() instanceof StreamType) {
                // Si la variable contient un stream
                System.out.println(variable + " = " + ((StreamType) contenuVariable.getKey()).affichageDansConsole());
            }
            // Si la variable contient une opération
            else {
                executionPile(contenuVariable.getValue(), variable);
            }
        }
        else {
            throw new IllegalArgumentException("Variable non existante");
        }
    }


}
