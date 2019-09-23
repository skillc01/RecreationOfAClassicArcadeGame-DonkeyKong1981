package donkeykong; 
import java.awt.*; 

import java.awt.event.KeyEvent;
import java.io.*;
//level of game 
public class Stage1 extends AbstractState { 
    public static int win = 0; 
    public static int lives = 3; 
    public static int score = 0; 
    protected Background background; // black background 
    protected Platform[] platforms; // platforms 
    protected Ladder[] ladders; // ladders 
    protected Characters[] charas; //characters (Pauline) 
    protected Player mario; // new player  
    protected EnemyBarrel[] barrels;//enemy barrels 
    
    public Stage1(Manager gamestates) { 
        super(gamestates); 
        GamePanel.TickCounter = 0; //starts the tickcounter after the player has pressed "Play" on start menu 
        if (win ==1) { //when player touches Pauline win is 1 
            gamestates.stages.push(new GameOverScreen(gamestates)); //if player wins, taken to win screen
        }    
    }

    public void tick() { 
        for (int j = 0; platforms.length > j; j++) { 
            platforms[j].tick(); 
        } 
        for (int i = 0; ladders.length > i; i++) { 
            ladders[i].tick(); 
        } 
        mario.tick(ladders); 
        mario.tick(charas); 
        mario.tick(barrels); 
        barrels[0].tick(platforms);  
        
        if (GamePanel.TickCounter>600) { 
            barrels[1].tick(platforms);  }     
        if (GamePanel.TickCounter>1200) { 
            barrels[2].tick(platforms);   }       
        if (GamePanel.TickCounter>1800) { 
            barrels[3].tick(platforms);   }
        if (GamePanel.TickCounter>2400) { 
            barrels[4].tick(platforms);   } 
        if (GamePanel.TickCounter>3000) { 
            barrels[5].tick(platforms);   } 
        if (GamePanel.TickCounter>3600) { 
            barrels[6].tick(platforms);   } 
        if (GamePanel.TickCounter>4200) { 
            barrels[7].tick(platforms);   } 
        if (GamePanel.TickCounter>4800) { 
            barrels[8].tick(platforms);   }
        if (GamePanel.TickCounter>5200) { 
            barrels[9].tick(platforms);   } 
        
       mario.tick(platforms);
        
      for (int f = 0; barrels.length > f; f++) {
    	  //rectangle around each barrel
    	  Rectangle r = new Rectangle((int)barrels[f].x, (int) barrels[f].y, 30, 30);
    	  //if the rectangle contains the player point then the player loses a life and 
    	  if ( r.contains((int)Player.x+15, (int)Player.y+27) )   {
	     	  System.out.println("Barrel Hit Point 1"); 
	     	  Player.y = 700; //respawns at the beginning of the game
	     	  Player.x = 20;
	     	  lives = lives -1; 
		  }
    	  //score rectangle (rectangle above each barrel)
    	  Rectangle r2 = new Rectangle((int)barrels[f].x-2, (int)barrels[f].y-70, 35, 90); //score rectangle
    	  if   ( r2.contains((int)Player.x+15, (int)Player.y+15)   )
    	  {  System.out.println("Score increase");     	  
		     score = score + 50; 
		  }
        }
      }
    
    public void initial() { 
        barrels=new EnemyBarrel[10]; 
        background = new Background(0, 0);// position of background 
        mario = new Player(30, 30); //creates Mario //size of mario 
        for (int i=0;i<10;i++) { 
            barrels[i] = new EnemyBarrel(30, 30); //creates barrel1 //sizes 
        } 
        charas = new Characters[1];  
        charas[0] = new Characters(580, 140); //Position of all characters and oil drum
        ladders = new Ladder[4];  
        // positions of ladders 
        ladders[0] = new Ladder(620, 600); 
        ladders[1] = new Ladder(300, 450);  
        ladders[2] = new Ladder(720, 300); 
        ladders[3] = new Ladder(420, 150); 
         
        platforms = new Platform[9];  
        // positions of platforms 
        platforms[0] = new Platform(-50, 750); // bottom platform 
        platforms[1] = new Platform(350, 750); // bottom platform right  
        platforms[2] = new Platform(150, 600); /// left platform 1 
        platforms[3] = new Platform(250, 450); // right platform 1 
        platforms[4] = new Platform(150, 300); // left platform 2 
        platforms[5] = new Platform(250, 150); // right platform 2 - ending platform //where DK & Pauline to be drawn 
        //off-screen platforms 
        //lower platforms need to be more left/right than others 
        platforms[6] = new Platform(-450, 600); /// left platform 1  //400left of platform on screen 
        platforms[7] = new Platform(450, 450); // right platform 1  //400right of platform on screen 
        platforms[8] = new Platform(-450, 300); // left platform 2  //400left of platform on screen 
        //platforms[9] = new Platform(450, 150); // ending platform  //removed for fullscreen mode
    } 
 
    public void keyPressed(int k) { 
        mario.keyPressed(k);  
    } 
 
    public void keyReleased(int k) { 
        mario.keyReleased(k); 
    } 
 
    protected void draw(Graphics g) { 
        background.draw(g); 
        g.setColor(Color.black); //fills the empty screen with black background
		g.fillRect(0, 800, 2500, 2500); //fullscreen
		g.fillRect(1200, 0, 2500, 2500); //fullscreen
        for (int j = 0; platforms.length > j; j++) { // draws the platforms 
            platforms[j].draw(g); } 
        for (int i = 0; ladders.length > i; i++) { // draws the ladders 
            ladders[i].draw(g); }
        barrels[0].draw(g); // draws the barrel1      
        if (GamePanel.TickCounter>600) { 
            barrels[1].draw(g); }// draws the barrel2                   
        if (GamePanel.TickCounter>1200) { 
            barrels[2].draw(g); } // draws the barrel3     
        if (GamePanel.TickCounter>1800) { 
            barrels[3].draw(g); }// draws the barrel4 
        if (GamePanel.TickCounter>2400) { 
            barrels[4].draw(g); } // draws the barrel5 
        if (GamePanel.TickCounter>3000) { 
            barrels[5].draw(g); }// draws the barrel6 
        if (GamePanel.TickCounter>3600) { 
            barrels[6].draw(g); }// draws the barrel7 
        if (GamePanel.TickCounter>4200) { 
        	barrels[7].draw(g); }
        if (GamePanel.TickCounter>4800) { 
        	barrels[8].draw(g); }
        if (GamePanel.TickCounter>5200) { 
        	barrels[9].draw(g);  } 
         
        for (int o = 0; charas.length > o; o++) { // draws characters onto level (Pauline) 
            charas[o].draw(g);
            g.setColor(Color.white);//text for lives and score at top of screen
		    g.setFont(new Font("Helvetica", Font.BOLD, 20));
			g.drawString("Lives: " + lives, 780, 20);//text for lives & showing no. of lives 
			g.setColor(Color.red);
		    g.setFont(new Font("Helvetica", Font.BOLD, 20));
			g.drawString("SCORE", 10, 25);//text for SCORE text 
			g.setColor(Color.white);
			g.drawString("000" + score, 20, 50);//text showing score
			
			if (win ==1) { //player taken to end screen
                gamestates.stages.push(new GameOverScreen(gamestates)); // takes to end screen  
            } //if player out of lives then GAME OVER
            if (lives ==0) { //when character loses game over screen
            	gamestates.stages.push(new GameOverScreen(gamestates)); // takes to end screen
            } 
        } 
        mario.draw(g); // draws the character 
        
      //for platform fall off
        if (Player.y >= 850) {
        	lives = lives -1; 
        	Player.y = 700;
        	Player.x = 20;   }
    } 
} 
 