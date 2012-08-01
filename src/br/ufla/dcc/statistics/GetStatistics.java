package br.ufla.dcc.statistics;

import br.ufla.dcc.position.CityStartPositions;


public class GetStatistics {

	private int hops = 0;
	private static GetStatistics inst = null;

	private int insideStamp = 0;
	private int outsideStamp = 0;

	public void registerTransfer() {
		hops++;
	}

	public void registerInside() {
		insideStamp++;
	}

	public void registerOutside() {
		outsideStamp++;
	}

	public static GetStatistics getInstance() {
		if (inst == null) {
			inst = new GetStatistics();
		}
		return inst;
	}

	public void generateStatistics() {
		System.out.println(hops + ";" + insideStamp + ";" + outsideStamp + ";" + CityStartPositions.getStartNode());
	}
}
