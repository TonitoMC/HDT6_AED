package uvg.edu.gt;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * Esta clase es un Factory que crea diferentes implementaciones de la interfaz Map
 * @param <T> el tipo de valor de la llave
 * @param <E> el tipo de valor del value
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 11-03-2024
 */
public class MapFactory<T, E> {
    /**
     * Este metodo crea un nuevo Map con la implementacion indicada
     * @param type la implementacion que se desea utilizar
     * @return un nuevo Map con la implementacion indicada
     */
    public Map<T, E> newMap(String type) {
        switch (type) {
            case "HashMap":
                return new HashMap<T, E>();
            case "TreeMap":
                return new TreeMap<T, E>();
            case "LinkedHashMap":
                return new LinkedHashMap<T, E>();
            default:
                throw new IllegalArgumentException("Tipo de mapa no implementado: " + type);
        }
    }
}
