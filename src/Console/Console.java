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

    public void boucle() {
        while(true) {
            if (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();

                if (ligne.contains(" ") || ligne.startsWith("/")) {
                    this.arguments = ligne.split(" ");
                } else {
                    System.out.println("Merci de mettre des espaces autour du égal");
                    continue;
                }

                if (ligne.startsWith("/")) {
                    // Exécution d'une méta-instruction
                    analyseMetaInstruction();
                }
                else if (ligne.contains("=")) {
                    // Enregistrement de la ligne avec affectation d'une variable définie
                    constructionPile();
                    ajoutVariable();
                }
                else {
                    // Enregistrement de la ligne avec affectation d'une variable temporaire
                    constructionPile();
                }
            }
        }
    }

    public void ajoutVariable() {
        variables.put(arguments[0], new SimpleEntry(new Rationnel(BigInteger.valueOf(1)), pile));
    }

    public void constructionPile() {
        for (String action : arguments) {
            // On n'insère pas le nom de la variable ni le = dans la pile
            if (action.equals("=") || action.equals(arguments[0])) {
                continue;
            }
            pile.addLast(action);
        }
    }

    public void executionPile(Deque<String> pile) {
        ArrayList<Valeurs> arg = new ArrayList<Valeurs>();

        // Tant qu'il y a des éléments dans la pile d'instruction
        while (pile.size() > 0) {
            String element = pile.removeFirst();
            // Si c'est un nombre on l'ajoute à la liste d'arguments
            if (element.matches("\\d+")) {
                arg.add(new Rationnel(new BigInteger(element)));
            }
            else if (element.matches("<.*>")) {
                // On récupère la fonction qui correspond à l'élément
                Fonctions fonction_correspondante = Fonctions.obtenirFonction(element);
                Valeurs x = fonction_correspondante.execution(arg);
                System.out.println(x.affichageDansConsole());
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

            // Si la variable contient un rationnel
            if (contenuVariable.getValue().size() == 1 && contenuVariable.getKey() instanceof Rationnel) {
                System.out.println(((Rationnel) contenuVariable.getKey()).affichageDansConsole());
                System.out.println(contenuVariable.getValue());
            }
            // Si la variable contient un stream
            else {
                executionPile(contenuVariable.getValue());
            }
        }
    }


}
