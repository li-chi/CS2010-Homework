import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;



public class TestBuff {
	public static void main(String [] args) throws IOException{
		Reader bf = new BufferedReader(new InputStreamReader(System.in));
		//Reader r = new BufferedReader(new FileReader(filename));
	    StreamTokenizer stok = new StreamTokenizer(bf);
	    stok.parseNumbers();
		int a = stok.nextToken();
		System.out.print(a);
		int b = stok.nextToken();
		System.out.print(b);
		int c = stok.nextToken();
		System.out.print(c);
		int d = stok.nextToken();
		System.out.print(d);
		
	}
}
