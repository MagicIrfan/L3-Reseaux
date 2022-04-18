package sendable;

import java.io.*;
import java.util.*;
//INTERFACE REPRESENTANT L'OBJET ENVOYE PAR LE CLIENT
public interface Sendable extends Serializable {
   String getName();
   String toString();
   String getSender();
}
