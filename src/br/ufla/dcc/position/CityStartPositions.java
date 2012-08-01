package br.ufla.dcc.position;


import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.kernel.Configuration;
import br.ufla.dcc.grubix.simulator.movement.StartPositionGenerator;
import br.ufla.dcc.grubix.simulator.node.Node;
import br.ufla.dcc.grubix.xml.ConfigurationException;
import br.ufla.dcc.movement.Routes;


public class CityStartPositions extends StartPositionGenerator {
	
	private static int NODE_ID;
	
	public final static int getStartNode(){
		return NODE_ID;
	}
	
	@Override
	public void initConfiguration(Configuration config)throws ConfigurationException {
		super.initConfiguration(config);
		Routes.initRoutes(config);
		NODE_ID = (int)(Math.random() *config.getNodeCount());
	}
	
	@Override
	public final Position newPosition(Node node) {
		Position position = Routes.nextMoviment(node.getId().asInt());
		node.setPosition(position);
		Routes.changeMoviment(node.getId().asInt());
		return position;
	}

}
