import java.util.*;

public class Battleship {
    private Player user;
    private Player computer;
    private Grid userGrid;
    private Grid comGrid;
    private Random random;
    private Scanner scanner;
    int[] shipLengths = {2, 3, 3, 4, 5};
    ArrayList<String> columns = new ArrayList<>();
    
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    public Battleship() {
        user = new Player();
        computer = new Player();
        userGrid = user.getGrid();
        comGrid = computer.getGrid();
        initializeCols();
        random = new Random();
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        userPlaceShips();
        comPlaceShips();
        user.printUserShips();
        while (!user.win() && !computer.win()) {
            System.out.println("Current guesses: ");
            user.printUserGuessed();
            guess();
            if (user.win()) {
                System.out.println("User wins!");
                break;
            }
            compGuessAlgorithm();
            if (computer.win()) {
                System.out.println("You lost :(");
                break;
            }
            System.out.println("Computer's guesses: ");
            computer.printUserGuessed();
            System.out.println("Your ships: ");
            user.printUserShips();
        }
    }
    
    private void initializeCols() {
        for (char col = 'A'; col <= 'J'; col++) {
            columns.add(String.valueOf(col));
        }
    }
    
    private void userPlaceShips() {
        for (int length : shipLengths) {
            while (true) {
                System.out.println("You will now put a ship of length " + length);
                System.out.print("Place the column (A-J): ");
                String theCol = scanner.next().toUpperCase();
                System.out.print("Place the row (1-10): ");
                int row = scanner.nextInt() - 1;
                int col = columns.indexOf(theCol);
                System.out.print("Choose the direction (h/v): ");
                String theDir = scanner.next().toUpperCase();
                int direction = theDir.equals("V") ? 1 : 0;
                if (row < 0 || row >= 10 || col < 0 || col >= 10 || !user.validShip(col, row, direction) || !userGrid.validPlacement(row, col, direction, length)) {
                    System.out.println("Invalid.");
                    continue;
                }
                user.chooseShipLocation(col, row, direction);
                break;
            }
            user.printUserShips();
        }
    }
    
    private void comPlaceShips() {
        for (int length : shipLengths) {
            while (true) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int direction = random.nextInt(2);
                if (computer.validShip(col, row, direction)) {
                    computer.chooseShipLocation(col, row, direction);
                    break;
                }
            }
        }
    }
    
    private void guess() {
        System.out.print("Guess the column (A-J): ");
        String theCol = scanner.next().toUpperCase();
        int col = columns.indexOf(theCol);
        System.out.print("Guess the row (1-10): ");
        int row = scanner.nextInt() - 1;
        if (userGrid.alreadyGuessed(col, row)) {
            System.out.println("Invalid.");
            return;
        }
        if (comGrid.hasShip(col, row)) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss :(");
        }
        computer.recordGuess(col, row);
    }
    
    private void compGuessAlgorithm() {
        int col, row;
        if (computer.lastGuessWasHit()) {
            int[] lastGuess = computer.getLastGuess();
            col = lastGuess[0] + random.nextInt(3) - 1;
            row = lastGuess[1] + random.nextInt(3) - 1;
        } else {
            col = random.nextInt(10);
            row = random.nextInt(10);
        }
        if (comGrid.alreadyGuessed(col, row) || col < 0 || row < 0) {
            guess();
        } else if (userGrid.hasShip(col, row)) {
            System.out.println("Computer has hit!");
        } else {
            System.out.println("Computer miss :(");
        }
    }
}