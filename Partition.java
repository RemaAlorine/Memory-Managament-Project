public class Partition {
    public String status ; 
    public int size ; 
    public int startadd ; 
    public int endadd; 
    public String PID ; 
    public int Fsize ; 

    public Partition(int size, int startadd, int endadd) {
        this.status = "free" ; // free
        this.size = size;
        this.startadd = startadd;
        this.endadd = endadd;
        this.PID ="Null"; 
        this.Fsize = -1 ; 
    }

    public void allocate(int size ,String PID ){
        this.status = "allocated" ; 
        this.PID = PID ; 
        this.Fsize =this.size - size;
           }
    
    public void deallocate() {
       this.status = "free" ;
        this.PID ="Null" ; 
        this.Fsize = -1 ; 
       
    } 
    
     public String toString() {
        return "Partition{" + "status=" + status + ", size=" + size + ", start address=" + startadd + ", end address=" + endadd + ", process ID=" + PID + ", Fragmentation size=" + Fsize + '}';
    }
   
    
    
}
