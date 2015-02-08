import java.util.*;

// write your matric number here:A0117472H
// write your name here:LI Chi
// write list of collaborators here (reading someone's post in Facebook Group/IVLE discussion forum and using the idea is counted as collaborating):

class Labor {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------

  private PriorityQueue < IntegerPair > pq;
  private int [] D;
  private final int IFN = Integer.MAX_VALUE;
  // --------------------------------------------

  public Labor() {
    // Write necessary codes during construction
    //
    // write your answer here
	  pq = new PriorityQueue < IntegerPair >();
	 
	  


  }

  int Query() {
    int answer = 0;

    // You have to report the shortest path from Steven and Grace's home (vertex 0)
    // to reach their chosen hospital (vertex 1)
    //
    // PS: additional constraint ONLY for Subtask 5 -> such shortest path cannot contains more than 7 vertices
    //
    // write your answer here
    initSSSP(0);
    IntegerPair temp;
    int u,v,w;
    while(!pq.isEmpty()){
    	 temp = pq.poll();
    	 u = temp.second();
    	 for(int i=0; i<AdjList.get(u).size(); i++){
    		 v = AdjList.get(u).get(i).first();
    		 w = AdjList.get(u).get(i).second();
    		 if ((D[u] + w) < D[v]){
    			 D[v] = D[u] + w;
    			 pq.offer(new IntegerPair(D[v],v));
    		 }
    	 }
    }


    //------------------------------------------------------------------------- 
    answer = D[1];
    return answer;
  }

  // You can add extra function if needed
  // --------------------------------------------

  void initSSSP(int num){
	  D = new int[V];
	  Arrays.fill(D, IFN);
	  for (int i=0; i<AdjList.get(0).size(); i++){
		  D[AdjList.get(0).get(i).first()] = AdjList.get(0).get(i).second();
		  pq.offer(new IntegerPair(AdjList.get(0).get(i).second(),AdjList.get(0).get(i).first()));
		  
	  }
  }

  // --------------------------------------------

  void run() {
    // do not alter this method, i.e. you should not need to change anything from here to in order to pass the last subtask
    Scanner sc = new Scanner(System.in);

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new Vector < Vector < IntegerPair > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new Vector<IntegerPair>());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (in minutes) is stored here
        }
      }

      System.out.println(Query());
    }
  }

  public static void main(String[] args) {
    // do not alter this method
    Labor ps5 = new Labor();
    ps5.run();
  }
}



class IntegerPair implements Comparable<IntegerPair> {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
