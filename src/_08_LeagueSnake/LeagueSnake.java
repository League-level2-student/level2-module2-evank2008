package _08_LeagueSnake;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
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
        size(800,800);
    }

    @Override
    public void setup() {
        head = new Segment(400,400);
        frameRate(20);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(80)*10);
    	foodY = ((int)random(80)*10+10);
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
    	fill(30,200,30);
    	rect(head.segX,head.segY,10,10);
    	manageTail();
    }

    void drawTail() {
    	fill(0,255,0);
        // Draw each segment of the tail
        for(int i = tailArray.size();i>0;i--) {
        	
        	rect(tailArray.get(i).segX,tailArray.get(i).segY,10,10);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
    	if(keyCode!=UP&&keyCode!=DOWN&&keyCode!=LEFT&&keyCode!=RIGHT) {
    		move();
    	}
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
        if(head.segX>=800||head.segX<0||head.segY>=800||head.segY<0) {
        	JOptionPane.showMessageDialog(null,"You hit the edge!");
        	System.exit(0);
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.segX==foodX&&head.segY==foodY) {
        	fullness+=1;
        	dropFood();
        	Segment tailSegment = new Segment(head.segX,head.segY);
        	tailArray.add(tailSegment);
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
