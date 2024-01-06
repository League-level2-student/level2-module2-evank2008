package _08_LeagueSnake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import processing.core.PApplet;

public class LeagueSnake extends PApplet implements ActionListener {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    boolean paused = false;
    boolean sprinting = false;
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    int size = 800;
    int foodX;
    int foodY;
    int direction = UP;
    int fullness = 0;
   ArrayList <Segment> tailArray = new ArrayList <Segment>();
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(size,size);
    }

    @Override
    public void setup() {
        head = new Segment(size/2,size/2);
        frameRate(10);
        dropFood();
       // timer.start();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(size/10)*10);
    	foodY = ((int)random(size/10)*10+10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,0);
        drawFood();
        move();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(255,0,0);
        rect(foodX,foodY,10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(60,150,140);
    	rect(head.segX,head.segY,10,10);
    	manageTail();
    }

    void drawTail() {
    	fill(0,255,0);
        // Draw each segment of the tail
       for(int i = tailArray.size()-1;i>-1;i--) {
        	//this line of code freezes the program when food is eaten
        	rect(tailArray.get(i).segX,tailArray.get(i).segY,10,10);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
    	
    	drawTail();
    	
        // After drawing the tail, add a new segment at the "start" of the tail and
    	tailArray.add(new Segment(head.segX,head.segY));
        // remove the one at the "end"
    	tailArray.remove(0);
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    }

    void checkTailCollision() {
    	
        // If the snake crosses its own tail, shrink the tail back to one segment
        for(int i = tailArray.size()-2;i>-1;i--) {
        	if(head.segX==tailArray.get(i).segX&&head.segY==tailArray.get(i).segY) {
        		JOptionPane.showMessageDialog(null, "car cash :(");
        		System.exit(0);
        	}
        }
        
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
    	
        // Set the direction of the snake according to the arrow keys pressed
    	if(direction!=UP&&keyCode==DOWN) {
            direction=keyCode;
        	}
    	if(direction!=DOWN&&keyCode==UP) {
            direction=keyCode;
        	}
    	if(direction!=LEFT&&keyCode==RIGHT) {
            direction=keyCode;
        	}
    	if(direction!=RIGHT&&keyCode==LEFT) {
            direction=keyCode;
        	}
    	if(keyCode==32) {
    		if(paused==false) {
    		frameRate(frameRate/10);
    		paused = true;
    		}
    		else {
    			frameRate(frameRate*10);
    			paused = false;
    		}
    	}
    	if(keyCode==16) {
    		if(sprinting==false) {
    		frameRate(frameRate*4);
    		sprinting = true;
    		}
    		else {
    			frameRate(frameRate/4);
    			sprinting = false;
    		}
    	}
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
            // Move head up
            head.segY-=10;
        } else if (direction == DOWN) {
            // Move head down
        	head.segY+=10;
        } else if (direction == LEFT) {
        	head.segX-=10;
        } else if (direction == RIGHT) {
        	head.segX+=10;
        }
        checkBoundaries();
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.segX>=size||head.segX<0||head.segY>=size||head.segY<0) {
        	JOptionPane.showMessageDialog(null,"car crash :(");
        	System.exit(0);
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.segX==foodX&&head.segY==foodY) {
        	fullness+=1;
        	dropFood();
        	//Segment tailSegment = new Segment(head.segX,head.segY);
        	tailArray.add(new Segment(head.segX,head.segY));
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
