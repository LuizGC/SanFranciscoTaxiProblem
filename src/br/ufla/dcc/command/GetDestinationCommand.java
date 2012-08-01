package br.ufla.dcc.command;

import java.util.List;

import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.kernel.Configuration;
import br.ufla.dcc.grubix.simulator.node.user.Command;

public class GetDestinationCommand extends Command {

	private Position destiny;
	private List<Position> GPS;
	
	private int node;
	
	public GetDestinationCommand(int node) {
		this.node = node;
	}

	public int getNode() {
		return node;
	}

	public Position getDestiny() {
		return destiny;
	}
	
	public void setDestiny(Position destiny) {
		this.destiny = destiny;
	}

	public void send(){
		Configuration.getInstance().getMovementManager().sendCommand(this);
	}

	public List<Position> getGPS() {
		return GPS;
	}

	public void setGPS(List<Position> GPS) {
		this.GPS = GPS;
	}
	
}
