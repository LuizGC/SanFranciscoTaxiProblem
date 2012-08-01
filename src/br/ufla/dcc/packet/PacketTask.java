package br.ufla.dcc.packet;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.NodeId;
import br.ufla.dcc.grubix.simulator.event.ApplicationPacket;
import br.ufla.dcc.node.TaxiLayer;

public abstract class PacketTask extends ApplicationPacket{

	public PacketTask(Address sender, NodeId receiver) {
		super(sender, receiver);
	}

	public abstract void execute(TaxiLayer taxi);
	
}
