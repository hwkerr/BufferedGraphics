import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Driver extends Canvas implements Runnable
{
    private Thread t;
    private Graphics bufferGraphics = null; //The graphics for the backbuffer
    private BufferStrategy bufferStrategy = null; //The strategy the app uses
    private boolean running;
    private int x, y;
    public Driver(Dimension size)
    {
        t = new Thread(this);
        running = true;
        //Declarations go here
        x = 0;
        y = 0;
        this.setPreferredSize(size);
    }
    
    public void run()//This puts the thread to work by giving it actions to carry out
    {
        while(running)
        {
            DoLogic();
            Draw();
            DrawBackbufferToScreen();
            
            Thread.currentThread();
            try{
                t.sleep(30);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void DoLogic()
    {
        //Calculations of the program
        x++;
        y++;
    }
    
    public void Draw()//this is the only method where you actually add code in order to draw things
    {
        //Clear 2nd screen
        bufferGraphics = bufferStrategy.getDrawGraphics();
        try{
            bufferGraphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
            //This is where everything will be drawn to the backbuffer
            Graphics2D bg2d = (Graphics2D)bufferGraphics; //bg2d stands for bufferGraphics2D
            
            //Drawing methods!
            bg2d.setColor(Color.BLUE);
            bg2d.fillRect(x, y, 50, 50);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            bufferGraphics.dispose();
        }
    }
    
    public void DrawBackbufferToScreen()
    {
        bufferStrategy.show();
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void paint(Graphics g)
    {
        if(bufferStrategy == null)
        {
            this.createBufferStrategy(2);
            bufferStrategy = this.getBufferStrategy();
            bufferGraphics = bufferStrategy.getDrawGraphics();
            
            this.t.start();
        }
        Graphics2D g2 = (Graphics2D)g;
    }
}
