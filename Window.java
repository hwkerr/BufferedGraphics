import javax.swing.*;
import java.awt.*;
/**
 * The actual JFrame
 * For most programs, no code needs to be added in this class
 * When running a program, this is the class from which you start
 */
public class Window extends JFrame
{
    public Window(String name)
    {
        super(name);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension windowSize = new Dimension(800, 600);//Set the dimensions of your window here!
        setSize(windowSize);
    }
    public static void main(String[] args)
    {
        Window window = new Window("My Program");//Set the window header/title of your program here
        
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        
        Driver d = new Driver(window.getSize());
        contentPane.add(d);
        
        window.setVisible(true);
    }
}
