import java.util.*;

// write your matric number here:A0117472H
// write your name here:LI Chi
// write list of collaborators here (reading someone's post in Facebook group/IVLE discussion forum and using the idea is counted as collaborating):

class Caesarean {
  private int V; // number of vertices in the graph (steps of a Caesarean section surgery)
  private int E; // number of edges in the graph (dependency information between various steps of a Caesarean section surgery)
  private Vector < IntegerPair > EL; // the unweighted graph, an edge (u, v) in EL implies that step u must be performed before step v
  private Vector < Integer > estT; // the estimated time to complete each step

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  private int [] time;
  private Vector < Vector <Integer> > AdjList;
  private int [] visited;
  private int [] topo;
  private int num_visited;
  // --------------------------------------------

  public Caesarean() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }

  int Query() {
    int answer = 0;

    // You have to report the quickest time to complete the whole Caesarean section surgery
    // (from vertex 0 to vertex V-1)
    //
    // write your answer here
    time = new int[V];
    AdjList = new Vector < Vector <Integer> >();
    num_visited = 0;
    topo = new int[V];
    time[0] = estT.get(0);
    initialList();
    topoSort();
    
    for (int i=0;i<V;i++){
    	for(int j=0;j<AdjList.get(topo[i]).size();j++){
    		stretch(topo[i],AdjList.get(topo[i]).get(j));
    	}
    }
    answer = time[V-1];
    return answer;
    
  }
	
  // You can add extra function if needed
  // --------------------------------------------
  void stretch(int a, int b) {
	  int c = time[a] + estT.get(b);
	  if (c > time[b]) {
		  time[b] = c;
	  }
  }
  
  void initialList() {
	  for(int i=0; i<V;i++){
		  AdjList.add(new Vector<Integer>());
	  }
	  for(int i=0; i<EL.size();i++){
	    	AdjList.get(EL.get(i).first()).add(EL.get(i).second());
	    }
  }
  
  void topoSort() {
	  visited = new int[V];
	  Arrays.fill(visited, 0);
	  DFS(0);
  }
  
  void DFS (int u){
	  visited[u] = 1;
	  for (int i=0; i<AdjList.get(u).size();i++){
		  if(visited[AdjList.get(u).get(i)] == 0){
			  DFS(AdjList.get(u).get(i));
		  }
	  }
	  topo[V-1-num_visited] = u;
	  num_visited++;
  }

  // --------------------------------------------

  void run() {
    // do not alter this method, yes, do NOT alter this method to standardize the runtime for I/O
    // any required changes has to be done in other functions!
    Scanner sc = new Scanner(System.in);

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt(); E = sc.nextInt(); // read V and then E

      estT = new Vector < Integer > ();
      for (int i = 0; i < V; i++)
        estT.add(sc.nextInt());

      // clear the graph and read in a new graph as an unweighted Edge List (only using IntegerPair, not IntegerTriple)
      EL = new Vector < IntegerPair > ();
      for (int i = 0; i < E; i++)
        EL.add(new IntegerPair(sc.nextInt(), sc.nextInt())); // just directed edge (u -> v)

      System.out.println(Query());
    }
  }

  public static void main(String[] args) {
    // do not alter this method
    Caesarean ps6 = new Caesarean();
    ps6.run();
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
