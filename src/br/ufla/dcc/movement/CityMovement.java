package br.ufla.dcc.movement;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufla.dcc.command.GetDestinationCommand;
import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.event.Movement;
import br.ufla.dcc.grubix.simulator.kernel.Configuration;
import br.ufla.dcc.grubix.simulator.movement.MovementManager;
import br.ufla.dcc.grubix.simulator.node.Node;
import br.ufla.dcc.grubix.simulator.node.user.Command;
import br.ufla.dcc.grubix.xml.ConfigurationException;

public class CityMovement extends MovementManager {
	
	
	public void initConfiguration(Configuration config)
			throws ConfigurationException {
		super.initConfiguration(config);
	}

	private boolean isNear (Position p1, Position p2) {
		boolean result = false;
		if (p1.equals(p2))
			result = true;
		
		return result;
	}
	
	private Position interpolatedPosition(Position pinitial, Position pfinal){
		double distance = pinitial.getDistance(pfinal);
		double step = 5.0;
		double p = step/distance;
		if (p>1) p=1;
		double newx = pinitial.getXCoord() * (1-p) + pfinal.getXCoord() * p;
		double newy = pinitial.getYCoord() * (1-p) + pfinal.getYCoord() * p;
		return new Position(newx, newy);
	}
	
	public final Collection<Movement> createMoves(Collection<Node> allNodes) {
		Iterator<Node> iter = null;
		iter = allNodes.iterator();
		List<Movement> nextMoves = new LinkedList<Movement>();
		while (iter.hasNext()) {
			Node node = iter.next();
			Position next = Routes.nextMoviment(node.getId().asInt());
			
			if(this.isNear(next,node.getPosition())){
				Routes.changeMoviment(node.getId().asInt());
			}	
			
			Position newPos = this.interpolatedPosition(node.getPosition(), next);
			
			nextMoves.add(new Movement(node, newPos, 0));
		}
		
		
		return nextMoves;
	}
	
	public void sendCommand(Command c) {
		if (c instanceof GetDestinationCommand) {
			GetDestinationCommand command = (GetDestinationCommand) c;
			command.setDestiny(Routes.getDestiny(command.getNode()));
			command.setGPS(Routes.GPS(command.getNode()));
		}
	}
	
}
