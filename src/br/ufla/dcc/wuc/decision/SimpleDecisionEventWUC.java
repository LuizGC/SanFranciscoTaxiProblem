package br.ufla.dcc.wuc.decision;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.ReturnDestinyPacket;

public class SimpleDecisionEventWUC extends DecisionEvent {

	public SimpleDecisionEventWUC(Address sender, double delay) {
		super(sender, delay);
	}

	@Override
	public void executeTask(TaxiLayer taxi) {
		
		ReturnDestinyPacket nextNo = null;
		
		for (ReturnDestinyPacket pakage : nosPakage) {
				
			if(taxi.getAgent().getTarget().isInside(pakage.getPosition())){
					nextNo = pakage;
					break;
			}else
				if(taxi.getAgent().getTarget().isInside(pakage.getDestiny()))
					nextNo = pakage;
			
			
		}

		if (nextNo != null) {
			this.sendAgent(taxi,nextNo);
		}
	
	}

}
