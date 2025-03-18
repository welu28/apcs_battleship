public class Player
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private Ship[] ships;
    private Grid player;
    private Grid opponent;
    private int numShips;
    private int[] lastGuess;
    
    public Player()
    {
        ships = new Ship[SHIP_LENGTHS.length];
        player = new Grid();
        opponent = new Grid();
        addShips();
        numShips = 0;
    }
    
    public Grid getGrid()
    {
        return player;
    }
    
    private void addShips()
    {
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
    }
    
    public void printUserShips()
    {
        player.printShips();
    }
    
    public void printUserGuessed()
    {
        player.printStatus();
    }
    
    public void printComputerGuessed()
    {
        opponent.printStatus();
    }
    
    public void recordGuess(int row, int col)
    {
        if(player.hasShip(col, row))
        {
            player.markHit(col, row);
        }
        else
        {
            player.markMiss(col, row);
        }
        lastGuess[0] = col;
        lastGuess[1] = row;
    }
    
    public boolean validShip(int row, int col, int direction)
    {
        return player.validPlacement(row, col, direction, SHIP_LENGTHS[numShips]);
    }
    
    public void chooseShipLocation(int row, int col, int direction)
    {
        Ship s = ships[numShips];
        s.setDirection(direction);
        s.setLocation(col, row);
        player.addShip(s);
        numShips++;
    }
    
    public boolean guessed(int row, int col)
    {
        Location curr = opponent.get(col, row);
        return !curr.isUnguessed();
    }
    
    public boolean win()
    {
        return player.hasWon();
    }
    
    public int[] getLastGuess()
    {
        return lastGuess;
    }
    
    public boolean lastGuessWasHit()
    {
        return lastGuess[0] != -1 && lastGuess[1] != -1 && player.hasShip(lastGuess[0], lastGuess[1]);
    }
}