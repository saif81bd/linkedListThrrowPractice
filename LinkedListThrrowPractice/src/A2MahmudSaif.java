
import java.util.Random;

/**
 * COMP 2140   		SECTION A01
 * INSTRUCTOR   	Cuneyt Akcora
 * Assignment       2
 * @author			Saif Mahmud
 * @id				7808507
 * @version      	02/23/2020
 *
 * PURPOSE: To implement Link list merge, fix and conversion methods.
 */

public class A2MahmudSaif
{
  public static void main(String[] args )
  {
    //Send input parameters to your method. In run/edit configurations, use program arguments and enter "1500 1300"
    int list1Size = Integer.parseInt(args[0]);//1500;
    int list2Size = Integer.parseInt(args[1]);//1300;
    
    Random r1 = new Random();
    LinkedList list1 = new LinkedList();
    for(int i = 0; i< list1Size; i++){
      int randomInt = r1.nextInt(list1Size);
      
      list1.insertValue(randomInt);
    }
    LinkedList list2 = new LinkedList();
    for(int i = 0; i< list2Size; i++){
      int randomInt = r1.nextInt(list2Size);
      list2.insertValue(randomInt);
    }
    list1.quickSort();
    list2.quickSort();

    System.out.println( "\nCOMP 2140 Assignment 2 linked list questions" );
    System.out.println( "\nCurrently both List1 and List2 are circular." );
    System.out.println( "\nWe insert nodes or values to the circular version only." );
    System.out.println( "\nOtherwise we would need to rewrite the insert function." );
    list1.add(list2);
    int size = list1Size + list2Size;
    System.out.println("List1 is expanded: "+(list1.getSize()== size));
    System.out.println("List1 is in order: "+ list1.isSorted());
    System.out.println("List2 is in order: "+ list2.isSorted());
    System.out.println("Type of List1 is Circular:"+list1.isCircular());

    list1.convertCircularToOrdinary();
    System.out.println("Type of List1 is Ordinary:"+list1.isOrdinary());
    list1.deleteOddNodes();
    System.out.println("Odd nodes are deleted:"+list1.getSize()+" nodes remain");
    list1.convertOrdinaryToCircular();
    System.out.println("Type of List1 is Circular:"+list1.isCircular());
    list1.convertCircularToOrdinary();//again, in order to add dummies.
    list1.addDummies();
    System.out.println("List1 has dummies:"+list1.hasDummies());

    list1.reset();//back to a circular list
    int[] arr = new int[size];

    //testInsertion below is overloaded. It has multiple
    // versions each with a different signature.
    //In your IDE, if you click on the function name while holding the CTRL key
    //the cursor will move to the function declaration
    long secondsList = testInsertion(size, list1);
    long secondsArray = testInsertion(size, arr);
    System.out.println("Insertions take "+secondsArray+" (array) "+(secondsList)+"(list) nsecs");

    list1.reset();
    long t1 = System.nanoTime();
    int runs = 1000000;
    for(int i = 0; i< runs; i++){
      insertValue(list1,i);
    }
    System.out.println(list1.getSize() + " is the list size");
    t1 = System.nanoTime()-t1;
    System.out.println("Linked list new values take "+(t1)+" nsecs");
    arr = new int[size];

    //insertvalue is overloaded.
    t1= System.nanoTime();
    for(int i = 0; i< runs; i++){
      arr = insertValue(arr,i,i);
    }
    t1 = System.nanoTime()-t1;
    System.out.println("Array new values take "+(t1)+" nsecs");

    
    //corrupt the linked list at one position
    int index = r1.nextInt(list1.getSize()/2);// corrupted point is in the first half of the list1
    list1.convertCircularToOrdinary();//ordinary linked list, with no duplicates (even the values are in sorted order).
    System.out.println("Type of List1 is Ordinary:"+list1.isOrdinary());
    System.out.println("List is originally corrupt: "+list1.findCorruption());
    int valueAtCorruption = list1.corrupt(index);

    boolean foundValue = list1.findCorruption();

    System.out.println("Corruption at index "+index+" (value:"+valueAtCorruption+") was found: "+(foundValue));
    System.out.println( "Program ends." );

  } // end main



  /*
  Insert new integers into the list (you can insert 1 every time) 'size' times, and return the time
  it takes to complete these insertions.
   */
  private static long testInsertion(int size, LinkedList list1) {
    long nsecs = System.nanoTime();
    	for(int i = 0; i < size; i++)
    	{
    		list1.insertValue(1);
    	}
    long end = System.nanoTime();
    return end - nsecs;
  }

  /*
  Insert new integers into the array (you can insert 1 every time) 'size' times, and return the time
  it takes to complete these insertions.
   */
  private static long testInsertion(int size, int[] arr) {
    long nsecs = System.nanoTime();
    if (size <= arr.length)
    {
    	for(int i = 0; i < size; i++)
    	{
    		arr[0] = 1;
    	}
    } else {
    	System.out.println("Size is larger then the Array Length");
    }
    long end = System.nanoTime();
    return end - nsecs;
  }
  /*
    insertValue: insert the given value into the array. If the array size is exceeded,
    you must create a new array (1.5 size of the current array, if arr size is 100,
    create a new arr for 150 values) and copy the old array into the new array.
    Afterwards, insert the new item value to the new array
     */
  private static int[] insertValue(int[]arr, int i, int index) {
	  int[] out = arr;
	  int len = arr.length;

	  if(index < arr.length)
	  {
		  arr[index] = i;
	  } else {
		  out = new int[len + (len + len/2)];
		  System.out.println("New array created. arr.size: "+ len + ", newArr.size: " + out.length);
		  System.arraycopy(arr, 0, out, 0, len);
		  out[index] = i;
	  }
	 return out;
    //everytime you create a new array, printout a log with arr.size and newarray.size
  }
  /*
    insertValue: insert the given value into the link list. We could use the list.insert()
    but the goal of this exercise is to show the dynamic nature of lists.
     */
  private static void insertValue(LinkedList list1, int i) {
	  list1.insertValue(i);
  }

} // end class Assignment2

