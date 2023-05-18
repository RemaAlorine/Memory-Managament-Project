

import java.util.Scanner;

public class ProjectMain {
	
	public static void main(String [] args) {
		
		Scanner read = new Scanner( System.in );
		int M;
		char alo;
		Partition array[];
		
		System.out.println("Enter number of partitions: ");
		M = read.nextInt();
		array=new Partition[M];

		int start=0; //50
		
		for( int i = 0 ; i < M ; i++) {
		System.out.println("Enter the size of partition " + (i+1) + " in KB: ");
		int s = read.nextInt(); //50
		int end= start+s-1; //49  74
		//set size of partition here
		array[i]=new Partition(s,start,end);
		start=end+1; //75

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
				//allocating method
		        System.out.println("enter process size in KB");
				int processSize=read.nextInt();
				System.out.println("enter process ID");
				String pID=read.next();
				
				switch(alo) {
				
				case 'F':
		          boolean done = firstFit(processSize,pID);
				  if(done){
					System.out.println("allocating Partition is completed")
					displayState();
				  }
				  else
				  	System.out.println("allocating Partition is not completed")
					break;
				case 'B':
					bestFit(PartitionSize,pID);
					break;
				case 'W':
					worstFit(PartitionSize,pID);
					break;
				}//switch

				break;
			case 2:
				//de-allocating method
				System.out.println("enter process ID");
				String pID=read.next();
				boolean check=false;
				for(int i=0;i<array.length; i++){
			      if(array[i].ID.equals(pID)){
					array[i].deallocate();
					check=true;
					System.out.println("Process deallocated successfully");
				  }
				}
				if(check)
				displayState();
				else
				System.out.println("error , can't deallocate process");

				break;
			case 3:
				//reporting method
				break;
			}//switch

		
		}while(x!=4);
		
	}//main


	public static boolean firstFit(int size,pId){
		boolean check=false;
		for(int i=0;i<array.length; i++){
			if(array[i].pStatus.equals("free") && array[i].pSize>= size){
				array[i].allocate(size,pId);
				check=true;
                return check;
		
			}

		}
		return check;
	}

	public static boolean bestFit(int size,pId){
		boolean check=false;
		int indexMin =-1;
		int minFrag=0;
		for(int i=0;i<array.length; i++){
			if(array[i].pStatus.equals("free") && array[i].pSize>= size){
				int frag=array[i].pSize-size;
				if(frag<minFrag){
					minFrag=frag;
					indexMin=i;
					check=true;
				}
			}
		}
		if(check){
			array[indexMin].allocate(size,pId);
			System.out.println("allocating Partition is completed");	
			displayState();
		}
		else
			System.out.println("allocating Partition is not completed");

	}

	public static worstFit(int size,pId){
		boolean check=false;
		int indexMax =-1;
		int maxFrag=0;
		for(int i=0;i<array.length; i++){
			if(array[i].pStatus.equals("free") && array[i].pSize>= size){
				int frag=array[i].pSize-size;
				if(frag>maxFrag){
					maxFrag=frag;
					indexMax=i;
					check=true;
				}
			}
		}
		if(check){
			array[indexMax].allocate(size,pId);
			System.out.println("allocating Partition is completed");	
			displayState();
		}
		else
			System.out.println("allocating Partition is not completed");

	}

	public static displayState(){
		String state="[";
		for(int i=0;i<array.length; i++){
			if(!array[i].ID.equals("Null"))
			state=state+array[i].ID +" ";
			else
			state=state+"H ";
			if(i+1 >=array.length)
			state+="]"
			else
			state+="| "


		}
		System.out.println("Memory State:\n" + state);	
		}
	}



		
	
	

//class
//test commit
//testttt
