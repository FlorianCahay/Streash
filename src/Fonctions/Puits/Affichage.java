package Fonctions.Puits;

import Fonctions.AbstractFonction;
import Types.Rationnel;
import Types.StreamType;
import Types.TypesDonnees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Affichage extends AbstractFonction {
    private static final int nombreArgument = 1;
    private String[] typesArgs = {"Stream"};

    public Affichage() {
        super(nombreArgument);
    }

    @Override
    public TypesDonnees execution(ArrayList<TypesDonnees> args) {
        // Vérification du nombre et du type des arguments
        verifNombreArgument(args);
        verifTypeArgument(args, typesArgs);

        StreamType st = constructionStream((StreamType) args.get(0));
        Stream s = st.getStream();
        s.forEach(System.out::println);
        s = st.copier().getStream();
        return new Rationnel(s.count());
    }

    private StreamType constructionStream(StreamType st) {
        if (st.streamInfini()) {
            throw new IllegalArgumentException("Le stream est infini, impossible de connaître sa taille");
        }
        // Récupération de l'objet
        StreamType x = st.getObject();

        return x.copier();
    }
}
