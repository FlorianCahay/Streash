package Fonctions.Puits;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Maximum extends AbstractFonction {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Stream"};

    public Maximum() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        Stream s = constructionStream((StreamType) args.get(0));
        Optional a = s.max(Comparator.naturalOrder());

        if (!a.isPresent()) {
            return new Rationnel(0);
        }

        return (Rationnel)a.get();
    }

    private Stream constructionStream(StreamType st) {
        if (st.streamInfini()) {
            throw new IllegalArgumentException("Le stream est infini, impossible de connaître son maximum");
        }
        // Récupération de l'objet
        StreamType x = st.getObject();

        return x.copier().getStream();
    }
}
