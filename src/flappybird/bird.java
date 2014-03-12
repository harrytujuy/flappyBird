package flappybird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

/*/*
 *Clase en donde se declara el pajaro y todos los metodos que se usaran para controlarlo
*/

public class bird {
        static int DIAMETER = 25;				//Diametro del pajaro
	static int X = ( Game.WIDTH / 2 ) - ( DIAMETER / 2 );	//Inicializa la posicion en X del pajaro (nunca cambiara)  
	static int y =  Game.HEIGHT / 2;			//Inicializa la posición en Y del pajaro
	static int acceleration = 1;				//Aceleracion utilizada para simular la gravedad
	static int speed = 2;					//Velocidad a la que cae el pajaro

	static BufferedImage img = null;{
		try {
                    img = ImageIO.read(getClass().getResourceAsStream("Images/bird.png"));  //Agrega la imagen del pajaro
		} catch (IOException e) {
			System.out.println("WRONG BIRD");	//Despliega mensaje de error si no se carga bien la imagen
		}
        }
	
	public bird(){
		//Constructor de la clase pajaro
	}
	
	/*
            Es llamada con el click del mouse. Fija temporalmente la velocidad en 17
            para despues disminuirla por efecto de la aceleracion (gravedad)
        */
	public void jump(){
		speed = - 17;			
	}
	
        /*
            Metodo que controla el movimiento del pajaro y revisa que no choque
            con ninguno de los limites del frame
        */
	public static void move(){
	
		//Verifica que el pajaro no este chocando ni con la parte de arriba
                //ni la de abajo de la pantalla
		if ( ( y > 0 ) && ( y < Game.HEIGHT )) {
			speed += acceleration;								//Here's the gravity I was talking about the speed is just increased by 1 all the time, even after a jump
			y += speed;											//The actual movement, y location equals (where it was) + (how far it should go)
		}
		//en caso contrario el juego se reinicia
		else {
			reset();											//rests bird's postion, actual method below
			Game.loose = true;									//bird is dead! This is used in the Main method to reset the walls after a death
		}
		
	}
	/*
            Metodo que se encarga de reiniciar todos los valores del usuario 
            asi como también el juego
        */
	public static void reset(){	//Llamado cuando el jugador pierde
		y = Game.HEIGHT / 2;	//Se reinicia la posicion Y del pajaro
		speed = 2;              //Se reinicia la velocidad del pajaro
		Game.score = 0;         //Se reinicia el score del jugador
		
		Game.deathMessage = "Perdiste, vuelve a intentarlo";	//Muestra el mensaje de perdida al jugador
		
		//Controla el temporizador del mensaje de perdida, aparece durante 3000 milisegundos
		Timer deathTimer = new Timer(3000, new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				Game.deathMessage = "";
			 };
		});
		
		deathTimer.start();
	}
	
        /*
            Pinta el pajaro cada que se manda llamar
        */
	public static void paint(Graphics g){	
		g.drawImage(img, X, y, null);
	}
	
        /*
            Regresa las medidas del pajaro para detectar las colisiones
        */
	public static Rectangle getBounds(){
		 return new Rectangle(X, y, DIAMETER, DIAMETER);
		}
}