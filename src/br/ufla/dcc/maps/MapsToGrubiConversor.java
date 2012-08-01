package br.ufla.dcc.maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.ufla.dcc.grubix.simulator.Position;

public class MapsToGrubiConversor {

	private double maiorX, menorX, maiorY, menorY, xSize, ySize;
	
	public MapsToGrubiConversor(double xSize, double ySize, double mediaX, double mediaY){
		this.xSize = xSize;
		this.ySize = ySize;
		maiorX = mediaX + 0.04;
		menorX = mediaX -0.038;
		maiorY = mediaY +0.02;
		menorY = mediaY -0.019;
	}
	
	public void normalizer(Map<Integer, List<Position>> groupRoutes){
		for(int idNode : groupRoutes.keySet()){
			List<Position> moves = new ArrayList<Position>();
			for(Position position : groupRoutes.get(idNode)){
				if(position.getXCoord() <  this.maiorX && position.getXCoord() >  this.menorX)
					if(position.getYCoord() < this.maiorY && position.getYCoord() >  this.menorY)
						moves.add(this.googlePositionToGrubi(position));
			}
			groupRoutes.put(idNode, moves);
		}
	}
	
	private Position googlePositionToGrubi(Position position) {
		double xSet = (xSize) / (maiorX - menorX);
		double ySet = (ySize) / (maiorY - menorY);
		return new Position((position.getXCoord() - menorX) * xSet,
				(position.getYCoord() - menorY) * ySet);
	}
	
}
