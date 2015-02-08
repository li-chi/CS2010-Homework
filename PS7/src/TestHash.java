import java.util.Arrays;
import java.util.Hashtable;


public class TestHash {
	public static void main(String [] args) {
		Hashtable<Integer, Integer> hash = new Hashtable<Integer,Integer>();
		hash.put(1,11);
		hash.put(2, 22);
		System.out.println(hash.get(2));
		int [] x = new int[33000];
		Arrays.fill(x, Integer.MAX_VALUE);
		int i = 2;
		//if(i&(1<<1))
			System.out.println("true");
	}	
}