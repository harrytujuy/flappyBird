package flappybird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

/*
    Clase que pinta y controla las paredes del juego
*/
public class Wall {
    Random rnd = new Random();				//Variable para crear de manera aleatoria la altura de las paredes
		
    int x ;						//Inicializa la posicion en X de las paredes
    int y = rnd.nextInt(Game.HEIGHT - 400) + 200;	//Inicializa la posicion en Y de las paredes que es el tope
    static int speed = - 6;				//Inicializa la velocidad a la que avanzan las paredes hacia el pajaro
    int WIDTH = 45;					//Se define el ancho de las paredes 
    int height = Game.HEIGHT - y;			//Se define el tamaño de la pared de arriba
    int GAP = 200;					//Inicializa el tamaño de separacion entre paredes
	
	static BufferedImage img = null;{
		try {
                    img = ImageIO.read(getClass().getResourceAsStream("Images/barra.png")); //Carga la imagen de la pared
					
		} catch (IOException e) {
			System.out.println("WRONG WALL");	//Muestra un mensaje de error cuando no se carga bien la imagen de la pared
		}}
	/*
            Metodo constructor del objeto pared recibiendo como parametros la posicion en X
        */
	public Wall(int i){
		this.x = i;
	}
	
	/*
            Metodo que pinta las dos paredes en el frame
        */
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);					//Pinta la pared superior 
		g.drawImage(img, x, ( -Game.HEIGHT ) + ( y - GAP), null);	//Pinta la pared inferior
	}
	
        /*
            Metodo que controla el movimiento de las dos paredes
        */
	public void move(){
		
			x += speed;					//Rota la pared hacia el pajaro
	
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);      //Detecta si el pajaro choca con la pared inferior
		Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Game.HEIGHT - (height + GAP)); //Detecta si el pajaro choca con la pared superior
		
		if ( (wallBounds.intersects(bird.getBounds()) ) || (wallBoundsTop.intersects(bird.getBounds()))){   //Revisa si el pajaro choca con alguna de las paredes
			bird.reset();       //Reinicia el pajaro a la posicion inicial
			died();             //Indica que el juego termino
		}
			
		
               
		if (x <= 0 - WIDTH){                                //Verifica cuando las paredes llegan al final del frame
			x = Game.WIDTH;                             //Inicializa la ubicacion de las paredes fuera del frame por el lado derecho
			y = rnd.nextInt(Game.HEIGHT - 400) + 200;   //Inicializa la posicion en Y de las paredes
			height = Game.HEIGHT - y;                   //Inicialia la altura de las paredes
		}		
	}
	

	/*
            Metodo que manda llamar el metodo que reinicia el juego ademas creo nuevas
            alturas para las paredes
        */
	public void died(){
			y = rnd.nextInt(Game.HEIGHT - 400) + 200;   //Inicializa una nueva posicion en Y de la pared
			height = Game.HEIGHT - y;                   //Inicializa una nueva altura de la pared
			Game.loose = true;                          //Ejecuta el metodo loose
	}
}
