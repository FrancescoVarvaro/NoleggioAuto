package it.polito.tdp.noleggio.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.PriorityQueue;
import it.polito.tdp.noleggio.model.Event.EventType;

public class Simulatore {
	
	// Parametri di ingresso: N. AUTO, TEMPO ARRIVO TRA UN CLIENTE E L'ALTRO, DURATA NOLEGGIO
	private int NC; 
	private Duration T_IN = Duration.ofMinutes(10); 
	private Duration T_TRAVEL = Duration.ofHours(1); // 1, 2, o 3 volte tanto
	
	// Valori calcolati in uscita L'OUTPUT
	private int nClientiTot ; 
	private int nClientiInsoddisfatti ;
	
	// Stato del mondo ( VARIABILI CHE SERVONO AL SIMULATORE PER CAPIRE COME EVOLVE IL SISTEMA )-->IN QUESTO CASO N.AUTO DISPONIBILI
	private int autoDisponibili ;
	
	// Coda degli eventi  CREO NUOVA CLASSE EVENT--> CONTIENE INFO SULL'EVENTO (IL TEMPO (SEMPRE), TIPO DI EVENTO, INFO AGGIUNTIVE)
	private PriorityQueue<Event> queue ; 
	
	// Costruttore
	public Simulatore(int NC) {
		this.NC = NC ;
		this.queue = new PriorityQueue<>();
		this.autoDisponibili = NC ;
	}
	
	public void run() { // ESEGUE LA SIMULAZIONE, finchè la coda non è vuota !!
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll() ; // ESTRAE IN ORDINE DI TEMPO (1 PER VOLTA E DALLA TESTA) L'EVENTO DELLA LISTA
			processEvent(e);  // PASSO L'EVENTO --> E DECIDE LUI COSA FARE 
		}
		
	}
	
	public void caricaEventi() {  // PRECARICO L'ORA DI ARRIVO DI TUTTI I CLIENTI
		
		LocalTime ora = LocalTime.of(8, 0) ; // 8:00 DEL MATTINO (ORA DI PARTENZA)
		while(ora.isBefore(LocalTime.of(16,0))) { // SE L'ORA E' PREC. ALL'ORA DI CHIUSURA 16:00
			this.queue.add(new Event(ora, EventType.NUOVO_CLIENTE)) ;
			ora = ora.plus(T_IN) ;
		}
	}

	private void processEvent(Event e) {
		
		switch(e.getType()) {
		case NUOVO_CLIENTE: // ARRIVA NUOVO CLIENTE
			this.nClientiTot++ ;
			if(this.autoDisponibili>0) {
				this.autoDisponibili-- ;
				int ore = (int)(Math.random()*3)+1;  // MODELLO CHE CI DICE PER QUANTE ORE LA NOLEGGIA
				
				LocalTime oraRientro = e.getTime().plus(Duration.ofHours(ore * T_TRAVEL.toHours())); 
				this.queue.add(new Event(oraRientro, EventType.AUTO_RESTITUITA));
			} else {
				this.nClientiInsoddisfatti++;
			}
			break;
		case AUTO_RESTITUITA:  // SE IL CLIENTE RESTITUISCE UN AUTO
			this.autoDisponibili++ ;  // NON HO BISOGNO DI GENERARE NUOVI EVENTI
			break;
		}
	}

	public int getnClientiTot() {
		return nClientiTot;
	}

	public int getnClientiInsoddisfatti() {
		return nClientiInsoddisfatti;
	}

	public void setNC(int nC) {
		NC = nC;
	}

	public void setT_IN(Duration t_IN) {
		T_IN = t_IN;
	}

	public void setT_TRAVEL(Duration t_TRAVEL) {
		T_TRAVEL = t_TRAVEL;
	}
	
	
	

}
