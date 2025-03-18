public class Grid
{
    private Location[][] grid;
    // Direction constants
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;

    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    // Create a new Grid. Initialize each Location in the grid
    // to be a new Location object.
    public Grid()
    {
        grid = new Location[NUM_COLS][NUM_ROWS];
        for(int i = 0; i < NUM_COLS; i++) {
            for(int j = 0; j < NUM_ROWS; j++) {
                grid[i][j] = new Location();
            }
        }
    }
    
    public boolean validPlacement(int row, int col, int direction, int length) {
        if (direction == VERTICAL && row + length > NUM_ROWS) {
            return false;
        }
        else if (direction == HORIZONTAL && col + length > NUM_COLS) {
            return false;
        }
    
        // Check the proposed positions for the ship
        for (int i = 0; i < length; i++) {
            if (direction == VERTICAL && grid[row + i][col].hasShip()) {
                return false;
            }
            else if (direction == HORIZONTAL && grid[row][col + i].hasShip()) {
                return false;
            }
        }
        return true;
    }
    
    // Mark a hit in this location by calling the markHit method
    // on the Location object.  
    public void markHit(int row, int col)
    {
        Location curr = grid[col][row];
        curr.markHit();
    }
    
    // Mark a miss on this location.    
    public void markMiss(int row, int col)
    {
        Location curr = grid[col][row];
        curr.markMiss();
    }
    
    // Set the status of this location object.
    public void setStatus(int row, int col, int status)
    {
        Location curr = grid[col][row];
        curr.setStatus(status);
    }
    
    // Get the status of this location in the grid  
    public int getStatus(int row, int col)
    {
        Location curr = grid[col][row];
        return curr.getStatus();
    }
    
    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col) 
    {
        Location curr = grid[col][row];
        return !curr.isUnguessed();
    }
    
    // Set whether or not there is a ship at this location to the val   
    public void setShip(int row, int col, boolean val)
    {
        Location curr = grid[col][row];
        curr.setShip(val);
    }
    
    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col)
    {
        Location curr = grid[col][row];
        return curr.hasShip();
    }
    
    
    // Get the Location object at this row and column position
    public Location get(int row, int col)
    {
        Location curr = grid[col][row];
        return curr;
    }
    
    // Return the number of rows in the Grid
    public int numRows()
    {
        return NUM_ROWS;
    }
    
    // Return the number of columns in the grid
    public int numCols()
    {
        return NUM_COLS;
    }
    
    // Print the Grid status including a header at the top
    // that shows the columns 1-10 as well as letters across
    // the side for A-J
    // If there is no guess print a -
    // If it was a miss print a O
    // If it was a hit, print an X
    // A sample print out would look something like this:
    // 
    //   1 2 3 4 5 6 7 8 9 10 
    // A - - - - - - - - - - 
    // B - - - - - - - - - - 
    // C - - - O - - - - - - 
    // D - O - - - - - - - - 
    // E - X - - - - - - - - 
    // F - X - - - - - - - - 
    // G - X - - - - - - - - 
    // H - O - - - - - - - - 
    // I - - - - - - - - - - 
    // J - - - - - - - - - - 
    public void printStatus()
    {
        // Print the column labels
        System.out.print("  ");
        for(int i = 1; i <= NUM_COLS; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Initialize an array for the letter of each row
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; 
        
        // Print each row and at the start the letter
        for(int i = 0; i < NUM_ROWS; i++)
        {
            System.out.print(letters[i] + " ");
            for(int j = 0; j < NUM_COLS; j++)
            {
                Location curr = grid[j][i];
                if(curr.isUnguessed())
                {
                    System.out.print("- ");
                }
                else if(curr.checkHit())
                {
                    System.out.print("X ");
                }
                else if(curr.checkMiss())
                {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }
    
    public boolean hasWon()
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(grid[j][i].hasShip() && !grid[i][j].checkHit())
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Print the grid and whether there is a ship at each location.
    // If there is no ship, you will print a - and if there is a
    // ship you will print a X. You can find out if there was a ship
    // by calling the hasShip method.
    //
    //   1 2 3 4 5 6 7 8 9 10 
    // A - - - - - - - - - - 
    // B - X - - - - - - - - 
    // C - X - - - - - - - - 
    // D - - - - - - - - - - 
    // E X X X - - - - - - - 
    // F - - - - - - - - - - 
    // G - - - - - - - - - - 
    // H - - - X X X X - X - 
    // I - - - - - - - - X - 
    // J - - - - - - - - X - 
    public void printShips()
    {
        // Print the column labels
        System.out.print("  ");
        for(int i = 1; i <= NUM_COLS; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Initialize an array for the letter of each row
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; 
        
        // Print each row and at the start the letter
        for(int i = 0; i < NUM_ROWS; i++)
        {
            System.out.print(letters[i] + " ");
            for(int j = 0; j < NUM_COLS; j++)
            {
                Location curr = grid[j][i];
                if(curr.hasShip())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    
    public void addShip(Ship s)
    {
        for(int i = 0; i < s.getLength(); i++)
        {
            if(s.getDirection() == VERTICAL && s.getRow() + s.getLength() <= NUM_ROWS)
            {
                grid[s.getRow()][s.getCol() + i].setShip(true);
            }
            else if(s.getDirection() == HORIZONTAL && s.getCol() + s.getLength() <= NUM_COLS)
            {
                grid[s.getRow() + i][s.getCol()].setShip(true);
            }
        }
    }
}