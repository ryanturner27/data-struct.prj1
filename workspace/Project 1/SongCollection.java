import java.io.*;
import java.util.*;


/*
 * SongCollection.java
 * Read the specified data file and build an array of songs.
 * 
 * Initial code by Bob Boothe September 2008
 */
public class SongCollection {
	private Song[] songs;    // array of all the songs


	// read in the song file and build the songs array
	public SongCollection(String filename) throws FileNotFoundException {

		Scanner sc =new Scanner (new File(filename)); //new Scanner for the file being read in
		ArrayList<Song> songlist = new ArrayList<Song>(); //Creates a new arrayList for class Song
		String artist = "", title = ""; // initialize the strings artist and title

		while(sc.hasNextLine()){ //reading in the file
			if(sc.hasNext("\"")){ // Due to a blank line in the txt file this allows for the adjustment needed 
				sc.nextLine(); //
			}

			String artistline = sc.nextLine(); //Get the artist name 
			String[] art = artistline.split("\"");
			artist = art[1]; //Sets the String Artist

			String titleline = sc.nextLine(); //get the title name
			String[] tlte = titleline.split("\"");
			title = tlte[1];  //Sets the String Title

			String lyrics = " "; //initialized the String for Lyrics
			while(sc.hasNextLine()){ //checks all lines left of lyrics
				lyrics += sc.nextLine() + "\n"; //adds to the string and space for the lyrics
				if(sc.hasNext("\"")){ //When the Scanner reaches the quotes it will skip that line and break out of Lyrics while loop
					sc.nextLine();
					break;
				}

			}
			lyrics = lyrics.substring(9); //Cuts the LYRICS=" out

			Song sng = new Song(artist, title, lyrics); //create a new Song Object that feeds in the 3 variables
			songlist.add(sng); //adds artist, title, and lyrics to arrayList

		} //close while loop

		sc.close(); //close scanner
		songs = new Song [songlist.size()]; //initialize the array to the arrayList size
		songlist.toArray(songs); //Convert from Array list to array

		//sort the songs array
		Arrays.sort(songs);

	}

	// return the songs array
	// this is used as the data source for building other data structures
	public Song[] getAllSongs() {
		return songs; 
	}

	// testing method
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length == 0) {
			System.err.println("usage: prog songfile");
			return;
		}

		SongCollection sc = new SongCollection(args[0]);
		// todo: show First 10 songs
		int Totalsongs = sc.getAllSongs().length;
		System.out.println("Total songs = " + Totalsongs + ", first songs: "); //First line of the test
		if(sc.getAllSongs().length == 0){
			System.out.println("There are no songs in the array");
		}
		else if(sc.getAllSongs().length<10){
			for(int g = 0; g<sc.getAllSongs().length; g++){
				System.out.println(sc.getAllSongs()[g].toString());
			}
		}
		else{
			for(int i =0; i< 10; i++){ //first 10 songs
				System.out.println(sc.getAllSongs()[i].toString()); 
			}
		}
	//the number of comparisons used to sort it
		System.out.println("Compare count = " + Song.cmpcount);
	}	
}


