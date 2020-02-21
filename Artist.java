
public class Artist {
	private String name;
	private Artist next = null;
	
	public Artist (String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public void setNext(Artist artist) {
		this.next = artist;
	}
	
	public Artist getNext() {
		return next;
	}
	
}

class TopStreamingArtists {
    private Artist first;
    
    public TopStreamingArtists(){
      first = null;
    }
    public boolean isEmpty(){
     return (first == null);
    }
    
  //returns first node
    public Artist getFirst(){
        return first;
    }
    
    
    public void displayList(){
        //start from the first element 
        Artist current = first;
        // while loop (while not at the end)
        while(current != null){
        // display the node contents
         System.out.println(current);
         current = current.getNext();
        }
    }
    
    public void insertFirst (String name) {
    	if (isEmpty()) {
    		first = new Artist (name);
    	}
    	else {
    		Artist newNode = new Artist(name);
    		newNode.setNext(first);
    		first = newNode;
    	}
    	
    }
}
  