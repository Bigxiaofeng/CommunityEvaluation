package io.graph.gml;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.commons.collections15.Transformer;
import org.python.modules.errno;

import edu.uci.ics.jung.graph.Graph;
import io.graph.GraphOutputStream;


/**
 * @author Reihaneh Rabbany (rabbanyk@ualberta.ca)
 *
 * http://www.fim.uni-passau.de/en/fim/faculty/chairs/theoretische-informatik/projects.html
 * https://gephi.org/users/supported-graph-formats/gml-format/
 * 
graph
[
  node
  [
   id A
   label "Node A"
  ]
  node
  ...
   edge
  [
   source B
   target A
   label "Edge B to A"
  ]
  edge
  ...
]
 * 
 * 
 */
public class GMLGraphWriter<V,E> extends GraphOutputStream<V, E>{

	/*
	graph
		[
		...
		]
	 */
	protected String graphMetaData(Graph<V, E> graph){
		return "graph\n[\n";
	}
	protected String graphEnd(){
		return "]\n";
	}
	
/*
 *  node
  [
   id A
   label "Node A"
  ]
 */
	//Attributes with more than one value, will be written as attKey {val1,val2,...}
	protected String formatVetice(V v1,Transformer<V, Integer> vertex_Ids,Map<V, HashMap<Object,Vector<Object>>> vertex_attributes){
		String res = "node\n[\n\tid "+ (vertex_Ids!=null?vertex_Ids.transform(v1):v1.hashCode());
		if(vertex_attributes!=null){
			Map<Object, Vector<Object>> attributes = vertex_attributes.get(v1);
			for(Object att : attributes.keySet()){
				res += "\n\t"+ att;
				Vector<Object> values = attributes.get(att);
				if (values.size()>1) res +=" {";
				for (int i = 0; i < values.size(); i++) {
//					for (Object value :values ){
					Object value = values.get(i);
					String marker = (value instanceof String)?"\"":"";
					res += ((values.size()>1)?(i!=0?",":""):" ") +  marker + value  +marker;
				}
				if (values.size()>1) res +="} ";
			}
		}
		
		res +="\n]\n";
 		return res; 
 	}
	
/*
 edge
  [
   source B
   target A
   label "Edge B to A"
  ]
  */
	protected	String formatEdge(int v1, int v2 ,String weight){
		return ("edge\n[\n\tsource "+(v1) + "\n\ttarget " + (v2)+
				(weight==null?"": ("\n\tvalue " +weight)) 	+ "\n]\n");
	}

	
}
