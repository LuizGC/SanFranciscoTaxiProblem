package br.ufla.dcc.agent;

import br.ufla.dcc.target.TargetRegion;

public class Agent {
	
	private TargetRegion target;
	
	public Agent(TargetRegion t) {
		target = t;
	}
	
	public TargetRegion getTarget() {
		return target;
	}
	
}
