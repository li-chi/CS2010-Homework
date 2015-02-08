import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here (reading someone's post in Facebook Group/IVLE discussion forum and using the idea is counted as collaborating):

class OutForAWalk {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

  


  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  
  private static Vector < Boolean > taken;  
  private static PriorityQueue < IntegerTriple > pq;
  int [][] max_weight;
  boolean [] has_built;
  int [] check_num;
  // --------------------------------------------

  public OutForAWalk() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }
  
  
  
  void PreProcess() {
    // write your answer here
    // you can leave this method blank if you do not need it
	  
	    max_weight = new int[10][3000];
	    has_built = new boolean [10];
	    check_num = new int[10];
	    for(int i=0;i<10;i++)
	    	check_num[i] = 1;
	    for(int i=0;i<10;i++)
	    	has_built[i] = false;
	   /*
	    int num = V<10 ? V : 10;
	    for(int i=0;i<num;i++){
	    	build(i);
	   }
	   */
  }

  int Query(int source, int destination) {
    int answer = 0;

    // You have to report the weight of a corridor (an edge)
    // which has the highest effort rating in the easiest path from source to destination for Grace
    //
    // write your answer here
    if (has_built[source] == false){
    	has_built[source] = true;
    	build(source);
    }
    
    answer = max_weight[source][destination];
    return answer;
  }

  // You can add extra function if needed
  // --------------------------------------------
  void process(int vtx){
	  taken.set(vtx, true);
	    for (int j = 0; j < AdjList.get(vtx).size(); j++) {
	      IntegerPair v = AdjList.get(vtx).get(j);
	      if (!taken.get(v.first())) {
	        pq.offer(new IntegerTriple(v.second(), v.first(),vtx));  
	      }
	    }
  }
  
  void build(int i){
	  //has_built[i] = true;
  	taken = new Vector < Boolean >(); 
	taken.addAll(Collections.nCopies(V, false));
	pq = new PriorityQueue < IntegerTriple > ();
  	max_weight[i][i] = 0;
  	process(i);
  
  	while (!pq.isEmpty() && (check_num[i] != V)) { 
  		IntegerTriple front = pq.poll();

  		if (!taken.get(front.second())) { 
  			if (front.first() > max_weight[i][front.third()])
  				max_weight[i][front.second()] = front.first();
  			else 
  				max_weight[i][front.second()] = max_weight[i][front.third()];
  			process(front.second());
  			check_num[i]++;
  		}
  	}
  }


  // --------------------------------------------

  void run() throws Exception {
    // you are much more used to Mooshak online judge strict I/O setting by now
    // you are allowed to edit this method if you think you need to do so :)
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
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
        }
      }

      PreProcess(); // you may want to use this function or leave it empty if you do not need it

      int Q = sc.nextInt();
      while (Q-- > 0)
        System.out.println(Query(sc.nextInt(), sc.nextInt()));
      System.out.println(); // separate the answer between two different graphs
    }
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    OutForAWalk ps4 = new OutForAWalk();
    ps4.run();
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



class IntegerTriple implements Comparable {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(Object o) {
    if (!this.first().equals(((IntegerTriple)o).first()))
      return this.first() - ((IntegerTriple)o).first();
    else if (!this.second().equals(((IntegerTriple)o).second()))
      return this.second() - ((IntegerTriple)o).second();
    else
      return this.third() - ((IntegerTriple)o).third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}
