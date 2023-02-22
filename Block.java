public class Block {
    boolean isWall;
    float gScore; // Cost from start to n
    float hScore; // Heuristic Score
    float fScore; // g + h
    
    int x;
    int y;

    public Block(boolean isWall, int x, int y) {
        this.isWall = isWall;
        this.x = x;
        this.y = y;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public float getgScore() {
        return gScore;
    }

    public void setgScore(float gScore) {
        this.gScore = gScore;
    }

    public float gethScore() {
        return hScore;
    }

    public void sethScore(float hScore) {
        this.hScore = hScore;
    }

    public float getfScore() {
        return fScore;
    }

    public void setfScore(float fScore) {
        this.fScore = fScore;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}