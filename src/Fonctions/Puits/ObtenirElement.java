package Fonctions.Puits;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObtenirElement extends AbstractFonction {
    private static final int nombreArgument = 2;
    private String[] typesArgs = {"Stream", "Rationnel"};

    public ObtenirElement() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        StreamType st = constructionStream((StreamType) args.get(0));
        Rationnel argument = (Rationnel) args.get(1);
        int position = argument.getNumerateur().intValue();

        // Si l'index renseigné n'est pas hors limite
        if (st.copier().getStream().count() > argument.getNumerateur().intValue()) {
            ArrayList<TypesDonnees> toto = (ArrayList)st.copier().getStream().collect(Collectors.toList());
            return toto.get(position);
        }
        throw new IllegalArgumentException("L'index renseigné est hors limite");
    }

    private StreamType constructionStream(StreamType st) {
        if (st.streamInfini()) {
            throw new IllegalArgumentException("Le stream est infini");
        }
        // Récupération de l'objet
        StreamType x = st.getObject();

        return x.copier();
    }
}
