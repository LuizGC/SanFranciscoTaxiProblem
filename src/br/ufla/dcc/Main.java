package br.ufla.dcc;
import br.ufla.dcc.grubix.simulator.kernel.Simulator;


public class Main {
	
	public static IntelligenceLevel intelligenceLevel = IntelligenceLevel.SMART;
	
	public static void main(String[] args) { 
		String path = "application.xml";
		args = new String[1];
		args[0] = path;
		Simulator.main(args);
	}
	
	
	
}
