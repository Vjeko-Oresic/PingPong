package pingPong;

public interface Konstante {
	// Velièina prozora
	public final int PODLOGA_SIRINA =  330; 
	public final int PODLOGA_VISINA = 230;
	public final int PODLOGA_GORE = 12;
	public final int PODLOGA_DOLJE = 180;
	// Pomicanje lopte(pixels) 
	public final int LOPTA_POMAK = 4;
	// Max i min koordinate lopte 
	public final int LOPTA_MIN_X = 1+ LOPTA_POMAK;
	public final int LOPTA_MIN_Y =  10;//1 + LOPTA_POMAK;
	public final int LOPTA_MAX_X = PODLOGA_SIRINA - LOPTA_POMAK;
	public final int LOPTA_MAX_Y = PODLOGA_VISINA - LOPTA_POMAK;
	// Poèetne koordinate lopte
	public final int LOPTA_START_X = PODLOGA_SIRINA/2;
	public final int LOPTA_START_Y = PODLOGA_VISINA/2;
	// Velièina reketa, pozicija i pomicanje 
	public final int IGRAC_X = 300;
	public final int IGRAC_Y = 100;
	public final int KOMPJUTER_X = 15;
	public final int KOMPJUTER_Y = 100;
	public final int REKET_POMAK = 15;
	public final int REKET_DUZINA = 30;
	public final int REKET_SIRINA = 5;
	public final int REZULTAT_WIN = 21;
	// Smanjenje na normalniju brzinu 
	public final int SLEEP_VRIJEME = 10; //ms
}
