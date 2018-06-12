
import java.util.LinkedList;
import java.util.Iterator;

	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList  implements Graph {

	private int numOfVertices;
	private int numOfEdges;
	private LinkedList<Edge>[] edges;
	private boolean isdirected;


	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */
	    
	 public GraphAdjList(int V, boolean directed) {
	     
		this.numOfVertices = V;
		this.isdirected = directed;
		//Create new linked list of vertices;
		if (V != 0) {
			this.edges = new LinkedList[V];
			for(int i=0; i<V; i++){
				edges[i] = new LinkedList<>();
			}

		}
	 }

	 
	  // 1. IMPLEMENTATION METHOD numVerts: 
	  public int numVerts() { 

		return this.numOfVertices;
     
     }

	  // 2. IMPLEMENTATION METHOD numEdges:
	  public int numEdges() {
		  return this.numOfEdges;

	  }
	  //  3. IMPLEMENTATION METHOD addEdge:
	  public void addEdge(int v1, int v2, int w) {
		boolean Exist = false;
		try {
			for(int i =0; i < edges[v1].size(); i++){
				if (edges[v1].get(i).getVertex() == v2){
					edges[v1].get(i).setWeight(w);
					Exist = true;
				}
			}
			if(!Exist){
				//Create a new edge and add weight
				edges[v1].add(new Edge(v2, w));
				//Increment number of edges
				numOfEdges++;
				if(!isdirected && v1 != v2){
					edges[v2].add(new Edge(v1, w));
				}
			}
		}catch (IndexOutOfBoundsException ex){
			System.out.println("index out of bounds "+ex);
		}

    }
	  
	 // 4. IMPLEMENTATION METHOD removeEdge: 
	 public void removeEdge(int v1, int v2) {
		try {
			for(int i = 0; i < edges[v1].size(); i++){
				if(edges[v1].get(i).getVertex() == v2){
					edges[v1].remove(i);
					numOfEdges--;
					if(!isdirected && v1 != v2){
						for(int j=0; j< edges[v2].size(); j++){
							if(edges[v2].get(j).getVertex() == v1){
								edges[v2].remove(j);
							}
						}
					}
				}
			}
		}catch (IndexOutOfBoundsException ex){
			System.out.println("index out of bounds\n "+ex);
		}
	 }
	 
	 // 5. IMPLEMENTATION METHOD hasEdge:
	 public boolean hasEdge(int v1, int v2) {
		try {
			for (int i = 0; i < edges[v1].size(); i++){
				if(edges[v1].get(i).getVertex() == v2){
					return true;
				}
			}

		}catch (IndexOutOfBoundsException ex){
			System.out.println("Has Edge Index out of Bounds\n " + ex);
		}
		return false;
	 }

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	 public int getWeightEdge(int v1, int v2)  {
	    int weight;
	    try {
	    	for (int i=0; i < edges[v1].size(); i++){
	    		if(edges[v1].get(i).getVertex() == v2){
	    			weight = edges[v1].get(i).getWeight();
	    			return weight;
				}
			}
		}catch (IndexOutOfBoundsException ex){
			System.out.println("Get weigh Index out of bounds exception \n" + ex);
		}
		return -1;
	 }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	 public LinkedList getNeighbors(int v) {
	 	//Create a LinkedList to store all neighbors
	     LinkedList neighbours = new LinkedList();
	     if(v < edges.length) {
			 for (int i = 0; i < edges[v].size(); i++) {
				 neighbours.add(edges[v].get(i).getVertex());
			 }
			 return neighbours;
		 }
		 else
		 	return null;
	 }

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		int degree;
		int nonDirected = 0;
		if(v < numOfVertices){
			if(!edges[v].isEmpty()){
				degree = edges[v].size();
				return degree;
			}
		}
		return nonDirected;
	}

	// 9. IMPLEMENTATION METHOD toString:
	 public String toString() {
	     String colRows = "";
	     for (int i=0; i< numOfVertices; i++){
	     	colRows+= edges[i].toString() + "\n";
		 }
		 return colRows;
	 }

	}


