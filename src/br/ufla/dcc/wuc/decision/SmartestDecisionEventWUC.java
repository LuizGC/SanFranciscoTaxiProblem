package br.ufla.dcc.wuc.decision;

import java.util.ArrayList;

import br.ufla.dcc.command.GetDestinationCommand;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.ReturnDestinyPacket;

public class SmartestDecisionEventWUC extends DecisionEvent {

	public SmartestDecisionEventWUC(Address sender, double delay) {
		super(sender, delay);
	}

	@Override
	public void executeTask(TaxiLayer taxi) {
		GetDestinationCommand command = new GetDestinationCommand(taxi.getNode().getId().asInt());
		command.send();
	
		int thisNodeTimeInsideRegion =  taxi.getAgent().getTarget().probilityOfWalkingInside(command.getGPS());

		int largerTimeInsideRegion = 0;
		ReturnDestinyPacket timesInsideRegion = null;
		ArrayList<ReturnDestinyPacket> nosPakage = this.nosPakage;

		double closest = taxi.getAgent().getTarget().near(command.getGPS());
		ReturnDestinyPacket closestNo = null;
		
		ReturnDestinyPacket nodeInside = null;
		
		for (ReturnDestinyPacket pakage : nosPakage) {
			double distance = taxi.getAgent().getTarget().near(pakage.getGPS());
			if(closest > distance){
				closest = distance;
				closestNo = pakage;
			}
			
			int probability = taxi.getAgent().getTarget().probilityOfWalkingInside(pakage.getGPS());
			if (largerTimeInsideRegion < probability) {
				largerTimeInsideRegion = probability;
				timesInsideRegion = pakage;
			}
			
			if(taxi.getAgent().getTarget().isInside(pakage.getPosition())){
				nodeInside = pakage;
				break;
			}
		}
		
	
		if(nodeInside != null){
			this.sendAgent(taxi, nodeInside);
		}else{
			if (thisNodeTimeInsideRegion < largerTimeInsideRegion) {
				this.sendAgent(taxi, timesInsideRegion);
			}else{
				if(closestNo != null && thisNodeTimeInsideRegion == 0){
					this.sendAgent(taxi,closestNo);
				}
			}
			
		}
	}

}
