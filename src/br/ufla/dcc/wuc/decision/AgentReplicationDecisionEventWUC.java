package br.ufla.dcc.wuc.decision;

import br.ufla.dcc.agent.Agent;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.ReturnDestinyPacket;

public class AgentReplicationDecisionEventWUC extends DecisionEvent {

	public AgentReplicationDecisionEventWUC(Address sender, double delay) {
		super(sender, delay);
	}

	@Override
	public void executeTask(TaxiLayer taxi) {
	
		for (ReturnDestinyPacket pakage : nosPakage) {
			Agent agent = taxi.getAgent();
			this.sendAgent(taxi,pakage);
			taxi.setAgent(agent);
		}
	}

}
