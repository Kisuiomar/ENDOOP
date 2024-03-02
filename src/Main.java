import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface GameFactory {
    Game createGame(String name, double price);
}
class Game {
    private String name;
    private double price;
    public Game(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
class SimpleGameFactory implements GameFactory {
    public Game createGame(String name, double price) {
        return new Game(name, price);
    }
}
class GameStore {
    private List<Game> games = new ArrayList<>();
    private GameFactory gameFactory;
    public GameStore(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }
    public void addGame(String name, double price) {
        Game game = gameFactory.createGame(name, price);
        games.add(game);
        System.out.println("Game added: " + name);
    }
    public void listGames() {
        System.out.println("Available games:");
        for (Game game : games) {
            System.out.println(game.getName() + " - $" + game.getPrice());
        }
    }
    public void purchaseGame(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                System.out.println("Purchased game: " + game.getName());
                return;
            }
        }
        System.out.println("Game not found: " + name);
    }
}
public class Main {
    public static void main(String[] args) {
        GameFactory gameFactory = new SimpleGameFactory();
        GameStore gameStore = new GameStore(gameFactory);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add game");
            System.out.println("2. List games");
            System.out.println("3. Purchase game");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter game name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter game price: ");
                    double price = scanner.nextDouble();
                    gameStore.addGame(name, price);
                    break;
                case 2:
                    gameStore.listGames();
                    break;
                case 3:
                    System.out.print("Enter game name to purchase: ");
                    String gameName = scanner.nextLine();
                    gameStore.purchaseGame(gameName);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
