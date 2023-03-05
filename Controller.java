import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Controller {
    private View view;

    public static void main(String[] args) {
        new Controller();
    }

    public Controller() {
        this.view = new View(new ActionListener() {
            @Override  
            public void actionPerformed(ActionEvent e) {
                // Open up maze frame when start button is clicked
                openMaze();
            }
        });
        view.viewSearchPath();
        view.viewActualPath();
    }

    public void openMaze() {
        view.disposeStart();
        view.MazeView();
        view.getS().search();

    }
}
 