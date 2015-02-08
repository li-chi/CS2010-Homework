import java.util.*;

// write your matric number here: A0117472H
// write your name here: LI Chi
// write list of collaborators here (reading someone's post in Facebook group/IVLE discussion forum and using the idea is counted as collaborating):

class SchedulingDeliveries {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
	int [] dilations = new int[30000];
	String [] women = new String[30000];
	int num_of_women = 0;
  public SchedulingDeliveries() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }

  void ArriveAtHospital(String womanName, int dilation) {
    // You have to insert the information (womanName, dilation)
    // into your chosen data structure
    //
    // write your answer here
	  women[num_of_women] = womanName;
	  dilations[num_of_women] = dilation;
	  num_of_women++;


  }

  void UpdateDilation(String womanName, int increaseDilation) {
    // You have to update the dilation of womanName to
    // dilation += increaseDilation
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
	  int i ;
	  for (i = 0; i<num_of_women; i++)
	    if (women[i].equals(womanName))
	    	break;
	 dilations[i] += increaseDilation;

  }

  void GiveBirth(String womanName) {
    // This womanName gives birth 'instantly'
    // remove her from your chosen data structure
    //
    // write your answer here
	  int i ;
	  for (i = 0; i<num_of_women; i++)
	    if (women[i].equals(womanName))
	    	break;
	 dilations[i] = 0;


  }

  String Query() {
    String answer = "The delivery suite is empty";

    // You have to report the name of the woman that the doctor
    // has to give the most attention to. If there is no more woman to
    // be taken care of, return a String "The delivery suite is empty"
    //
    // write your answer here
    int maxDilation = 0;
    int maxNum = 0;
    for (int i=0; i<num_of_women; i++){
    	if (dilations[i] > maxDilation){
    		maxNum = i;
    		maxDilation = dilations[i];
    	}
    }
    if (maxDilation == 0){}
    else
    	answer = women[maxNum];
    return answer;
  }

  void run() {
    // you cannot alter this method
    // let's standardize the running speed using this Java I/O settings
    // various AC solutions from several TAs and myself are in the region of [0.3-0.4]s in Mooshak and thus the 1s time limit is generous enough

    Scanner sc = new Scanner(System.in);
    int numCMD = sc.nextInt(); // note that numCMD is >= N
    while (numCMD-- > 0) {
      int cmd = sc.nextInt();
      switch (cmd) {
        case 0: ArriveAtHospital(sc.next(), sc.nextInt()); break;
        case 1: UpdateDilation(sc.next(), sc.nextInt()); break;
        case 2: GiveBirth(sc.next()); break;
        case 3: System.out.println(Query()); break;
      }
    }
  }

  public static void main(String[] args) {
    // do not alter this method
    SchedulingDeliveries ps2 = new SchedulingDeliveries();
    ps2.run();
  }
}
