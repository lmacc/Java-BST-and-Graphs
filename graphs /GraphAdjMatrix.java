import java.util.Arrays;
import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

 
public class GraphAdjMatrix implements Graph{

	private int numOfEdges;
	private int numOfVertices;
	private boolean isDirected;
	private int[][] adjMatrix;
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
     this.numOfVertices = V;
     this.isDirected = directed;
     this.adjMatrix = new int[V][V];
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

        try{
            if((adjMatrix[v1][v2] == 0) && !(v1 > numOfVertices || v2 > numOfVertices)) {
                numOfEdges++;
                adjMatrix[v1][v2] = w;
                if(!isDirected){
                    adjMatrix[v2][v1] = w;
                }
            }
            else {
                System.out.println("Edge " + v1 + " ---- " + v2 + " exists");
            }

        }catch (IndexOutOfBoundsException ex){
            System.out.println("AddEdge out of Bounds");
        }
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2){
       int count = 0;
       try {
            if(adjMatrix[v1][v2] != 0){
                adjMatrix[v1][v2] = 0;
                numOfEdges--;
                if(!isDirected){
                    adjMatrix[v2][v1] = 0;
                }
            }

       }catch (IndexOutOfBoundsException ex){
           count++;
           System.out.println("AdjMatrix removeEdge Index Out Of Bounds \n" + count + ex);

       }

   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        boolean hasAnEdge;
        if(adjMatrix[v1][v2] != 0){
            hasAnEdge = true;
            return hasAnEdge;
        }
        else
            hasAnEdge = false;
        return hasAnEdge;
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		int weight = 0;
		int count = 0;
		try {
		    //Check for edge
            if(adjMatrix[v1][v2] != 0){
                weight = adjMatrix[v1][v2];
                return weight;
            }
            else
                return 0;
        }catch (IndexOutOfBoundsException ex){

            System.out.println("AdjMatrix getWeightEdge Index Out Of Bounds \n" + ex);
        }
        return weight;
    }

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v){
        LinkedList neighbours = new LinkedList();
        //check first if int v is less than the number of vertices
        if(v < numOfVertices){
            for (int i = 0; i < numOfVertices; i++){
                if(adjMatrix[v][i] != 0){
                    neighbours.add(i);
                }
            }
            return neighbours;
        }
        else
            return null;
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v){
	    int degree = 0;
	    if(v < numOfVertices){
	        for(int i = 0; i < numOfVertices; i++){
	            if(adjMatrix[v][i] != 0){
	                degree++;
                }
            }
            if(isDirected){
                for(int i = 0; i < numOfVertices; i++){
                    if(adjMatrix[i][v] != 0){
                        degree++;
                    }
                }
            }
        }
        return degree;
	}
	

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString(){
	   String matrix = "";
	   for (int i =0; i < adjMatrix.length; i++){
	       matrix+= Arrays.toString(adjMatrix[i]) + "\n";
           //System.out.println();
       }
	   return matrix;
    }    
}