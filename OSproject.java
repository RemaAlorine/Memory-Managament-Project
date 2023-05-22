

// FINAL COPY

import java.io.*;
import java.util.Scanner;

public class OSproject {
      	
    public static Partition array[];	
    public static void main(String[] args) {
        
    Scanner read = new Scanner( System.in );
		int numofPart;
		char alo;
		int start ;
		int end ;         
		System.out.println("Enter number of partitions: ");
		numofPart = read.nextInt();
		array = new Partition [numofPart]; 

		start = 0;
		
		for( int i = 0 ; i < numofPart ; i++) {
		System.out.println("Enter the size of partition " + (i+1) + " in KB: ");
		int s = read.nextInt();
		end = start + s - 1;//-1
		//set size of partition here
		array[i] = new Partition(s,start,end);
		start=end+1;

		}
		
		System.out.println("Enter allocation strategy ( F:First-fit , B:Best-fit, W;Worst-fit ): ");
		alo = read.next().charAt(0);
		
		//create memory first 
		
		int x;
		do {
			System.out.println("How do you want to manage your memory?");
			System.out.println("(1) Allocate a block of memory.");
			System.out.println("(2) De-allocate a block of memory.");
			System.out.println("(3) Report detailed information about regions of free and allocated memory blocks.");
			System.out.println("(4) Exit.");
			x = read.nextInt();

			
			switch(x) {

			case 1:
				// input process ID 
				System.out.println("Enter ID of process: ");
				String PID = read.next();
				// input process size
				System.out.println("Enter the size of process: ");
				int SP = read.nextInt();
				//allocating method
				switch(alo) {
					case 'F': //call method
                                            First_fit(PID, SP); 
                                            break;
					case 'B': // 
                                            Best_fit(PID, SP);
                                            break;

					case 'W': // 
                                            Worst_fit(PID, SP);
                                            break; 
				}
				break;
			case 2:
				// input process ID to de allocate 
				System.out.println("Enter ID of process: ");
				PID = read.next();
            boolean check=false;
                        //de-allocating method
                        for (Partition array1 : array) {
                            if (array1.PID.equals(PID)) {
                                array1.deallocate();
                                check=true;
                    
                                break ; 
                            }
                        }
                        if(check)
                        System.out.println("deallocating process complete");
                        else
                        System.out.println("deallocating process not complete!!");
                        
                        displayState();


                        
				break;
			case 3:
                            try{
                            File of=new File("Report.txt");
                            FileOutputStream sf=new FileOutputStream(of);
                            PrintWriter pf=new PrintWriter(sf);
                            
                            for (int i = 0 ; i < array.length ; i++ ){
                            pf.println(array[i].toString());
                            System.out.println(array[i].toString());

                            }
                            
                            pf.close();
                            
                            } 
                        catch (Exception e ) {
                            System.out.print(e);
                        }
                        break ; 
                            
                        }//switch

		
		}while(x!=4);
                
                
		
	}//main    }
    
    public static void First_fit(String PID, int size) {
               boolean check=false;
		for(int i=0;i<array.length; i++){
			if(array[i].status.equals("free") && array[i].size>= size){
				array[i].allocate(size,PID);
				check=true; 
                                break ; 
        }
    } 
                if(check)
					System.out.println("allocating Partition is completed") ;
				  else
				  	System.out.println("allocating Partition is not completed") ; 
               
               displayState();

    } 
    
  public static void Best_fit(String PID, int size) {
    boolean check = false;
    int indexMin = -1;
    int minFrag = Integer.MAX_VALUE; // Set initial value to maximum possible value
    for (int i = 0; i < array.length; i++) {
        if (array[i].status.equals("free") && array[i].size >= size) {
            int frag = array[i].size - size;
            if (frag < minFrag) {
                minFrag = frag;
                indexMin = i;
                check = true;
            }
        }
    }
    if (check) {
        array[indexMin].allocate(size, PID);
        System.out.println("Allocating partition is completed");
        displayState();
    } else {
        System.out.println("Allocating partition is not completed");
        displayState();
    }
}

    
    
    public static void Worst_fit(String PID, int size) {
      boolean check=false;
		int indexMax =-1;
		int maxFrag=0;
		for(int i=0;i<array.length; i++){
			if(array[i].status.equals("free") && array[i].size >= size){
				int frag=array[i].size-size;
				if(frag>maxFrag){
				maxFrag=frag;
					indexMax=i;
					check=true;
				}
			}
		}
		if(check){
			array[indexMax].allocate(size,PID);
			System.out.println("allocating Partition is completed");	
			displayState();
		}
		else{
			System.out.println("allocating Partition is not completed");
         displayState();
         }
    }
    
    public static void displayState() {
    String state = new String("[");
    for (int i = 0; i < array.length; i++) {
        if (! (array[i].PID.equals("Null") )) {
            state=state+array[i].PID+" ";
        } else {
            state=state+"H ";
        }
        if (i + 1 >= array.length) {
            state=state+"]";
        } else {
            state=state+"| ";
        }
    }
    System.out.println("Memory State:\n" + state);
}

    
}