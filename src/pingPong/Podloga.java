package pingPong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//Prikaz stola, reketa i loptice
@SuppressWarnings("serial")
public class Podloga extends JPanel implements Konstante{	
	
	private JLabel label;
	private int kompjuter_Y = KOMPJUTER_Y;
	private int igrac_Y = IGRAC_Y;
	private int loptaX = LOPTA_START_X;
	private int loptaY = LOPTA_START_Y; 

	Dimension velicina = new Dimension(PODLOGA_SIRINA,PODLOGA_VISINA);
	// Postavlja velièinu za frame.
	public Dimension getVelicina() {
		return velicina;
	}
	// Konstuktor. Kreira Listener za dogaðaje tipki
	Podloga(){
		Igra igra = new Igra(this);
		//	Osluškuje pritiskanje tipki
		addKeyListener(igra);
	}
	// Dodavanje palete u Frame
	void addPaneltoFrame(Container container) {
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(this);
		label = new JLabel("Pritisnite N za novu igru, S za servis ili I za izlaz");
		container.add(label);
	}
	// Iscratavanje prozora.
	// Osvježava zaslon ili se poziva kad je metodana repaint() pozvana
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  
		// boja stol u zeleno
		g.setColor(Color.green);
		g.fillRect(0,0,PODLOGA_SIRINA,PODLOGA_VISINA); 
		// boja igraèev reket
		g.setColor(Color.yellow);
		g.fillRect(IGRAC_X, igrac_Y,REKET_SIRINA, REKET_DUZINA); 
		// boja kompjuterov reket
		g.setColor(Color.blue);
		g.fillRect(KOMPJUTER_X, kompjuter_Y,REKET_SIRINA,REKET_DUZINA); 
		// boja loptu
		g.setColor(Color.red);
		g.fillOval(loptaX,loptaY,10,10);     	
		//crta bijele linije
		g.setColor(Color.white);
		g.drawRect(10,10,300,200);
		g.drawLine(160,10,160,210);
		
		requestFocus();
	}
	// Postavlja poziciju igraèevog reketa
	public void setIgrac_Y(int yKoordinata){
		this.igrac_Y = yKoordinata;
		repaint();
	}
	//Vraæa poziciju igraèevog reketa
	public int getIgrac_Y(){
		return igrac_Y;
	}

	//Postavlja poziciju kompkuterocog reketa
	public void setKompjuter_Y(int yKoordinata){
		this.kompjuter_Y = yKoordinata;
		repaint();
	}
	// Postavlja poruku 
	public void setTextPoruke(String text){
		label.setText(text);
		repaint();
	}
	// Postavlja loptu na poziciju
	public void setPozicijaLopte(int xPos, int yPos){
		loptaX=xPos;
		loptaY=yPos;
		repaint();
	}

	public static void main(String[] args) {
		// Pravi instancu okvira
		JFrame f = new JFrame("PingPong");
		// Omoguæuje da se prozor zatvori na pritiskanje križiæa
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Podloga podloga = new Podloga();
		podloga.addPaneltoFrame(f.getContentPane());
		// Postavlja velièinu prozora tj. podloge
		f.setBounds(0,0,PODLOGA_SIRINA+5, PODLOGA_VISINA+40);
		f.setVisible(true);	
	}
}