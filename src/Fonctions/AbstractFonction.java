package Fonctions;

import Types.StreamType;
import Types.TypesDonnees;
import Types.Valeurs;

import java.util.ArrayList;

public abstract class AbstractFonction implements Fonctions {
    private final int nbreArgument;
    private ArrayList<TypesDonnees> listeArgument;

    public AbstractFonction(int nbreArgument) {
        this.nbreArgument = nbreArgument;
        this.listeArgument = new ArrayList<TypesDonnees>();
    }

    public void typeArgumentsNonValide() {
        throw new IllegalArgumentException("Les arguments ne sont pas du même type.");
    }

    public boolean verifNombreArgument(ArrayList<TypesDonnees> args) {
        if (args.size() < nbreArgument) {
            throw new IllegalArgumentException("Il n'y a pas assez d'argument");
        } else if (args.size() > nbreArgument){
            throw new IllegalArgumentException("Il y a trop d'argument");
        }
        return true;
    }

    /*
        Lève une exception si un argument est de mauvais type
     */
    public void verifTypeArgument(ArrayList<TypesDonnees> args, String[] types) {
        // Initialisation du message d'erreur
        StringBuilder sb = new StringBuilder("Un/Des argument(s) passé(s) en paramètre est/sont de mauvais type :\n");

        for (int i = 0; i < nbreArgument; i++) {
            // Si le type attendu est un stream
            if (types[i].equals("Stream")) {
                if (!args.get(i).getType().startsWith("Stream")) {
                    sb.append("\trecu=" + args.get(i).getType() + " attendu=" + types[i] + ",");
                }
            }
            else if (!args.get(i).getType().equals(types[i])) {
                sb.append("\trecu=" + args.get(i).getType() + " attendu=" + types[i] + ",");
            }
        }
        // Si un argument est du mauvais type on lève une exception
        if (!sb.toString().equals("Un/Des argument(s) passé(s) en paramètre est/sont de mauvais type :\n")) {
            throw new IllegalArgumentException(sb.toString());
        }
    }


}
