package flappybird;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/*
 *Clase que contiene el proceso de ejecucion del juego
*/

public class FlappyBird {

    static JFrame frame = new JFrame();                                     //Declaración del JFrame
    public static void main(String[] args) throws InterruptedException {
        frame.setSize(Game.WIDTH, Game.HEIGHT);                             //Se declara el tamaño del JFrame y la ubicación
	frame.setVisible(true);                                             //Se define como visible el JFrame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	frame.setLocationRelativeTo(null);                                  //Fija la ubicación del JFrame en el centro de la pantalla
		
	run();	//ejecuta el juego
    }
    
    public static void run() throws InterruptedException{
		
		final Menu menu = new Menu();   //Inicializa el menu
		final Game game = new Game();	//Inicializa el juego
		
		
		Timer animationTimer = new Timer(20, new ActionListener(){  //Se declara el hilo y repinta los objetos cada 20 millisegundos
			  public void actionPerformed(ActionEvent event){
				 game.repaint();
				 game.move();
			 };
		});
		
		frame.add(menu);		//Agrega el menu al frame
		menu.setVisible(true);		//Hace visible el menu				
		frame.revalidate();		//Se asegura que el menu este pintado
		frame.repaint();
		
		while (menu.startGame == false){    //Espera a que sea presionado el mouse
			Thread.sleep(10);
		}	
				
		frame.remove(menu);		//Cuando hacen click en el mouse desaparece el menu
		frame.add(game);		//Ahora agrega el juego al frame
		game.setVisible(true);		//Hace visible el juego
		frame.revalidate();             //Se asegura que el juego se encuentre visible

		animationTimer.start();		//Inicia el temporizador del juego y comienza
    }
}
