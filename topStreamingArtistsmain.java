
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class topStreamingArtistsmain {

	public static void main(String[] args) throws Exception {
		
		int rows = 200;
		int cols = 5;
		int index = 0;
		
		String [][] dataArray = new String [rows][cols];
		String delimiter = "\t";
		
		Scanner sc = new Scanner(new File("/Users/taylorkirschenheiter/Downloads/java/hw1-data/tsv-spotfiy-data-new copy.tsv"));
		
		TopStreamingArtists linkedList = new TopStreamingArtists();
		ArrayList<String> toSort = new ArrayList <>();
		HashMap<String, Integer> mapCount = new HashMap<>();
		ArrayList<String> nonRepeatingNames = new ArrayList<>(mapCount.keySet());
		
		//Reads in TSV file, puts data in 2D array
		readData(dataArray, sc, index, delimiter, cols);
		
		//Sorts names before putting in linked list
		sortNames(dataArray, toSort, rows);
		
		//Uses hashmap to count and eliminate extras
		artistCount(toSort,mapCount);
		
		//Adds artist names and count to linked list
		addToList(nonRepeatingNames, linkedList);
	    
		//Prints array and linked list to a file
	    printOutputToFile(dataArray, linkedList, mapCount);
	  	
	}
	
	public static void readData(String [][] dataArray, Scanner sc, int index, String delimiter, int cols) {
		 while (sc.hasNextLine()) {
				String [] temp = sc.nextLine().split(delimiter);
				for (int c = 0; c<cols; c++) {
					dataArray[index][c] = temp [c];
				}
				index++;
			}
			sc.close(); 
	 }
	
	 public static void sortNames (String [][] dataArray, ArrayList<String> toSort, int rows) {
			for (int i = 0; i < rows; i++) {
				String nameSort = dataArray[i][2];
				toSort.add(nameSort);
			}
				toSort.sort(String.CASE_INSENSITIVE_ORDER);
				Collections.reverse(toSort);
	 }
	 
	
	 public static void artistCount(ArrayList<String> toSort, HashMap<String, Integer> mapCount ){
	        for(String names: toSort){
	            if(mapCount.containsKey(names)){
	                Integer value = mapCount.get(names);
	                value++;
	                mapCount.put(names,value);
	            }else{
	                mapCount.put(names,1);
	            }
	        }
	  }	   

	 public static void addToList (ArrayList<String> nonRepeatingNames, TopStreamingArtists linkedList) {
		  nonRepeatingNames.sort(String.CASE_INSENSITIVE_ORDER);
	      Collections.reverse(nonRepeatingNames);
	      	for(String names: nonRepeatingNames){
	      		linkedList.insertFirst(names);
	        }
	 }
	 
	 public static void printOutputToFile(String[][] dataArray, TopStreamingArtists linkedList, HashMap<String, Integer> mapCount ) throws Exception{
	        PrintStream output = new PrintStream(new FileOutputStream(new File("/Users/taylorkirschenheiter/Downloads/java/ArtistOutput.txt")));
	        
	        output.println("Alphabetical List + Count: \n");
	        Artist node = linkedList.getFirst();
		    while(node != null){
		    	output.printf("%-30s %10d%n", node.toString() , mapCount.get(node.toString()));
		    node = node.getNext();
		    }
		    
		    output.println();
	        
	        output.println("2D Array of Data: \n");
	        for(int i=0; i<200; i++){
	            output.printf("%-9s %-70s %-30s %-9s %s%n",dataArray[i][0],dataArray[i][1],dataArray[i][2],dataArray[i][3],dataArray[i][4]);
	        }
	        //closes PrintStream
	        output.close();
	    }
}
