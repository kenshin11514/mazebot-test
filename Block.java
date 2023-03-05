import java.util.ArrayList;

/*
 * Initializes values of a block.
 */
public class Block {
    
    Integer x, y;
    Boolean isWall = false;
    Boolean isStart = false;
    Boolean isGoal = false;
    Boolean isPath = false;
    Boolean isExplored = false;

    Double gScore; // Cost from start to n
    Double hScore; // Heuristic score
    Double fScore; // g() + h()

    ArrayList<Block> neighbors = new ArrayList<Block>();
    Block prev;

    public Block(Integer x, Integer y, char c) {
        this.x = x;
        this.y = y;

        if(c == '#')
            this.isWall = true;

        if(c == 'S')
            this.isStart = true;

        if(c == 'G')
            this.isGoal = true;

        if(c == '.')
            this.isPath = true;
    }

    public Double getGScore() {
        return gScore;
    }

    public void setGScore(Double gScore) {
        this.gScore = gScore;
    }


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(this.y) +", "+ String.valueOf(this.x) +"]";
    }

    // Getters and Setters
    public Boolean isWall() {

        return isWall;
    }

    public Double getgScore() {
        return gScore;
    }

    public Double getfScore() {
        return fScore;
    }

    public void setfScore(Double fScore) {
        this.fScore = fScore;
    }

    public void setPrev(Block prev) {
        this.prev = prev;
    }
}
