package uvg.edu.gt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Este programa lee un archivo que contiene informacion sobre cartas, cada carta tiene un nombre
 * y un tipo. Estos estan separados por |, conteniendo una carta por linea en un archivo llamado
 * "cards_desc.txt". Permite al usuario agregar cartas por nombre, mostrar informacion sobre cartas
 * dentro de la coleccion y la totalidad de cartas existentes. Se permite al usuario elegir que
 * implementacion de la interfaz mapa se desea emplear en el funcionamiento interno del programa.
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 11-03-2024
 */
public class App {
    public static void main(String[] args) {
        //Crea un scanner y un loop para que el usuario pueda elegir una implementacion
        Scanner scanner = new Scanner(System.in);
        boolean firstSelect = true;
        int mainSelect = 0;
        String stringSelect = "";
        while (firstSelect) {
            System.out.println("Ingrese el numero de la implementacion que desea utilizar");
            System.out.println("1. Hashmap");
            System.out.println("2. TreeMap");
            System.out.println("3. LinkedHashMap");
            //Se verifica que la entrada sea un entero, de lo contrario se regresa al inicio del loop
            try {
                mainSelect = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero");
                scanner.nextLine();
                continue;
            }
            //Se crea un switch case, si la opcion no es valida se regresa al inicio del loop
            switch (mainSelect) {
                case 1:
                    stringSelect = "HashMap";
                    break;
                case 2:
                    stringSelect = "TreeMap";
                    break;
                case 3:
                    stringSelect = "LinkedHashMap";
                    break;
                default:
                    System.out.println("Seleccion Invalida");
                    continue;
            }
            //Se sale del loop
            firstSelect = false;
        }
        //Se crea un nuevo CardManager con la implementacion a utilizar y se lee el archivo
        CardManager cardManager = new CardManager(stringSelect);
        cardManager.readFile();
        System.out.println("Cartas Cargadas");
        //Se inicia otro loop para el menu principal
        while (true){
            System.out.println("Ingres el numero correspondiente a la opcion");
            System.out.println("1. Ingresar una carta a la coleccion");
            System.out.println("2. Mostrar el tipo de una carta");
            System.out.println("3. Mostrar las cartas en la coleccion");
            System.out.println("4. Mostrar las cartas en la coleccion por tipo");
            System.out.println("5. Mostrar las cartas disponibles");
            System.out.println("6, Mostrar las cartas disponibles por tipo");
            //Se verifica que la entrada sea un numero entero
            try {
                mainSelect = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero");
                scanner.nextLine();
                continue;
            }
            switch (mainSelect){
                /**
                 * Pide al usuario ingresar el nombre de la carta que se desea agregar
                 * a la coleccion y la agrega
                 */
                case 1:
                    System.out.println("Ingrese el nombre de la carta por agregar");
                    String cardToAdd = scanner.nextLine();
                    cardManager.addCard(cardToAdd);
                    break;
                /**
                 * Se pide al usuario el nombre de la carta de la que desea informacion y
                 * la despliega
                 */
                case 2:
                    System.out.println("Ingrese el nombre de la carta");
                    String cardToSearch = scanner.nextLine();
                    System.out.println(cardManager.getType(cardToSearch));
                    break;
                /**
                 * Muestra las cartas del usuario
                 */
                case 3:
                    cardManager.showUserCards();
                    break;
                /**
                 * Muestra las cartas del usuario
                 */
                case 4:
                    cardManager.mostrarCartasPorTipoUsuario();
                    break;
                case 5:
                    cardManager.showAvailable();
                    break;
                case 6:
                    cardManager.mostrarCartasPorTipoDisponibles();
                default:
                    System.out.println("Seleccion invalida");
                    break;
            }
        }
    }
}
