import java.util.*;

// write your matric number here: A0117472H
// write your name here: LI Chi
// write list of collaborators here (reading someone's post in Facebook group/IVLE discussion forum and using the idea is counted as collaborating):

class SchedulingDeliveries {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
	int [] dilations = new int[5000];
	String [] women = new String[5000];
	int num_of_women = -1;
  public SchedulingDeliveries() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }
  void insert (String womanName, int dilation){
	  num_of_women++;
	  women[num_of_women] = womanName;
	  dilations[num_of_women] = dilation;
	  shiftUp(num_of_women);
  }
  
  void shiftUp(int i){
	  while (i>0 && dilations[parent(i)] < dilations[i]){
		  swapWomen(parent(i),i);
		  swapDilation(parent(i),i);
		  i = parent(i);
	  }
  }
  int parent(int i){
	  return i >> 1;
  }
  void swapWomen(int i, int j){
	  String temp = women[i];
	  women[i] = women[j];
	  women[j] = temp;
  }
  
  void swapDilation(int i, int j){
	  int temp = dilations[i];
	 dilations[i] = dilations[j];
	 dilations[j] = temp;
  }
  
  void shiftDown(int i){
	  while(i <= num_of_women){
		  int max = dilations[i];
		  int max_id = i;
		  if (left(i) <= num_of_women && max < dilations[left(i)]){
			  max = dilations[left(i)];
			  max_id = left(i);
		  }
		  if (right(i) <= num_of_women && max < dilations[right(i)]){
			  max = dilations[right(i)];
			  max_id = right(i);
		  }
		  if (max_id != i){
			  swapWomen(i,max_id);
			  swapDilation(i,max_id);
			  i = max_id;
		  }
		  else
			  break;
	  }
  }
  
  int left(int i){
	  return i*2 + 1;
  }
  
  int right(int i){
	  return i*2 + 2;
  }
  
  void ArriveAtHospital(String womanName, int dilation) {
    // You have to insert the information (womanName, dilation)
    // into your chosen data structure
    //
    // write your answer here
	insert(womanName,dilation);

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
	 shiftUp(i);


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
	 women[i] = women[num_of_women];
	 dilations[i] = dilations[num_of_women];
	 num_of_women--;
	 shiftDown(i);

  }

  String Query() {
    String answer = "The delivery suite is empty";

    // You have to report the name of the woman that the doctor
    // has to give the most attention to. If there is no more woman to
    // be taken care of, return a String "The delivery suite is empty"
    //
    // write your answer here
    if (num_of_women >= 0)
    	answer = women[0];
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
