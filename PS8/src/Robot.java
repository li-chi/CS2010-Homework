import java.util.*;
import java.io.*;

// write your matric number here: A0117472H
// write your name here: LI Chi
// write list of collaborators here (reading someone's post in Facebook group/IVLE discussion forum and using the idea is counted as collaborating):

class Robot {
  // all these values are global in this class
  private int M, N, A, B, K;
  private char[][] grid;
  private int [][][] cost;
  private final int EAST = 0;
  private final int SOUTH = 1;
  private final int WEST = 2;
  private final int NORTH = 3;
  
  class IntegerTriple {
	  int mm;
	  int nn;
	  int ff;
	  int kk;
	  public IntegerTriple(int m,int n,int f,int k) {
		  mm = m;
		  nn = n;
		  ff = f;
		  kk = k;
	  }
  }
  
  private int Query() {
    // write your answer here
	  cost = new int[M][N][4];
	  BFS();
	  int answer = Integer.MAX_VALUE;
	  for (int i=0;i<4;i++){
		  if (cost[A][B][i] == 0)
			  continue;
		  if (cost[A][B][i] < answer)
			  answer = cost[A][B][i];
	  }
	  
	  if(answer == Integer.MAX_VALUE)
		  answer = -1;
	  return answer;
  }
  
  public void BFS() {
	    Queue < IntegerTriple > q = new LinkedList < IntegerTriple > ();
	    IntegerTriple s = new IntegerTriple(0,0,0,0);
	    q.offer(s);
	    while (!q.isEmpty()) {
	      IntegerTriple u = q.poll(); // queue: layer by layer!
	      IntegerTriple v = moveForward(u);
	      if(v != null){
	    	  if(cost[v.mm][v.nn][v.ff] == 0) {
	    		  q.offer(v);
	    		  cost[v.mm][v.nn][v.ff] = cost[u.mm][u.nn][u.ff]+3;
	    	  }
	      }
	      
	      if (cost[u.mm][u.nn][turn(u.ff)] == 0 && u.kk<K){
	    	  IntegerTriple w = new IntegerTriple(u.mm,u.nn,turn(u.ff),u.kk+1);
		      q.offer(w);
		      cost[w.mm][w.nn][w.ff] = cost[u.mm][u.nn][u.ff]+2;
	      }
	   }
  }
  
  int turn(int x){
	  if(x==3)
		  return 0;
	  else 
		  return x+1;
  }
  
  IntegerTriple moveForward(IntegerTriple x) {
	  int m = x.mm;
	  int n = x.nn;
	  switch(x.ff) {
	  case EAST: n=n+1; break;
	  case SOUTH: m=m+1; break;
	  case WEST: n=n-1; break;
	  case NORTH: m=m-1; break;
	  }
	  if (m<=M-1 && m>=0 && n<=N-1 && n>=0) {
		  if(grid[m][n] == '.')
			  return new IntegerTriple(m,n,x.ff,x.kk);
		  else return null;
	  } else 
		  return null;  
  }
  
  void run() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int i, j, TC = Integer.parseInt(br.readLine());

    while (TC-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken()); B = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      grid = new char[M][N];
      for (i = 0; i < M; i++)
        grid[i] = br.readLine().toCharArray();

      pr.println(Query());
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    Robot ps8 = new Robot();
    ps8.run();
  }
}
