import controller.ItemController;
import controller.UserController;
import model.Cart;
import model.Item;
import model.Product;
import model.User;
import utils.Utils;
import view.DataBase;

import java.util.*;

public class Store {

    private static Scanner leitor;
    private static DataBase dataBase;
    private static Map<Item, Integer> carrinhoCompras;
    private static ItemController controllerEstoque;
    private static UserController controllerUser;
    private static int authUser = 0;


    public static void main(String[] args) {

        leitor = new Scanner(System.in);
        dataBase = new DataBase();

        controllerEstoque = new ItemController(dataBase);
        controllerUser = new UserController(dataBase);

        login();
        mainMenu();


    }
    private static void about() {
        System.out.println(" Name: PUCVisionaire Store\n Created by: Lucas Carvalho\n Version: 3");
    }
    public static void relatorio(){
        if(authUser == 0){
            login();
        }else{

            if( authUser == 1){
                System.out.println(" ******* REPORT *******");
                for (User u : controllerUser.findAll()) {
                    System.out.printf(" ******* Client %s *******\n", u.getNome());
                    List<Cart> c = u.getCarrinho();

                    for (Cart cart : c){
                        System.out.println(cart.toString());
                    }
                }
            }else{
                System.out.println("sorry, only admin can access this option");
                mainMenu();
            }

        }
    }


    /*
    *    MÉTODOS DO MÓDULO DE LOGIN
    */
    private static void login() {
        try{
            System.out.println("""
                     want to login?
                    enter 1 for yes or 0 for no""");
            int op = Integer.parseInt(leitor.next());

            if(op == 1){
                auth();
            }
            mainMenu();
        }catch (NullPointerException e){
            System.out.println("Error! please enter a numeric value");
            mainMenu();
        }

    }

    private static void auth(){
        try{

            System.out.println("Enter with CPF");
            String cpf = leitor.next();

            if(Utils.validCPF(cpf)){
                User user =  controllerUser.findByCpf(cpf);

                if(user != null){

                    System.out.println("Enter with PASSWORD");
                    String password = leitor.next();

                    System.out.println(" Authenticating ... ");

                    if(password.equals(user.getSenha())){
                        System.out.println("Welcome "+ user.getNome());
                        authUser = user.getId();

                    }else{

                        System.out.println(" invalid password ");
                        auth();
                    }
                }else{

                    register();
                }

            }
        }catch (NullPointerException e) {
            System.out.println(" Error when logging in");
            mainMenu();
        }
    }

    private static void register(){
        try{
            System.out.println(" Resgistering new User");

            System.out.println("Enter with NAME");
            String name = leitor.next();

            System.out.println("Enter with CPF");
            String cpf = leitor.next();

            System.out.println("Enter with PASSWORD");
            String password = leitor.next();

            if( Utils.cpfValidation(cpf) && Utils.validName(name) && Utils.validPassword(password)){

                System.out.println(" Register ... ");
                User user = new User(name, cpf, password);
                controllerUser.create(user);

                authUser = user.getId();

                shopping();
            }

        }catch (NullPointerException e){
            System.out.println(" Erro to Register a new User");
        }

    }

    public static void mainMenu(){
        System.out.println(" ****************************************************");
        System.out.println(" ******************** MAIN MENU  ********************");
        System.out.println(" 1- SHOP");
        System.out.println(" 2- (ADMIN) REPORT ON CLIENTS ");
        System.out.println(" 3- CHANGE USER");
        System.out.println(" 4- ABOUT");
        System.out.println(" 5- GET OUT");

        try {
            System.out.println(" ***************** SELECT AN OPTION *****************");
            Integer op = Utils.validNumber(leitor.next());

            switch (op) {
                case 1 -> {
                    carrinhoCompras = new HashMap<>();
                    shopping();
                }
                case 2 -> relatorio();
                case 3 -> login();
                case 4 -> about();
                case 5 -> {
                    System.out.println(" We appreciate the preference, come back aften!");
                    System.exit(1);
                }
                default -> {
                    System.out.println(" Option not available in menu");
                    mainMenu();
                }
            }
        }catch (NullPointerException e){
            System.out.println(" Error! please enter a numeric value");
        }

    }

    /*
    *   MÉTODOS DO MÓDULO DE COMPRAS
    */
    public static void shopping(){

        if(authUser == 0){
            System.out.println(" To make purchases you must login");
            login();
        }else{

            Item item = productsMenu();
            if(item == null){
                shopping();
            }else {
                System.out.println(" Enter the quantity of");
                Integer qtd = Utils.validNumber(leitor.next());

                //Item itemEstoque = dataBase.getEstoque().get(item.getId());

                if(item.getQtd() >= qtd) {
                    try {
                        carrinhoCompras.put(item, carrinhoCompras.get(item.getId()) + qtd);
                    }catch (NullPointerException e){
                        carrinhoCompras.put(item, qtd);
                    }

                    System.out.printf("%d %s Add to cart\n",qtd, item.getProduct().getNome());
                    item.setQtd(qtd);
                    dataBase.getEstoque().put(item.getId(), item);

                }else {
                    System.out.println("Sorry, but we do not have the quantity of this product in stock.");
                }

                System.out.println("""
                         want to add a new product to the cart?
                        enter 1 for yes or 0 for no""");

                int op = Utils.validNumber(leitor.next());
                if(op == 1){
                    shopping();
                }else if(op == 0) {
                    System.out.println("finish shop");
                    finishPurchase();
                }else{
                    System.out.println("Error! please enter a numeric value");
                }

            }
        }
    }

    public static Item productsMenu(){

        System.out.println(" ************************************************");
        System.out.println(" **************** PRODUCTS MENU  ****************\n");

        for(Item item : controllerEstoque.findAll()){
            System.out.println(item.toString());
        }

        System.out.println("\n***************** SELECT AN OPTION *****************\n");
        Integer op = Utils.validNumber(leitor.next());
        return controllerEstoque.findById(op);
    }

    public static void finishPurchase(){
        double valorCompra = 0;

        System.out.println(" ************************************************");
        System.out.println(" *************** PRODUCTS IN CART ***************\n");

        if(carrinhoCompras.size() > 0){
            List<Product> p = new ArrayList<>();

            for(Map.Entry<Item, Integer> item : carrinhoCompras.entrySet()){
                Item i = item.getKey();
                double valor = (i.getProduct().getValor() * item.getValue());
                valorCompra += valor;

                System.out.println("" + i.getProduct().toString()
                        + " X " + item.getValue()
                        + " " + Utils.doubleToString(valor) + "");

                p.add(i.getProduct());
            }

            System.out.println("\n total amount to be paid *************** " + Utils.doubleToString(valorCompra));
            System.out.println(" ************************************************");

            dataBase.getUsers().get(authUser).setCarrinho(
                    new Cart(p, valorCompra ));

        }else{
            System.out.println(" cart is empty");
        }

        mainMenu();

    }

}
