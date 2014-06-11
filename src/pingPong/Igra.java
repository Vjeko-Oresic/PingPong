package pingPong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Igra implements Runnable, KeyListener, Konstante{
	
	private Podloga podloga;
	private int igrac_Y = IGRAC_Y;
	private int kompjuter_Y=KOMPJUTER_Y;
	private int igrac_rezultat; 
	private int kompjuter_rezultat;
	private int loptaX;   // pozicaja lopte po X
	private int loptaY;   // pozicija lopte po Y
	int predznak_Y = 1;
	private boolean pomicanjeLijevo = true;
	private boolean lopta_servirana = false;
	//Vrijednost u pixelima za vertikalno pomicanje lopte     
	private int vertikalnoPomicanje;  
	Thread thr;

	public Igra(Podloga zelenapodloga){
		podloga = zelenapodloga;
		thr = new Thread(this);
		thr.start();
	}
	// Metoda za keyListener
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if(igrac_Y > PODLOGA_GORE)
			igrac_Y -= REKET_POMAK;
			//Postavljanje nove pozicije reketa
			podloga.setIgrac_Y(igrac_Y);
			break;
		case KeyEvent.VK_DOWN:
			if(igrac_Y < PODLOGA_DOLJE)
			igrac_Y += REKET_POMAK;
			podloga.setIgrac_Y(igrac_Y);
			break;
		case KeyEvent.VK_N:
			startNovaIgra();
			break;
		case KeyEvent.VK_I:
			endIgra();
		case KeyEvent.VK_S:
			igracServira();
		}
	}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){
	}
	// Pokretanje nove igre  
	public void startNovaIgra(){
		kompjuter_rezultat=0;
		igrac_rezultat=0;
		podloga.setTextPoruke("Rezultat Kompjuter: 0  Igraè: 0");
		igracServira();
	}
	// Završetak igre
	public void endIgra(){
		System.exit(0);
	}
	//Metoda za Runnable 
	public void run(){
		boolean canBounce=false; 
		while (true) {
			if(lopta_servirana){ 
				//1. Da li lopta pomièe u lijevo?
				if ( pomicanjeLijevo && loptaX > LOPTA_MIN_X){
					canBounce = (loptaY >= kompjuter_Y && loptaY < (kompjuter_Y + REKET_DUZINA)?true: false);
						
					if(loptaY < LOPTA_MIN_Y){
						predznak_Y = -1;
					}
					else if(loptaY > LOPTA_MAX_Y-20){
						predznak_Y = -1;
					}
					
					loptaX-=LOPTA_POMAK;
					// Dodavanje pomaka prema gore ili dolje za bilo koji pomak lopte lijevo/desno 
					// Dijagonalni pomak lopte
					loptaY = loptaY - predznak_Y*vertikalnoPomicanje;
					podloga.setPozicijaLopte(loptaX,loptaY);
							
					if (loptaX <= KOMPJUTER_X  && canBounce){ 
						pomicanjeLijevo=false;       	
					}
				}
				// 2. Da li lopta pomièe u desno?
				if ( !pomicanjeLijevo && loptaX <= LOPTA_MAX_X){
					canBounce = (loptaY >= igrac_Y && loptaY < (igrac_Y + REKET_DUZINA)?true:false);
					loptaX+=LOPTA_POMAK;
					podloga.setPozicijaLopte(loptaX,loptaY);
						   			
					if (loptaX >= IGRAC_X && canBounce){	
						pomicanjeLijevo=true;
					}
				}	
				// 3. Pomicanje kompjuterovog reketa
				if (kompjuter_Y < loptaY && kompjuter_Y < PODLOGA_DOLJE){
					kompjuter_Y +=REKET_POMAK;
				}else if (kompjuter_Y > PODLOGA_GORE){
					kompjuter_Y -=REKET_POMAK;
				}
				podloga.setKompjuter_Y(kompjuter_Y);
				// 4. Malo usporavanje igre 	
				try {
					Thread.sleep(SLEEP_VRIJEME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 5. Update rezultata ako je lopta na podlozi, ali miruje
				if (isLoptaNaPodlozi()){
					if (loptaX > LOPTA_MAX_X ){
						kompjuter_rezultat++;
						prikazRezultata();
					}else if (loptaX < LOPTA_MIN_X){
						igrac_rezultat++;
						prikazRezultata();
					}
				} 	
			} // Kraj if(lopta_servirana)	
		} // Kraj while
	}// Kraj run()
	//Servira se sa trenutne pozicije igraèevog reketa
	private void igracServira(){
		lopta_servirana = true;
		loptaX = IGRAC_X-1;
		loptaY=igrac_Y;
		predznak_Y = 1;
		if (loptaY > PODLOGA_VISINA/2){
			vertikalnoPomicanje=-1;
		}else{
			vertikalnoPomicanje=1;
		}
		podloga.setPozicijaLopte(loptaX,loptaY);
		podloga.setIgrac_Y(igrac_Y);
	}

	private void prikazRezultata(){
		lopta_servirana = false;
		if (kompjuter_rezultat == REZULTAT_WIN){
			podloga.setTextPoruke("Pobjeda za kompjuter! " + kompjuter_rezultat +  ":" + igrac_rezultat);
		}else if (igrac_rezultat == REZULTAT_WIN){
			podloga.setTextPoruke("Tvoja pobjeda! "+ igrac_rezultat + ":" + kompjuter_rezultat);
		}else{
			podloga.setTextPoruke("Kompjuter: "+ kompjuter_rezultat +  " Igrac: " + igrac_rezultat);
		}
	}
	// Provjera da li je lopta presla granice na podlozi
	private boolean isLoptaNaPodlozi(){
		if (loptaY >= LOPTA_MIN_Y && loptaY <= LOPTA_MAX_Y){
			return true;
		}else {
			return false;
		}
	}
}