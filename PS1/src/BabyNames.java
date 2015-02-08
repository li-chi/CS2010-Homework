import java.util.*;
import java.io.*;

// write your matric number here: A0117472H
// write your name here: LI Chi
// write list of collaborators here (reading someone's post in IVLE discussion forum/Facebook group and using the idea is counted as collaborating):

class BabyNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------
	class baby{
		baby left, right, parent;
		String name;
		int gender;
		int height;
	} 
	baby root = null;
	int COUNT = 0;
  // --------------------------------------------

 public BabyNames() {
    // Write necessary codes during construction;
    //
    // write your answer here

    // --------------------------------------------

    // --------------------------------------------
  }

 int findHeight(baby tempBaby){
	 if (tempBaby == null)	
		 return -1;
	 else
		 return tempBaby.height;
 }
 
 int max(int height1, int height2){
	 return height1 > height2 ? height1 : height2;
 }
 
 baby newBaby(String Name, int gender){
	 baby newBaby = new baby();
	 newBaby.name =  Name;
	 newBaby.gender = gender; 
	 newBaby.right = null;
	 newBaby.left = null;
	 newBaby.parent = null;
	 newBaby.height = 0;
	 return newBaby;
 }
 
 baby rotateLeft (baby T){
	 baby w = T.right;
	// w.parent = T.parent;
	// T.parent = w;
	 T.right = w.left;
//	 if (w.left != null)
	//	 w.left.parent = T;
	 w.left = T;
	 T.height = max(findHeight(T.left),findHeight(T.right));
	 w.height = max(findHeight(w.left),findHeight(w.right));
	 return w;
 }
 
 baby rotateRight(baby T){
	 baby w = T.left;
	 //w.parent = T.parent;
	 //T.parent = w;
	 T.left = w.right;
	 //if (w.right != null)
		// w.right.parent = T;
	 w.right = T;
	 T.height = max(findHeight(T.left),findHeight(T.right));
	 w.height = max(findHeight(w.left),findHeight(w.right));
	 return w;
 }
 
 baby rotateLeftRight(baby a){
	 a.left = rotateLeft(a.left);
	 return rotateRight(a);
 }
 
 baby rotateRightLeft(baby a){
	 a.right = rotateRight(a.right);
	 return rotateLeft(a);
 }
 
 int bf(baby T){
	 return findHeight(T.left) - findHeight(T.right);
 }
 
 baby insert(String name, int gender, baby tempBaby){
	 if (tempBaby == null){
		 return newBaby(name,gender);
	 }
	 else if (name.compareTo(tempBaby.name)<0){
	 	 tempBaby.left = insert(name,gender,tempBaby.left);
		 tempBaby.left.parent = tempBaby;
		 if (bf(tempBaby) == 2){
			 if (name.compareTo(tempBaby.left.name) < 0)
				 tempBaby = rotateRight(tempBaby);
			 else{
				 //rotateLeftRight(tempBaby);
				 tempBaby.left = rotateLeft(tempBaby.left);
				 tempBaby = rotateRight(tempBaby);
			 }
		 }
	 }
	 else{
		 tempBaby.right = insert(name,gender,tempBaby.right);
		 tempBaby.right.parent = tempBaby;
		 if (bf(tempBaby) == -2){
			 if (name.compareTo(tempBaby.right.name) > 0)
				 tempBaby = rotateLeft(tempBaby);
			 else{
				 //tempBaby = rotateRightLeft(tempBaby);
				 tempBaby.right = rotateRight(tempBaby.right);
				 tempBaby = rotateLeft(tempBaby);
			 }
		 }
	 }
	 tempBaby.height = 1 + max(findHeight(tempBaby.left), findHeight(tempBaby.right));
	 return tempBaby;
 }
 
 int count (baby checkBaby, String START, String END, int genderPreference){
	 if (checkBaby == null) return 0;
	 if ((checkBaby.name.compareTo(START)>=0 && checkBaby.name.compareTo(END)<0) 
	 && (checkBaby.gender == genderPreference || genderPreference == 0))
			return 1 + count(checkBaby.right,START,END,genderPreference)
			+ count(checkBaby.left,START,END,genderPreference);
	 else 
		 	return count(checkBaby.right,START,END,genderPreference)
				+ count(checkBaby.left,START,END,genderPreference);
 }
 
 
 void postorderCheck(baby checkBaby, String START, String END, int genderPreference) {
	if (checkBaby == null)
		return;
	System.out.print(checkBaby.name);
	postorderCheck(checkBaby.left,START,END,genderPreference);
	postorderCheck(checkBaby.right,START,END,genderPreference);
	if ((checkBaby.name.compareTo(START)>=0 && checkBaby.name.compareTo(END)<0) && (checkBaby.gender == genderPreference || genderPreference == 0))
		COUNT++;
	//System.out.print(checkBaby.name);
 }
 void AddSuggestion(String babyName, int genderSuitability) {
    // You have to insert the information (babyName, genderSuitability)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
	 
	 root = insert (babyName,genderSuitability,root);
	 // --------------------------------------------
  }

 int Query(String START, String END, int genderPreference) {
   int count = 0;
 COUNT = 0;
    // You have to answer how many baby names starts
    // with prefix that is inside query interval [START..END)
    //
    // write your answer here
 	
    // --------------------------------------------
 postorderCheck(root,START,END,genderPreference);
 //count = count(root,START,END,genderPreference);
   //System.out.println(count);
    // --------------------------------------------
count = COUNT;
 System.out.println(count);
 return count;
  }
 
 void run() throws Exception {
	    // do not alter this method to avoid unnecessary errors with the automated judging
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	    while (true) {
	      StringTokenizer st = new StringTokenizer(br.readLine());
	      int command = Integer.parseInt(st.nextToken());
	      if (command == 0) // end of input
	        break;
	      else if (command == 1) // Add Suggestion
	        AddSuggestion(st.nextToken(), Integer.parseInt(st.nextToken()));
	      else // if (command == 2) // Query
	        pr.println(Query(st.nextToken(),      // START
	                         st.nextToken(),      // END
	                         Integer.parseInt(st.nextToken()))); // GENDER
	    }
	    pr.close();
	  }

	  public static void main(String[] args) throws Exception {
	    // do not alter this method to avoid unnecessary errors with the automated judging
	    BabyNames ps1 = new BabyNames();
	    ps1.run();
	  }
	}

