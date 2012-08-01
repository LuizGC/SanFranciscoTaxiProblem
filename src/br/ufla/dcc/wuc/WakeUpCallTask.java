package br.ufla.dcc.wuc;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.event.WakeUpCall;
import br.ufla.dcc.node.TaxiLayer;

public abstract class WakeUpCallTask extends WakeUpCall{

	public WakeUpCallTask(Address sender) {
		super(sender);
	}

	
	public WakeUpCallTask(Address sender, double delay) {
		super(sender, delay);
	}


	public abstract void executeTask(TaxiLayer taxi);
	
}
