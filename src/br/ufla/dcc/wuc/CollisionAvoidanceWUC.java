package br.ufla.dcc.wuc;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.event.ApplicationPacket;
import br.ufla.dcc.node.TaxiLayer;

public class CollisionAvoidanceWUC extends WakeUpCallTask{

	private ApplicationPacket pak;
	
	public CollisionAvoidanceWUC(Address sender, double delay, ApplicationPacket pak) {
		super(sender, delay);
		this.pak = pak;
	}


	@Override
	public void executeTask(TaxiLayer taxi) {
		taxi.sendPacket(this.pak);	
	}

}
