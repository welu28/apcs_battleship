public class Ship
{
    // Direction constants
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    // Instance variables
    private int row;
    private int col;
    private int length;
    private int direction;
    
    // Constructor. Create a ship and set the length.
    public Ship(int length)
    {
        this.length = length;
        this.row = UNSET;
        this.col = UNSET;
        this.direction = UNSET;
    }
    
    // Has the location been initialized?
    public boolean isLocationSet()
    {
        return row != UNSET && col != UNSET && direction != UNSET;
    }
    
    // Set the location of the ship 
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    // Getter for the row value
    public int getRow()
    {
        return row;
    }

    // Getter for the column value
    public int getCol()
    {
        return col;
    }

    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }

    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }

    // Helper method to get a string value from the direction
    private String directionToString()
    {
        if(direction == HORIZONTAL)
        {
            return "Horizontal";
        }
        else if(direction == VERTICAL)
        {
            return "Vertical";
        }
        else
        {
            return "Unset";
        }
    }

    // toString value for this Ship
    public String toString()
    {
        return directionToString() + " ship with length " + length +
        " at row " + row + " and column " + col;
    }
}