package it.polito.tdp.PremierLeague.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	Graph<Player,DefaultWeightedEdge> grafo;
	private PremierLeagueDAO dao;

	public Model() {
		this.dao=new PremierLeagueDAO();
		
	}
	
	public Graph<Player,DefaultWeightedEdge> creaGrafo(double mediaGol){
		
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.mediaGoal(mediaGol));
		
		for (Adiacenza a :dao.getAdiacenze(mediaGol)) {
			if(a.getPeso()>0) {
				Graphs.addEdgeWithVertices(grafo, a.getP1(), a.getP2(), a.getPeso());
			}
			else {
				if(a.getPeso()<0) {
					Graphs.addEdgeWithVertices(grafo, a.getP2(), a.getP1(), -a.getPeso());
				}
			}
			
		}
		
		
		return grafo;
	}
	
	

}
