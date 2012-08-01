package br.ufla.dcc.packet;

import br.ufla.dcc.agent.Agent;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.NodeId;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.statistics.GetStatistics;

public class AgentPacket extends PacketTask{

	public AgentPacket(Address sender, NodeId receiver, Agent a) {
		super(sender, receiver);
		agent = a;
	}
	
	private Agent agent;

	public Agent getAgent() {
		return agent;
	}

	@Override
	public void execute(TaxiLayer taxi) {
		GetStatistics.getInstance().registerTransfer();
		taxi.setAgent(this.getAgent());
	}	

}
