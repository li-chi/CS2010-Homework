import java.util.Vector;


public class test {
	public static void main(String [] args){
		Vector < Integer > RatingScore = new Vector < Integer > ();
		for (int i = 0; i<100000;i++)
			RatingScore.add(i);
		
		//RatingScore.add(3);
		System.out.println(RatingScore.get(11111));
		System.out.println("ja");
		
		Vector < Vector < Integer > > AdjList = new Vector < Vector < Integer > >();
		AdjList.add(new Vector< Integer >());
	    
		
	}
}
