import java.util.*;

// write your matric number here:
// write your name here:
// write list of collaborators here (reading someone's post in Facebook Group/IVLE discussion forum and using the idea is counted as collaborating):

class HospitalTour {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[][] AdjMatrix; // the graph (the hospital)
  private Vector < Integer > RatingScore; // the weight of each vertex (rating score of each room)

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  int [] visited;
  int num_of_visited;
  int [] important;
  
  public HospitalTour() {
    // Write necessary codes during construction
    //
    // write your answer here



  }

  int Query() {
    int answer = 0;

    // You have to report the rating score of the important room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no important room
    //
    // write your answer here
    important = new int[1000];
    int num_of_im = 0;
    answer = -1;
    
    if (V<=1)
    	return -1;
    
    if (V==2)
    	return -1;
    
    visited = new int[1000];
	visited[0] = 1;
	num_of_visited = 0;
	DFSrec(1);
	if (num_of_visited != (V-1))
		important[num_of_im++] = 0;
    
    for(int i=1; i<V;i++){
    	visited = new int[1000];
    	visited[i] = 1;
    	num_of_visited = 0;
    	DFSrec(0);
    	if (num_of_visited != (V-1))
    		important[num_of_im++] = i;	
    }
    
    if (num_of_im == 0)
    	return answer;
    
    answer = 100000;
    
    for (int i = 0; i<num_of_im; i++){
    	if (RatingScore.get(important[i]) < answer){
    		answer = RatingScore.get(important[i]);
    	}
    }
    return answer;
  }

  // You can add extra function if needed
  // --------------------------------------------
  void DFSrec(int u){
	  visited[u] = 1;
	  num_of_visited++;
	  for(int i = 0; i<V; i++){
		  if (AdjMatrix[u][i] == 1){
			  if (visited[i] != 1){
				  DFSrec(i);
			  }
		  }
	  }
  }


  // --------------------------------------------

  void run() {
    // you can edit this method if you need to do so
    Scanner sc = new Scanner(System.in);

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new Vector < Integer > ();
      for (int i = 0; i < V; i++)
        RatingScore.add(sc.nextInt());

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new int[V][V];
      for (int i = 0; i < V; i++) {
        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt();
          AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
        }
      }

      System.out.println(Query());
    }
  }

  public static void main(String[] args) {
    // do not alter this method
    HospitalTour ps3 = new HospitalTour();
    ps3.run();
  }
}



class IntegerPair implements Comparable {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(Object o) {
    if (!this.first().equals(((IntegerPair)o).first()))
      return this.first() - ((IntegerPair)o).first();
    else
      return this.second() - ((IntegerPair)o).second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
