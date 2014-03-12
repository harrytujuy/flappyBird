package flappybird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import flappybird.Wall;

/*
    Clase que contiene el juego, en esta se inicializan las paredes y el pajaro
*/
public class Game extends JPanel {
    
	static int HEIGHT = 800;			//Se inicializa la altura del frame (800)
	static int WIDTH = 600;				//Se inicializa el ancho del frame (600)
	bird birdy = new bird();			//Crea un nuevo objeto pajaro 
	Wall wall = new Wall(WIDTH);			//Crea la primera pared del juego
	Wall wall2 = new Wall(WIDTH + (WIDTH / 2));	//Crea la segunda pared del juego
	static int score = 0;				//Inicializa el puntaje del jugador en 0
	int scrollX = 0;				//Variable que controla la rotacion del fondo
	static boolean loose = false;			//Variable para saber cuando pierde el usuario
	static String deathMessage = "" ; 		//String que contiene el mensaje de perdida
	
	BufferedImage img = null;{
	try {
            img = ImageIO.read(getClass().getResourceAsStream("Images/fondo.png")); //Carga la imagen del fondo del juego
	} catch (IOException e) {
		System.out.println("WRONG BACKGROUND");		//Muestra un error en caso de que no se haya cargado bien el fondo
	}}
	
	/*
            Metodo en el cual se revisa que el mouse haya sido presionado
        */
	public Game(){ 
		this.addMouseListener(new MouseAdapter(){

			public void mousePressed(MouseEvent arg0) {     //Al dar click al mouse el pajaro brinca
				birdy.jump();
			}
		
		});	
		
	}
        /*
            Metodo en el que se pintan el fondo, las paredes y el pajaro
        */
	@SuppressWarnings("static-access")
	public void paint(Graphics g){
		super.paint(g);
		
		g.drawImage(img, scrollX, 0, null);		//Se usan dos fondos para que la rotacion de ambos fondos			
		g.drawImage(img, scrollX + 1800, 0, null);	//sea mas precisa
		
		wall.paint(g);                                  //Pinta la primera pared
		wall2.paint(g);                                 //Pinta la segunda pared
 		birdy.paint(g);                                 //Pinta al pajaro
 	
 		g.setFont(new Font("comicsans", Font.BOLD, 40));    //Define el tipo y tamaño de letra
 		g.drawString("" + score, WIDTH / 2 - 20, 100);      //Pinta el score del usuario
 		g.drawString(deathMessage, 35, 200);                //Pinta el mensaje de perdida
	}
	
        /*
            Metodo en el que se controla el movimiento de las paredes, el pajaro y el fondo
        */
	@SuppressWarnings("static-access")
	public void move(){

		wall.move();			//Reubica la primer pared
		wall2.move();			//Reubica la segunda pared
		birdy.move();			//Mueve el pajaro
	
		scrollX += Wall.speed;          //Rota el fondo del juego
		
		if (scrollX == -1800)           //Inicializa el ciclo con el que se repite el movimiento del fondo
			scrollX = 0;
		
		if (loose){			//Cuando el usuario pierde reubica las paredes
			wall.x = 600;
			wall2.x = 600 + (WIDTH / 2);
			loose = false;
		}
		
		if ( (wall.x == bird.X) || (wall2.x == bird.X) )    //Incrementa el score cuando el usuario pasa una pared
			score();
	}
	
        /*
            Metodo que aumenta el score del usuario
        */
	public static void score(){
		score += 1;
	}
}
