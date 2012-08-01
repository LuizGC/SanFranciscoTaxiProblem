package br.ufla.dcc.wuc;


import br.ufla.dcc.command.GetDestinationCommand;
import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.grubix.simulator.NodeId;
import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.kernel.SimulationManager;
import br.ufla.dcc.node.TaxiLayer;
import br.ufla.dcc.packet.FindDestinyPacket;
import br.ufla.dcc.statistics.GetStatistics;

public class TargetRegionCheckerWUC extends WakeUpCallTask {

	public TargetRegionCheckerWUC(Address sender, double delay) {
		super(sender, delay);
	}

	public TargetRegionCheckerWUC(Address sender) {
		super(sender);
	}

	@Override
	public void executeTask(TaxiLayer taxi) {
		GetDestinationCommand command = new GetDestinationCommand(taxi.getNode().getId().asInt());
		command.send();
		Position pos = command.getDestiny();
		
		if (taxi.getAgent() != null){	
			//System.out.println("com agente " + taxi.getNode());
			SimulationManager.logNodeState(taxi.getNode().getId(), "WithAgent", "int", "0");

			if (!taxi.getAgent().getTarget().isInside(taxi.getNode().getPosition()))
				GetStatistics.getInstance().registerOutside();
			else
				GetStatistics.getInstance().registerInside();

			if (!taxi.getAgent().getTarget().isInside(taxi.getNode().getPosition()))
				if (!taxi.getAgent().getTarget().isInside(pos)){
					//System.out.println("Envia pacote");
					taxi.sendPacket(new FindDestinyPacket(taxi.getSender(), NodeId.ALLNODES));
				}

		} else {
			SimulationManager.logNodeState(taxi.getNode().getId(), "WithAgent", "int", "1");
		}

		TargetRegionCheckerWUC uc = new TargetRegionCheckerWUC(taxi.getSender(), 1000);
		taxi.sendEventSelf(uc);
		
	}

}
