package flappybird;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/*
    Clase que inicializa el menu y lo manipula
*/
public class Menu extends JPanel {
    private static final long serialVersionUID = 1L;
	int highscore;                          //Variable para contabilizar el puntaje
	
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(getClass().getResourceAsStream("Images/menuImage.png")); //Se carga la imagen del menu
		} catch (IOException e) {
			System.out.println("WRONG MENU");       //Muestra un mensaje de error en caso de que no se cargue la imagen del menu
		}}
	
	boolean startGame = false;                      //Variable con la que se controla el inicio del juego
	
	/*
            Metodo constructor del menu
        */
	public Menu(){
		setFocusable(true);			//Espera el click del mouse e inicializa el juego
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {    //Al recibir el click inicia el juego
				startGame = true;
			}

			});
	}
	
	/*
            Metodo para pintar la pantalla principal del juego (menu)
        */
        public void paint (Graphics g){
		super.paint(g);
	
		g.drawImage(img, 0, 0, null);       //Pinta la imagen del menu
	}
}
