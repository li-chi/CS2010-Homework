import java.util.Arrays;


public class test {
	public static void main(String [] args){
		boolean [] p = new boolean[10];
		int [][] q = new int[10][10];
		Arrays.fill(q[1], 100);
		int [][] qq = q;
		qq[1][1] = 1;
		for(int i=0;i<10;i++){
			System.out.println(p[i]);
		}
	}
}
