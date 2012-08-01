 package br.ufla.dcc.packet;

import java.util.List;

import br.ufla.dcc.Main;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.NodeId;
import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.wuc.decision.DecisionEvent;

public class ReturnDestinyPacket extends PacketTask {

	private List<Position> GPS;
	private Position position;


	public ReturnDestinyPacket(Address sender, NodeId receiver,
			 List<Position> GPS, Position position) {
		super(sender, receiver);
		this.GPS = GPS;
		this.position = position;
		
	}	


	public List<Position> getGPS() {
		return GPS;
	}

	public Position getDestiny(){
		if(GPS.size() > 0)
			return GPS.get(GPS.size()-1);
		else
			return position;
	}
	
	public Position getPosition() {
		return position;
	}
	


	@Override
	public void execute(TaxiLayer taxi) {
		DecisionEvent decision = Main.intelligenceLevel.create(taxi.getSender(), 0);
		decision.setPakage(this);
		taxi.sendEventSelf(decision);
	}

}
