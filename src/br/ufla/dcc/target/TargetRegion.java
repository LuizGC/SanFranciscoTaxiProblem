package br.ufla.dcc.target;

import java.util.List;

import br.ufla.dcc.grubix.simulator.Position;

public class TargetRegion {
	

	private Position startCorner;
	private Position endCorner;
	
	public TargetRegion (Position startCorner, Position endCorner) {
		this.startCorner = startCorner;
		this.endCorner = endCorner;
	}
	
	public TargetRegion() {
		this.startCorner = new Position(80, 40);
		this.endCorner =  new Position(90, 50);
	}

	public boolean isInside(Position p) {
		double x = p.getXCoord();
		double y = p.getYCoord();
		Position c1 = startCorner;
		Position c2 = endCorner;
		if (c1.getXCoord() <= x && c2.getXCoord() >= x) {
			if (c1.getYCoord() <= y && c2.getYCoord() >= y)
				return true;
		}
		return false;
	}
	
	public int probilityOfWalkingInside(List<Position> destiny){
		int cont = 0;
		for (Position p: destiny){
			
			if (this.isInside(p)){
				cont++;
			}
		}
		return cont;
	}
	
	public int probilityOfWalkingInside(Position destiny, Position nowPosition){
		int cont = 0;
		if(destiny != null)
		for (double step =0; step<1; step+=0.01){
			double x1 = destiny.getXCoord();
			double y1 = destiny.getYCoord();
			double x2 = nowPosition.getXCoord();
			double y2 = nowPosition.getYCoord();
			
			double x = (1 - step)*x1 + step*x2;
			double y = (1 - step)*y1 + step*y2;
			Position p = new Position(x, y);
			
			if (this.isInside(p)){
				cont++;
			}
		}
		return cont;
	}
	
	public double near(List<Position> destiny){
		double mediaX = (startCorner.getXCoord() + endCorner.getXCoord())/2;
		double mediaY = (startCorner.getYCoord() + endCorner.getYCoord())/2;
		Position center = new Position(mediaX, mediaY);
		Position retorno = new Position(Double.MAX_VALUE, Double.MAX_VALUE);
		for(Position position: destiny){
			if(center.getDistance(retorno) > center.getDistance(position)){
				retorno = position;
			}
		}
		return center.getDistance(retorno);
	}


}
