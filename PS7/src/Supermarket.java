import java.util.*;
import java.io.*;

// write your matric number here: A0117472H
// write your name here:LI Chi
// write list of collaborators here (reading someone's post in Facebook group/IVLE discussion forum and using the idea is counted as collaborating):

class Supermarket {
  private int N; // number of items in the supermarket. V = N+1 
  private int K; // the number of items that Steven has to buy
  private int[] shoppingList; // indices of items that Steven has to buy
  private int[][] T; // the complete weighted graph that measures the direct walking time to go from one point to another point in seconds

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  private PriorityQueue < IntegerPair > pq;
  private int [][] T2;
  private int [][] T3;
  private final int IFN = Integer.MAX_VALUE;

  private static int minCost;
  private static Vector < Integer > path;
  private static int counter;
  private static boolean[] visited;
  private int [][] costTable;
 // private Hashtable<Integer, Integer> hashCost = new Hashtable<Integer,Integer>();
  public Supermarket() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }

  int Query() {
    int answer = 0;
    
    // You have to report the quickest shopping time that is measured
    // since Steven enters the supermarket (vertex 0),
    // completes the task of buying K items in that supermarket as ordered by Grace,
    // then, reaches the cashier of the supermarket (back to vertex 0).
    //
    // write your answer here
    T2 = new int[N+1][N+1];
    T3 = new int [K+1][K+1];
    preprocess();
    costTable = new int[K+1][33000];
    answer = IFN;
    for(int i=1;i<=K;i++){
    	int cost = calCost(i,0|(1<<(i-1)))+T3[0][i];
    	answer = Math.min(answer, cost);
    }
   
    return answer;
  }

  // You can add extra function if needed
  // --------------------------------------------

  int calCost(int u,int state){
	  if (state == (1<<K)-1){
		  return T3[0][u];
	  } else if (!(costTable[u][state] == 0)){
		  return costTable[u][state];
	  } else {
		  costTable[u][state] = IFN/2;
		  for (int i=1;i<=K;i++){
			  if ((i!=u) && ((state & (1<<(i-1)))==0)){
				  int _state = state | (1<<(i-1));
				  costTable[u][state] = Math.min(costTable[u][state], T3[u][i]+calCost(i,_state));
			  } 
		  }
		  return costTable[u][state];
	  }
  }
  
  void preprocess(){
	  IntegerPair temp;
	  //boolean [] visited = new boolean[N+1];
	  int u=0;
	//  int counter = 0;
	  for(int i=0;i<=N;i++){
		  System.arraycopy(T[i], 0, T2[i], 0, N+1);
	  }
	  pq = new PriorityQueue < IntegerPair >();
	  
	  pq.offer(new IntegerPair(0,0));
	  
	  while(!pq.isEmpty()){
		  temp = pq.poll();
		  u = temp.second();
		  
		  if(temp.first() != T2[0][u])
			  continue;

		  for(int j=0;j<=N;j++){
			  if(T2[0][u] + T2[u][j] < T2[0][j]){
				  T2[j][0] = T2[0][j] = T2[0][u] + T2[u][j];
				  pq.offer(new IntegerPair(T2[0][j],j));
			  }
		  }
	  }
	  for(int i=0;i<K;i++){

		  pq = new PriorityQueue < IntegerPair >();
		  
		   pq.offer(new IntegerPair(0,shoppingList[i]));
		  
		  while(!pq.isEmpty()){
			  temp = pq.poll();
			  u = temp.second();

			  
			  if(temp.first() != T2[shoppingList[i]][u])
				  continue;
			  
			  for(int j=0;j<=N;j++){
				  if(T2[shoppingList[i]][u] + T2[u][j] < T2[shoppingList[i]][j]){
					  T2[j][shoppingList[i]] = T2[shoppingList[i]][j] = T2[shoppingList[i]][u] + T2[shoppingList[i]][j];
					  pq.offer(new IntegerPair(T2[shoppingList[i]][j],j));
				  }
			  }
		  }
	  }
	  for(int i=0;i<K;i++){
		  T3[0][i+1] = T2[0][shoppingList[i]];
		  T3[i+1][0] = T2[0][shoppingList[i]];
	  }
	  
	  for(int i=0;i<K;i++){
		  for(int j=0;j<K;j++){
			  T3[i+1][j+1] = T2[shoppingList[i]][shoppingList[j]];
		  }
	  }
	  /*
	  for(int i=0;i<=N;i++){
		  for(int j=0;j<=N;j++){
			  System.out.print(T2[i][j]+" ");
		  }
		  System.out.println();
	  }
	  
	  System.out.println();
	  
	  for(int i=0;i<=K;i++){
		  for(int j=0;j<=K;j++){
			  System.out.print(T3[i][j]+" ");
		  }
		  System.out.println();
	  }
	  */
  }

  void run() throws Exception {
    // do not alter this method to standardize the I/O speed (this is already very fast)
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      // read the information of the complete graph with N+1 vertices
      N = sc.nextInt(); K = sc.nextInt(); // K is the number of items to be bought

      shoppingList = new int[K];
      for (int i = 0; i < K; i++)
        shoppingList[i] = sc.nextInt();

      T = new int[N+1][N+1];
      for (int i = 0; i <= N; i++)
        for (int j = 0; j <= N; j++)
          T[i][j] = sc.nextInt();

      pw.println(Query());
    }

    pw.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    Supermarket ps7 = new Supermarket();
    ps7.run();
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




class IntegerScanner { // coded by Ian Leow for PS4
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }
  
  public int nextInt() {    
    int result = 0;
    try {
      int cur = bis.read();
      if(cur == -1)
        return -1;
      
      while(cur < 48 || cur > 57) {
        cur = bis.read();
      }
      while(cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }
      return result;
    }
    catch(IOException ioe) {
      return -1;
    }
  }
}
