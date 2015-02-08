import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


Vector<Integer> visited = new Vector<Integer>;
class IntegerPair{

}

//BFS O(V+E)
public void BFS(int s) {
	Queue<Integer> q = new LinkedList<Integer>();
	q.offer(s);
	visited.set(s, 1); 
	while (!q.isEmpty()) {
		int u = q.poll(); 
		for (int j = 0; j < AdjList.get(u).size(); j++) {
			IntegerPair v = AdjList.get(u).get(j);
			if (visited.get(v.first()) == 0) { 
				visited.set(v.first(), 1); 
				p.set(v.first(), u); 
				q.offer(v.first()); 
			}
		}
	}
}

//DFS O(V+E)
private void DFSrec(int u) {
	visited.set(u, 1);
	for (int j = 0; j < AdjList.get(u).size(); j++) {
		IntegerPair v = AdjList.get(u).get(j);
		if (visited.get(v.first()) == 0) {
			p.set(v.first(), u); 
			DFSrec(v.first()); 
		}
	}
	toposort.add(u); 
}

//Prim O(ElogV)
void Prim(){
	process(0);
	int mst_cost = 0;
	while (!pq.isEmpty()) { 
		IntegerPair front = pq.poll();
		if (!taken.get(front.second())) { 
			mst_cost += front.first();
			process(front.second());
		}
	} 
}
void process(int vtx) {
	taken.set(vtx, true);
	for (int j = 0; j < AdjList.get(vtx).size(); j++) {
		IntegerPair v = AdjList.get(vtx).get(j);
		if (!taken.get(v.first())) {
			pq.offer(new IntegerPair(v.second(), v.first())); 
		}
	}
}

// Kruskal O(ElogV)
void Kruskal() {
	UnionFind UF = new UnionFind(V); 
	int mst_cost = 0;
	for (int i = 0; i < E; i++) {
		IntegerTriple e = EdgeList.get(i);
		int u = e.second(), v = e.third(), w = e.first(); 
		if (!UF.isSameSet(u, v)) { 
			mst_cost += w; 
			UF.unionSet(u, v); 
		}
	}
}

// Bellman Ford O(VE)
void BellmanFord() {
	for (int i = 0; i < V - 1; i++) 
		for (int u = 0; u < V; u++)
			for (int j = 0; j < AdjList.get(u).size(); j++) {
				IntegerPair v = AdjList.get(u).get(j);
				relax(u, v.first(), v.second());
			}
}

//￼Modified Dijkstra O((V+E)logV)
void Dijkstra(){
	PQ.enqueue((D[s], s)) 
	while(!qp.isEmpty()){
		(d, u) = PQ.dequeue()
		if (d == D[u]) {// important check, lazy DS
			for each vertex v adjacent to u{
				if (D[v] > D[u] + weight(u, v))
					D[v] = D[u] + weight(u, v) 
					PQ.enqueue((D[v], v))
			}
		}
	}

}


// Floyd Warshal O(VVV)
void FloydWarshall(){
	for(int k=0;k<V;j++)
		for(int i=0;i<V;i++)
			for (int j=0;j<V;j++)
				AdjMat[i][j] = min(AdjMat[i][j],AdjMat[i][k]+AdjMat[k][k]);
}

// O(N*2N)
void TSP() {
	int DP_TSP(u, vis){ // vis = visited
		if 'vis' is all 1 (in binary) // all vertices have been visited
			return weight(u, 0) // no choice, return to vertex 0
		if memo[u][vis] does not equals to -1 // computed before?
			return memo[u][vis]
		memo[u][vis] = INF // general case
		for each v in V 
			if edge(u, v) exists and v is not turned on in bitmask 'vis' memo[u][vis] = min(memo[u][vis],
				weight(u, v) + DP_TSP(v, vis with v-th bit turned on);
		return memo[u][vis]
	}
}

private static int LIS(int i) {
	if (i == N - 1) return 1;
	if (memo.get(i) != -1) return memo.get(i);
	int ans = 1; // at least A[i] itself
	for (int j = i + 1; j < N; j++)
		if (A.get(i) < A.get(j))
			ans = Math.max(ans, LIS(j) + 1);
	memo.set(i, ans);
	return ans; 
}