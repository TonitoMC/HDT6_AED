package uvg.edu.gt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Esta clase se encarga de la funcionalidad interna del programa, almacena la informacion de las cartas
 * disponibles y las cartas dentro de la coleccion del usuario dentro de la implementacion Map indicada.
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 11-03-2024
 */
public class CardManager {
    private MapFactory<String, String> factory = new MapFactory<String, String>();
    private Map<String, String> availableCards;
    private Map<String, String> userCards;
    /**
     * Crea un nuevo CardManager al igual que el Map de cartas disponibles y dentro de la coleccion del usuario
     * @param mapType
     */
    public CardManager(String mapType){
        availableCards = factory.newMap(mapType);
        userCards = factory.newMap(mapType);
    }
    /**
     * Agrega una carta a la coleccion del usuario
     * @param cardName el nombre de la carta por agregar
     */
    public void addCard(String cardName) {
        //Se verifica que la carta se encuentre dentro de las disponibles
        if (availableCards.containsKey(cardName)){
            //Busca el tipo de carta dentro del Map y agrega la carta a la colecciond el usuario
            String cardType = availableCards.get(cardName);
            userCards.put(cardName, cardType);
            System.out.println("Agregada " + cardName + " tipo: " + cardType);
        } else{
            System.out.println("La carta " + cardName + "no se encuentra en las cartas disponibls");
        }
    }

    /**
     * Retorna el tipo de una carta en especifico
     * @param cardName el nombre de la carta
     * @return el tipo de la carta
     */
    public String getType(String cardName){
        return availableCards.get(cardName);
    }

    /**
     * Despliega las cartas del usuario
     */
    public void showUserCards(){
        //Toma el set de keys del map y para cada key imprime el nombre y el tipo
        Set<String> keySet = userCards.keySet();
        for (String key : keySet){
            System.out.println("Nombre: " + key + "Tipo: " + userCards.get(key));
        }
    }

    /**
     * Muestra las cartas disponibles leidas del archivo txt
     */
    public void showAvailable(){
        //Toma el set de keys del map y para cada key imprime el nombre y el tipo
        Set<String> keySet = availableCards.keySet();
        for (String key : keySet){
            System.out.println("Nombre: " + key + "Tipo: " + availableCards.get(key));
        }
    }

    /**
     * Lee el archivo txt y almacena la informacion dentro de availableCards
     */
    public void readFile(){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("cards_desc.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("\\|");
                availableCards.put(arrOfStr[0], arrOfStr[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Muestra el nombre de las cartas ordenados por tipo
     */
    public  void mostrarCartasPorTipo() {
        Map<String, List<String>> cartasPorTipo = new HashMap<>();
        
        // Agrupar las cartas por tipo
        for (Map.Entry<String, String> entry : this.userCards.entrySet()) {
            String nombreCarta = entry.getKey();
            String tipoCarta = entry.getValue();
            
            // Si ya existe la lista para este tipo, agregamos el nombre de la carta a la lista existente
            if (cartasPorTipo.containsKey(tipoCarta)) {
                cartasPorTipo.get(tipoCarta).add(nombreCarta);
            } else {
                // Si es la primera carta de este tipo, creamos una nueva lista y agregamos el nombre de la carta
                List<String> listaCartas = new ArrayList<>();
                listaCartas.add(nombreCarta);
                cartasPorTipo.put(tipoCarta, listaCartas);
            }
        }
        
        // Mostrar las cartas agrupadas por tipo
        for (Map.Entry<String, List<String>> entry : cartasPorTipo.entrySet()) {
            String tipoCarta = entry.getKey();
            List<String> cartasTipo = entry.getValue();
            
            System.out.println("Tipo de Carta: " + tipoCarta);
            System.out.println("Nombres de Cartas:");
            for (String nombreCarta : cartasTipo) {
                System.out.println(nombreCarta);
            }
            System.out.println();
        }
    }
    /*
     * Muestra la cantidad de cartas ordenadas por tipo
     */
    public void mostrarCantidadDeCartas() {
        Map<String, Integer> conteoCartas = new TreeMap<>();
        
        // Recorrer todas las cartas
        for (Map.Entry<String, String> entry : this.userCards.entrySet()) {
            String nombreCarta = entry.getKey();
            String tipoCarta = entry.getValue();
            
            // Si ya hemos contado este tipo de carta, incrementamos su cantidad
            if (conteoCartas.containsKey(tipoCarta)) {
                conteoCartas.put(tipoCarta, conteoCartas.get(tipoCarta) + 1);
            } else {
                // Si es la primera vez que encontramos este tipo de carta, inicializamos su cantidad a 1
                conteoCartas.put(tipoCarta, 1);
            }
        }
        
        // Mostrar el resultado
        for (Map.Entry<String, Integer> entry : conteoCartas.entrySet()) {
            System.out.println("Tipo: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }

}
