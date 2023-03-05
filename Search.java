import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;

public class Search {
    private List<Block> blocks = new ArrayList<>();

    private Block startBlock;
    private Block goalBlock;
    private Block currentSearchBlock;

    LinkedHashSet searchPath = new LinkedHashSet();
    ArrayList<Block> actualPath = new ArrayList<>();
    ArrayList<Block> frontier = new ArrayList<>();

    Integer size = 0;
    public void initMaze(int size) {
        this.size = size;
        try {
            // Read file
            String fileStr = Files.readString(Path.of("maze/maze" + String.valueOf(size) + ".txt"));
            Integer firstLine=fileStr.indexOf('\n',0);
            fileStr = fileStr.substring(firstLine+1, fileStr.length());
            
            // Place characters into an array
            char[] chars = fileStr.toCharArray();
            int x = 0;
            int y = 0;

            for(char c : chars) {
                // Initialize a block inside the maze
                Block block = new Block(x, y, c);
                // Place blocks in an array
                blocks.add(block);
                // Assign x and y values
                y = y + 1;
                if(c == '\n') {
                    y = 0; // Reset y value to 0 for a new line
                    x = x + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block getBlock(Integer x, Integer y) {
        for(Block block : blocks) {
            if(block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    void search () {
        // PREPROCESSING
        updateFScore(startBlock, 0.0);
        frontier.add(startBlock);
        searchPath.add(startBlock);
        startBlock.isExplored = true;
        currentSearchBlock = getLowestFscoreFromFrontier();
        currentSearchBlock.setGScore(0.0);
        // BEGIN SEARCH
        while(!isEnd()) { // Check for end game conditions

            // Generate all possible actions from currentSearchBlock, update fscore, add to frontier, add to searchPath
            generatePossibleActions();

            // Remove currentSearchBlock from frontier
            frontier.remove(currentSearchBlock);

            // If frontier is empty, maze can not be completed
            if(frontier.size() == 0) {
                System.out.println("Solution cannot be found :(\n");
                return;
            }

            // Update value of currentSearchBlock to be the block in frontier that has the lowest fscore
            currentSearchBlock = getLowestFscoreFromFrontier();
            currentSearchBlock.isExplored = true;

        }
        if(currentSearchBlock == goalBlock) {
            System.out.println("Path Found!");
            reconstructActualPath();
            System.out.println(actualPath);
        }
    }

    Block getLowestFscoreFromFrontier() {
        Block lowest = frontier.get(0);
        for(Block b : frontier) {
            if(b.getfScore() < lowest.getfScore()) {
                lowest = b;
            }
        }
        return lowest;
    }

    void updateFScore(Block b, Double gScore) {
        float h  = calculateDistanceBetweenPoints(b.getX(), b.getY(), goalBlock.getX(), goalBlock.getY());
        b.setfScore(h + gScore);
    }

    public float calculateDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return (float) Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    void generatePossibleActions() {
        int x = currentSearchBlock.getX(); // Col
        int y = currentSearchBlock.getY(); // Row

        // Left
        if(getBlock(x, y-1) != null && y != 0 && !getBlock(x, y-1).isWall() &&  !getBlock(x, y-1).isExplored) {
            getBlock(x, y-1).setGScore(currentSearchBlock.getGScore() + 1);
            updateFScore(getBlock(x, y-1), currentSearchBlock.getgScore() + 1);
            frontier.add(getBlock(x, y-1));
            searchPath.add(getBlock(x, y-1));
            currentSearchBlock.neighbors.add(getBlock(x, y-1));
            getBlock(x, y-1).setPrev(currentSearchBlock);
        }
        // Right
        if(getBlock(x, y+1) != null && y + 1 != size && !getBlock(x, y+1).isWall() &&  !getBlock(x, y+1).isExplored) {
            getBlock(x, y+1).setGScore(currentSearchBlock.getGScore() + 1);
            updateFScore(getBlock(x, y+1), currentSearchBlock.getgScore() + 1);
            frontier.add(getBlock(x, y+1));
            searchPath.add(getBlock(x, y+1));
            currentSearchBlock.neighbors.add(getBlock(x, y+1));
            getBlock(x, y+1).setPrev(currentSearchBlock);
        }

        // Top
        if(getBlock(x-1, y) != null && x != 0 && !getBlock(x-1, y).isWall() &&  !getBlock(x-1, y).isExplored) {
            getBlock(x-1, y).setGScore(currentSearchBlock.getGScore() + 1);
            updateFScore(getBlock(x-1, y), currentSearchBlock.getgScore() + 1);
            frontier.add(getBlock(x-1, y));
            searchPath.add(getBlock(x-1, y));
            currentSearchBlock.neighbors.add(getBlock(x-1, y));
            getBlock(x-1, y).setPrev(currentSearchBlock);
        }

        // Bottom
        if(getBlock(x+1, y) != null && x + 1!= size && !getBlock(x+1, y).isWall() &&  !getBlock(x+1, y).isExplored) {
            getBlock(x+1, y).setGScore(currentSearchBlock.getGScore() + 1);
            updateFScore(getBlock(x+1, y), currentSearchBlock.getgScore() + 1);
            frontier.add(getBlock(x+1, y));
            searchPath.add(getBlock(x+1, y));
            currentSearchBlock.neighbors.add(getBlock(x+1, y));
            getBlock(x+1, y).setPrev(currentSearchBlock);
        }
    }

    boolean isEnd() {
        return currentSearchBlock == goalBlock || frontier.size() == 0;
    }

    public void setStartBlock(Block startBlock) {
        this.startBlock = startBlock;
    }

    public void setGoalBlock(Block goalBlock) {
        this.goalBlock = goalBlock;
    }

    public LinkedHashSet getSearchPath() {
        return searchPath;
    }

    public ArrayList<Block> getActualPath() {
        return actualPath;
    }

    void reconstructActualPath() {
        Block current = this.currentSearchBlock;
        while (current != startBlock) {
            this.actualPath.add(current);
            current = current.prev;
        }

    }
}
