package br.ufla.dcc.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufla.dcc.grubix.simulator.Position;
import br.ufla.dcc.grubix.simulator.kernel.Configuration;

public class Reader {

	private final String path = "TraceMoves/";
	private int nodesLength;
	private double xSize, ySize;
	
	public Reader(Configuration config) {
		this.nodesLength = config.getNodeCount();
		this.xSize = config.getXSize();
		this.ySize = config.getYSize();
	}

	public Map<Integer, List<Position>> readFile(){
		Map<Integer, List<Position>> groupRoutes = new HashMap<Integer, List<Position>>();
		File dir = new File(path);
		double sumX = 0;
		double sumY = 0;
		int contElement = 0;
		
		for (File file : dir.listFiles()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				if (nodesLength != 0) {
					List<Position> lista = new ArrayList<Position>();
					while (in.ready()) {
						Position position = this.createPosition(in.readLine());
						sumX += position.getXCoord();
						sumY += position.getYCoord();
						contElement++;
						lista.add(position);	
					}
					groupRoutes.put(nodesLength, lista);
					nodesLength--;
				} else {
					break;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		MapsToGrubiConversor conversor = new MapsToGrubiConversor(xSize, ySize, sumX/contElement, sumY/contElement);
		conversor.normalizer(groupRoutes);
		
		return groupRoutes;
	}
	
	
	private Position createPosition(String str){
		double xCoord = Math.abs(Double.valueOf(str.split(" ")[0].trim()));
		double yCoord = Math.abs(Double.valueOf(str.split(" ")[1].trim()));
		return new Position(xCoord, yCoord);
	}
	
	
	
}

