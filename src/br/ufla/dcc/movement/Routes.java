package br.ufla.dcc.movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.kernel.Configuration;
import br.ufla.dcc.maps.Reader;

public class Routes {

	private static Routes instance;
	
	private Map<Integer, List<Position>> groupRoutes;
	private Map<Integer, Position> destiny;
	
	private Routes(){
	}
	
	private Routes(Configuration config) {
		Reader reader = new Reader(config); 
		this.destiny = new HashMap<Integer, Position>();
		this.groupRoutes = reader.readFile();
	}
	
	public static void initRoutes(Configuration config) {
		if(instance == null){
			instance = new Routes(config);
		}	
	}

	public static Position nextMoviment(int nodeId){
		Position position = instance.groupRoutes.get(nodeId).get(0);
		return position;
	}
	
	public static void changeMoviment(int nodeId){
		Position nowPosition = instance.groupRoutes.get(nodeId).get(0);
		if(getDestiny(nodeId).equals(nowPosition)){
			newDestiny(nodeId);
		}
		instance.groupRoutes.get(nodeId).remove(0);
	}
	
	private static Position newDestiny(int nodeId){
		instance.destiny.put(nodeId, instance.groupRoutes.get(nodeId).get(20));
		return instance.destiny.get(nodeId);
	}

	public static Position getDestiny(int nodeId){
		if(instance.destiny.get(nodeId) == null){
			newDestiny(nodeId);
		}
		return instance.destiny.get(nodeId);
	}
	
	public static List<Position> GPS(int nodeId){
		List<Position> retorno = new ArrayList<Position>();
		for(Position position : instance.groupRoutes.get(nodeId).subList(0, 20)){
			if(!position.equals(instance.destiny.get(nodeId))){
				retorno.add(position);
			}else{
				break;
			}
		}
		return retorno;
	}
}
