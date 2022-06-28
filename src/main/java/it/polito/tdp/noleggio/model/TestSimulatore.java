package it.polito.tdp.noleggio.model;

public class TestSimulatore {

	public static void main(String[] args) {
		Simulatore sim = new Simulatore(12); 

		sim.caricaEventi(); //PRIMA CARICO GLI EVENTI
		sim.run(); // POI FACCIO RUN

		System.out.println(
				"Clienti: " + sim.getnClientiTot() + " di cui " + 
				sim.getnClientiInsoddisfatti() + " insoddisfatti\n");

	}

}
