package Console;

import Fonctions.Fonctions;
import Types.Rationnel;
import Types.Valeurs;

import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class Console {
    private Scanner scanner;
    private String[] arguments;
    private Deque<String> pile;
    private SortedMap<String, Entry<Valeurs, Deque<String>>> variables;

    public Console() {
        this.scanner = new Scanner(System.in);
        this.pile = new ArrayDeque<>();
        this.variables = new TreeMap<String, Entry<Valeurs, Deque<String>>>();
    }

    public Deque<String> getPile() {
        return pile;
    }

    public void boucle() {
        while(true) {
            if (scanner.hasNextLine()) {
                pile = new ArrayDeque<>();
                String ligne = scanner.nextLine();

                this.arguments = ligne.split(" ");
                if (arguments[0].contains("=")) {
                    System.out.println("Merci de mettre des espaces autour du égal");
                    continue;
                }

                if (ligne.startsWith("/")) {
                    // Exécution d'une méta-instruction
                    analyseMetaInstruction();
                }
                else if (ligne.contains("=")) {
                    // Enregistrement de la ligne avec affectation d'une variable définie
                    String resultat = constructionPile();
                    ajoutVariable(resultat);
                }
                else {
                    // Enregistrement de la ligne avec affectation d'une variable temporaire
                    String resultat = constructionPileTemporaire();
                    ajoutVariableTemporaire(resultat);
                }
            }
        }
    }



    public void ajoutVariable(String resultat) {
        if (resultat != null) {
            variables.put(arguments[0], new SimpleEntry(new Rationnel(new BigInteger(resultat)), pile));
        } else {
            variables.put(arguments[0], new SimpleEntry(new Rationnel(BigInteger.valueOf(1)), pile));
        }
    }

    public void ajoutVariableTemporaire(String resultat) {
        int i = 1;
        String nomVariable = "";
        while(true) {
            if (variables.containsKey("tmp"+i)) {
                i++;
                continue;
            }
            else {
                nomVariable = "tmp"+i;
                break;
            }
        }
        if (resultat != null) {
            variables.put(nomVariable, new SimpleEntry(new Rationnel(new BigInteger(resultat)), pile));
        } else {
            variables.put(arguments[0], new SimpleEntry(new Rationnel(BigInteger.valueOf(1)), pile));
        }
    }

    public String constructionPile() {
        for (String action : arguments) {
            // On n'insère pas le nom de la variable ni le = dans la pile
            if (action.equals("=") || action.equals(arguments[0])) {
                continue;
            }
            pile.addLast(action);
        }
        return arguments.length == 3 ? arguments[2] : null;
    }

    public String constructionPileTemporaire() {
        for (String action : arguments) {
            pile.addLast(action);
        }
        return arguments.length == 1 ? arguments[0] : null;
    }

    public void executionPile(Deque<String> pileAExecuter, String nomVariable) {
        ArrayList<Valeurs> arg = new ArrayList<Valeurs>();

        /////////////////////////////////////////////////////////////////////////////
        // TROUVER UN MOYEN POUR COPIER LA PILE ET L'INSERER DANS variables EN BAS
        // SANS LA MODIFIER
        // Pour pouvoir l'afficher plusieurs fois
        ////////////////////////////////////////////////////////////////////////////

        Deque<String> copie_pile = pileAExecuter;

        // Tant qu'il y a des éléments dans la pile d'instruction
        while (copie_pile.size() > 0) {
            String element = copie_pile.removeFirst();
            // Si c'est un nombre on l'ajoute à la liste d'arguments
            if (element.matches("\\d+")) {
                arg.add(new Rationnel(new BigInteger(element)));
            }
            // Si c'est un appel à une fonction
            else if (element.matches("<.*>")) {
                // On récupère la fonction qui correspond à l'élément
                Fonctions fonction_correspondante = Fonctions.obtenirFonction(element);
                // On exécute la fonction avec les arguments
                Valeurs x = fonction_correspondante.execution(arg);
                // On affiche le contenu de la variable dans la console
                System.out.println(nomVariable + " = " + x.affichageDansConsole());
                // On met à jour la valeur dans la map
                variables.replace(nomVariable, new SimpleEntry(new Rationnel(BigInteger.valueOf(1)), pileAExecuter), new SimpleEntry(x, pileAExecuter));
            }
        }
    }

    public void analyseMetaInstruction() {
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
            }
            else {
                System.out.println("Nombre d'argument incorrect");
            }

        }
        else if (arguments[0].equals("/printvar")) {
            if (arguments.length == 2) {
                System.out.println("Affichage d'une variable");
                afficheUneVariable(arguments[1]);
            }
            else {
                System.out.println("Nombre d'argument incorrect");
            }
        }
    }

    public void afficheUneVariable(String variable) {
        if (variables.containsKey(variable)) {
            Entry<Valeurs, Deque<String>> contenuVariable = variables.get(variable);

            // Si la variable contient seulement un rationnel
            if (contenuVariable.getValue().size() == 1 && contenuVariable.getKey() instanceof Rationnel) {
                System.out.println(variable + " = " + ((Rationnel) contenuVariable.getKey()).affichageDansConsole());
            }
            // Si la variable contient une opération ou un stream
            else {
                executionPile(contenuVariable.getValue(), variable);
            }
        }
    }


}
