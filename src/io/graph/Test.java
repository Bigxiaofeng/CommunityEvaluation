package io.graph;

import io.graph.gml.GMLGraphWriter;
import io.graph.pairs.PairsGraphWriter;
import io.graph.pajek.PajekGraphWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class Test {

	//Testing
	public static void main(String[] args){
//		String tmp =" 1 \"1\" a 0 b {2,3}";
//		String[] vals = tmp.trim().split("[,{}\\s]+");
//		for (String val:vals)
//			System.err.println(val);

		Graph<String, String> g = new SparseGraph<String, String>() ;
		g.addEdge("ab","a", "b");
		g.addEdge("bc","b", "c");
		g.addEdge("ac","a", "c");
		g.addEdge("ad", "a", "d");
		g.addEdge( "de","d", "e");
		final Map<String, Integer> ids = new HashMap<String, Integer>();
		ids.put("a", 1);
		ids.put("b", 2);
		ids.put("c", 3);
		ids.put("d", 4);
		ids.put("e", 5);
		
		final Map<String, Double> weights = new HashMap<String, Double>();
		weights.put("ab", 1.);
		weights.put("bc", 2.);
		weights.put("ac", 3.);
		weights.put("ad", 4.);
		weights.put("de", 5.);
		
		try {
			GraphOutputStream<String, String> graphWriter = new PairsGraphWriter<String, String>()	;
			graphWriter.writeGraph("testGraphF.wpairs", g , ids , weights, "%.0f");
			 graphWriter = new GMLGraphWriter<String, String>()	;
			graphWriter.writeGraph("testGraphF.gml", g , ids , weights, "%.0f");
			 graphWriter = new PajekGraphWriter<String, String>()	;
			graphWriter.writeGraph("testGraphF.net", g , ids , weights, "%.0f");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
