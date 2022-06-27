// 201811002
// zehra ece akdemir
package edu.utexas.se.swing.sample;

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 


	final public class AnimateExample extends JFrame
	{
		private static final long serialVersionUID = 1L;
		JFrame frame;
	    DrawPanel drawPanel;
	    private JPanel controlPanel;
	    
	    private int MAX_SIZE = 72;
	    private int MIN_SIZE = 24;
	    private int size = 24;
	    private int oneX = 85;
	    private int oneY = 75;
        private int x[] = { 10, 30, 40, 50, 110, 140 };
        private int y[] = { 140, 110, 50, 40, 30, 10 };
        private int numberofpoints = 6;
        private int c = 0;
        private int x_origin = 0;
		private int y_origin = 0;
	    
	    private boolean started = true;
	    private Color shapeColor = Color.BLACK;
	    private Color[] colorArray = { Color.YELLOW, Color.ORANGE, Color.RED,
	    		Color.MAGENTA, Color.PINK, Color.BLUE, Color.CYAN, Color.GREEN
	    };


	    public static void main(String... args)
	    {
	        new AnimateExample().go();
	    }

	    private void go()
	    {
	    	
	        frame = new JFrame("Animate");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        drawPanel = new DrawPanel();
	        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
	        frame.setResizable(false);
	        frame.setSize(900, 900);
	        frame.setLocationByPlatform(true);
	        frame.setLocationRelativeTo(null);  
	        frame.setVisible(true);	    	
	        moveIt();

	    }
	    
	    private void rotate(int degree)
	    {
	    int i;
	    float t,v;
	    double radian = degree * 3.14 / 180;
	    for(i=0;i<6;i++)
		    {
			     t=x[i]-x_origin;
			     v=y[i]-y_origin;
			     x[i]=(int)(x_origin+t*Math.cos(radian)-v*Math.sin(radian));
			     y[i]=(int)(y_origin+v*Math.cos(radian)+t*Math.sin(radian));
			}
	    	t=oneX-x_origin;
	    	v=oneY-y_origin;
	    	oneX = (int)(x_origin+t*Math.cos(radian)-v*Math.sin(radian));
	    	oneY =(int)(y_origin+v*Math.cos(radian)+t*Math.sin(radian));
	    }

	    class DrawPanel extends JPanel
	    {
	        private static final long serialVersionUID = 1L;

	        public void paintComponent(Graphics g)
	        {
	           g.setColor(Color.BLACK);
	           g.fillRect(0, 0, this.getWidth(), this.getHeight());
	           g.setColor(Color.RED);
	           g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
	           g.setColor(Color.DARK_GRAY);
	           g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
	           g.setColor(Color.GRAY);
	           g.fillPolygon(x, y, numberofpoints);
	           g.setColor(shapeColor);
	           g.fillOval(oneX, oneY, size, size);
	        }
	    }
	    
	    
	    private void moveIt()
	    {
	    	int state = 0;
	    	boolean flag = true;
	    	int j = 0;
	        while (started)
	        {
	        	switch(state){
	        	case 0 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        			x[i] += 3;
	        		
	        		oneX += 3;
	        		if(oneX > 650)
	        			state = 1;
	        		break;
	        	case 1 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        			y[i] += 3;
	        		
	        		oneY += 3;
	        		if(oneY > 700)
	        			state = 2;
	        		break;
	        	case 2 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        			x[i] -= 3;
	        		
	        		oneX -= 3;
	        		if(oneX < 200)
	        			state = 3;
	        		break;
	        	case 3 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        			y[i] -= 3;
	        		
	        		oneY -= 3;
	        		if(oneY < 200)
	        			state = 0;
	        		break;
	        	}
	        
	            if(size > MAX_SIZE)
	            	flag = false;
	            else if(size < MIN_SIZE)
	            	flag = true;	            
	            
	            if(flag)
	            	size++;	            
	            else
	            	size--;
	            if(c % 25 == 0)
	            {
	            	shapeColor = colorArray[j];
	            	j++;
	            	if(j == 8)
	            		j = 0;	            	
	            }
	            
	            if(c % 50 == 0)
	            {
	            	x_origin = x[0];
	            	y_origin = y[0];
	            	rotate(60);
	            }
	            
	            try
	            {
	                Thread.sleep(10);
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	            frame.repaint();
	            c++;
	        }
	        frame.repaint();
	    }
	}
