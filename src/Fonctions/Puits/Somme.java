package Fonctions.Puits;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Somme extends AbstractFonction {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Stream"};

    public Somme() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        Stream s = constructionStream((StreamType) args.get(0));
        Rationnel somme = (Rationnel) s.map(x -> (Rationnel)x).reduce(new Rationnel(0), (x, y) -> Rationnel.add((Rationnel)x, (Rationnel)y));

        return somme;
    }

    private Stream constructionStream(StreamType st) {
        if (st.streamInfini()) {
            throw new IllegalArgumentException("Le stream est infini, impossible de calculer la somme de ses elements");
        }
        // Récupération de l'objet
        StreamType x = st.getObject();

        return x.copier().getStream();
    }
}
