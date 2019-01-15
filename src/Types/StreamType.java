package Types;

import java.util.ArrayList;
import java.util.stream.Stream;

public interface StreamType extends TypesDonnees {

    StreamType initialisationStream(ArrayList<TypesDonnees> args);
    StreamType getObject();
    boolean streamInfini();
    Stream getStream();
    StreamType copier();

}
