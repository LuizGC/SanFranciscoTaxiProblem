package br.ufla.dcc;

import br.ufla.dcc.grubix.simulator.Address;
import br.ufla.dcc.wuc.decision.AgentReplicationDecisionEventWUC;
import br.ufla.dcc.wuc.decision.DecisionEvent;
import br.ufla.dcc.wuc.decision.ProbabilityDecisionEventWUC;
import br.ufla.dcc.wuc.decision.SimpleDecisionEventWUC;
import br.ufla.dcc.wuc.decision.SmartDecisionEventWUC;

public enum IntelligenceLevel {


	SIMPLE(1), PROBABILISTIC(2), SMART(3), REPLICATION(4);
	

	private final int level;
	
	private IntelligenceLevel(int level) {
		this.level = level;
	}

	public DecisionEvent create(Address sender, double delay){
		switch (level) {
		case 1:
			return new SimpleDecisionEventWUC(sender, delay);
		case 2:
			return new ProbabilityDecisionEventWUC(sender, delay);
		case 3:
			return new SmartDecisionEventWUC(sender, delay);
		case 4:
			return new AgentReplicationDecisionEventWUC(sender, delay);	
		default:
			return null;
		}
	}
}


