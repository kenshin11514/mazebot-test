import java.util.ArrayList;
import java.lang.Math;
import java.util.LinkedHashSet;

public class Search {
    Block[][] maze;

    Block startBlock;
    Block goalBlock;
    Block currentSearchBlock;

    LinkedHashSet searchPath = new LinkedHashSet();
    ArrayList<Block> actualPath = new ArrayList<>();
    ArrayList<Block> frontier = new ArrayList<>();


    void search () {
        // PREPROCESSING
        startBlock = maze[0][0];
        goalBlock = maze[1][2];
        updateFScore(startBlock, 0);
        frontier.add(startBlock);
        searchPath.add(startBlock);
        actualPath.add(startBlock);
        startBlock.isExplored = true;
        currentSearchBlock = getLowestFscoreFromFrontier();
        printMaze();
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

            // Add to actualPath
            actualPath.add(currentSearchBlock);
            printMaze();
        }

        System.out.println("Path to Goal");
        System.out.println(actualPath);
        System.out.println();

        System.out.println("Path Explored");
        System.out.println(searchPath);
        System.out.println();



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

    void updateFScore(Block b, float gScore) {
        float h  = calculateDistanceBetweenPoints(b.getX(), b.getY(), goalBlock.getX(), goalBlock.getY());
        b.setfScore(h + gScore);
    }

    public float calculateDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    void generatePossibleActions() {
        int x = currentSearchBlock.getX(); // Row
        int y = currentSearchBlock.getY(); // Col

        // Left
        if(y != 0 && !maze[x][y - 1].isWall() &&  !maze[x][y - 1].isExplored) {
            updateFScore(maze[x][y - 1], currentSearchBlock.getgScore() + 1);
            frontier.add(maze[x][y - 1]);
            searchPath.add(maze[x][y - 1]);
        }
        // Right
        // Replace 2 with n size of maze
        if(y != 2 && !maze[x][y + 1].isWall() &&  !maze[x][y + 1].isExplored) {
            updateFScore(maze[x][y + 1], currentSearchBlock.getgScore() + 1);
            frontier.add(maze[x][y + 1]);
            searchPath.add(maze[x][y + 1]);
        }

        // Top
        if(x != 0 && !maze[x - 1][y].isWall() &&  !maze[x - 1][y].isExplored) {
            updateFScore(maze[x - 1][y], currentSearchBlock.getgScore() + 1);
            frontier.add(maze[x - 1][y]);
            searchPath.add(maze[x - 1][y]);
        }

        // Bottom
        // Replace 2 with n size of maze
        if(x != 2 && !maze[x + 1][y].isWall() &&  !maze[x + 1][y].isExplored) {
            updateFScore(maze[x + 1][y], currentSearchBlock.getgScore() + 1);
            frontier.add(maze[x + 1][y]);
            searchPath.add(maze[x + 1][y]);
        }
    }

    boolean isEnd() {
        return currentSearchBlock == goalBlock || frontier.size() == 0;
    }

    void printMaze() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(currentSearchBlock.getX() == i && currentSearchBlock.getY() == j) {
                    System.out.print("B");
                }
                else {
                    if(maze[i][j].isWall) {
                        System.out.print("#");
                    }
                    else {
                        System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public Search(Block[][] maze) {
        this.maze = maze;
    }
}
