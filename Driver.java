public class Driver {
    public static void main(String[] args) {
        // Generate Maze
        Block[][] maze = new Block[3][3];
        maze[0][0] = new Block(false, 0, 0);
        maze[0][1] = new Block(false, 0, 1);
        maze[0][2] = new Block(false, 0, 2);

        maze[1][0] = new Block(false, 1, 0);
        maze[1][1] = new Block(true, 1, 1);
        maze[1][2] = new Block(false, 1, 2);

        maze[2][0] = new Block(false, 2, 0);
        maze[2][1] = new Block(false, 2, 1);
        maze[2][2] = new Block(false, 2, 2);


        // Run Search Algo
        Search s = new Search(maze);
        s.search();

        System.out.print("Search Finished!");
    }
}
