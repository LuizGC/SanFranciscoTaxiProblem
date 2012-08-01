package br.ufla.dcc.node;

import br.ufla.dcc.agent.Agent;
import br.ufla.dcc.grubix.simulator.LayerException;
import br.ufla.dcc.grubix.simulator.event.Finalize;
import br.ufla.dcc.grubix.simulator.event.Packet;
import br.ufla.dcc.grubix.simulator.event.StartSimulation;
import br.ufla.dcc.grubix.simulator.event.TrafficGeneration;
import br.ufla.dcc.grubix.simulator.event.WakeUpCall;
import br.ufla.dcc.grubix.simulator.node.ApplicationLayer;
import br.ufla.dcc.packet.PacketTask;
import br.ufla.dcc.position.CityStartPositions;
import br.ufla.dcc.statistics.GetStatistics;
import br.ufla.dcc.target.TargetRegion;
import br.ufla.dcc.wuc.TargetRegionCheckerWUC;
import br.ufla.dcc.wuc.WakeUpCallTask;
import br.ufla.dcc.wuc.decision.DecisionEvent;

public class TaxiLayer extends ApplicationLayer {

	private Agent agent;
	
	public Agent getAgent(){
		return this.agent;
	}
	
	public void setAgent(Agent agent){
		this.agent = agent;
	}


	@Override
	public int getPacketTypeCount() {
		return 1;
	}
	
	@Override
	public void processEvent(TrafficGeneration tg) {
	}

	@Override
	public void lowerSAP(Packet packet) throws LayerException {

		if (packet.getSender() != sender) {
			((PacketTask) packet).execute(this);
		}
		
	}

	protected void processEvent(StartSimulation start) {
		if (this.node.getId().asInt() == CityStartPositions.getStartNode()){
			this.agent = new Agent(new TargetRegion());
		}
	
		TargetRegionCheckerWUC wuc = new TargetRegionCheckerWUC(sender, 1000);
		this.sendEventSelf(wuc);
	}

	protected void processEvent(Finalize finalize) {
		if (this.node.getId().asInt() == 1)
			GetStatistics.getInstance().generateStatistics();
	}

	public void processWakeUpCall(WakeUpCall wuc) {
		try{
			if(!(wuc instanceof DecisionEvent) || this.getAgent() != null)
				((WakeUpCallTask) wuc).executeTask(this);
		}catch (Exception e) {
			//System.out.println("Pacotes com problemas = " + wuc.getReceiver() + " qual o problema " + wuc.getClass().getCanonicalName());
			//e.printStackTrace();
		}
	}
}
