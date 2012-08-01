package br.ufla.dcc.wuc.decision;

import java.util.ArrayList;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.AgentPacket;
import br.ufla.dcc.packet.ReturnDestinyPacket;
import br.ufla.dcc.wuc.WakeUpCallTask;

public abstract class DecisionEvent extends WakeUpCallTask{

	protected ArrayList<ReturnDestinyPacket> nosPakage;
	
	public DecisionEvent(Address sender, double delay) {
		super(sender, delay);
		nosPakage = new ArrayList<ReturnDestinyPacket>();
	}

	public ArrayList<ReturnDestinyPacket> getNosPakage() {
		return nosPakage;
	}

	public void setPakage(ReturnDestinyPacket pakage) {
		this.nosPakage.add(pakage);
	}

	protected void sendAgent(TaxiLayer taxi, ReturnDestinyPacket nextNo){
		AgentPacket ap = new AgentPacket(taxi.getSender(), nextNo.getSender().getId(), taxi.getAgent());
		taxi.sendPacket(ap);
		taxi.setAgent(null);
	}
	
}
