package br.ufla.dcc.packet;

import br.ufla.dcc.command.GetDestinationCommand;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.NodeId;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.wuc.CollisionAvoidanceWUC;

public class FindDestinyPacket extends  PacketTask{

	public FindDestinyPacket(Address sender, NodeId receiver) {
		super(sender, receiver);
	}

	@Override
	public void execute(TaxiLayer taxi) {
		GetDestinationCommand command = new GetDestinationCommand(taxi.getNode().getId().asInt());
		command.send();
		if(taxi.getAgent() == null)
			taxi.sendEventSelf(new CollisionAvoidanceWUC(taxi.getSender(), Math.random() * 5,
				new ReturnDestinyPacket(taxi.getSender(), this.getSender().getId(), command.getGPS(), taxi.getNode().getPosition())));
	}

}
