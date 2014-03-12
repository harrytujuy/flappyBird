package flappybird;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class looseMenu extends JPanel{
    private static final long serialVersionUID = 1L;
    
    static BufferedImage img = null;{
		try {
			img = ImageIO.read(getClass().getResourceAsStream("Images/perder.png")); //Se carga la imagen del menu de perdida
                    } catch (IOException e) {
			System.out.println("WRONG MENU");       //Muestra un mensaje de error en caso de que no se cargue la imagen del menu de perdida
                      }   
    }
	
	boolean startGame = false;
        
        public looseMenu(){
		setFocusable(true);			//Espera el click del mouse e inicializa el juego
		/*addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {    //Al recibir el click inicia el juego
				startGame = true;
			}

			});*/
                addKeyListener(new KeyListener(){

                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_SPACE)
                            startGame = true;
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        }
        
        public void paint (Graphics g){
		super.paint(g);
	
		g.drawImage(img, 0, 0, null);       //Pinta la imagen del menu
	}
}
