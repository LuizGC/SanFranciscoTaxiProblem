package br.ufla.dcc.wuc.decision;

import java.util.ArrayList;

import br.ufla.dcc.command.GetDestinationCommand;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.ReturnDestinyPacket;

public class ProbabilityDecisionEventWUC extends DecisionEvent {

	public ProbabilityDecisionEventWUC(Address sender, double delay) {
		super(sender, delay);
	}

	@Override
	public void executeTask(TaxiLayer taxi) {
		GetDestinationCommand command = new GetDestinationCommand(taxi.getNode().getId().asInt());
		command.send();

		int largerProb = taxi.getAgent().getTarget().probilityOfWalkingInside(command.getDestiny(), taxi.getNode().getPosition());
		ReturnDestinyPacket nextNo = null;
		ArrayList<ReturnDestinyPacket> nosPakage = this.nosPakage;
		
		for (ReturnDestinyPacket pakage : nosPakage) {
			int probability = taxi.getAgent().getTarget().probilityOfWalkingInside(pakage.getDestiny(), pakage.getPosition());
			if (largerProb < probability) {
				largerProb = probability;
				nextNo = pakage;
			}
			if(taxi.getAgent().getTarget().isInside(pakage.getPosition())){
				nextNo = pakage;
				break;
			}
		}

		if (nextNo != null) {
			this.sendAgent(taxi, nextNo);
		}
		
	}

}
