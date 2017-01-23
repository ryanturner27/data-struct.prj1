
import java.io.*;
import java.util.*;

/*
 * SearchByArtistPrefix.java
 * Starting code Bob Boothe September 2008
 * Ryan Turner
 * COS 285
 */
public class SearchByArtistPrefix{

	private Song[] songs;  // keep a direct reference to the song array
	private Song[] nwlist; //new array used for search results 

	public SearchByArtistPrefix(SongCollection sc) {
		songs = sc.getAllSongs();
	}

	/*
	 * find all songs matching artist prefix
	 * uses binary search
	 * should operate in time log n + k (# matches)
	 * @param string
	 * @return
	 */
	public Song[] search(String artistPrefix) {
		int counter =0;
		int max = (songs.length-1);
		int index = 0;
		String t ="";
		String l ="";
		ArrayList<Song> results = new ArrayList<Song>();

		Song ar = new Song(artistPrefix, t, l);


		index = Arrays.binarySearch(songs, ar);


		if(index < 0){ //Binary returns -1
			counter = -index -1;

		}
		else{ 
			counter = index; 
		}

		while(counter < max){ //Start searching the array
			if(songs[counter].getArtist().startsWith(artistPrefix)){ //checks to see if the artist matches the prefix

				results.add(songs[counter]);
				counter++; 
			}
			else {
				break; //ends the while loop
			}
		}
		nwlist = new Song[results.size()]; //New List sets size to arrayList size
		results.toArray(nwlist);
		return nwlist; //Return the new array



	}

	// testing routine
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 2) {
			System.err.println("usage: prog songfile artist");
			return;
		}

		SongCollection sc = new SongCollection(args[0]);
		SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);

		System.out.println("searching for: "+args[1]);
		Song[] byArtistResult = sbap.search(args[1]);	

		// to do: show first 10 songs
		if(byArtistResult.length==0){ //test if the array is empty
			System.out.println("No results found");
		}
		else if(byArtistResult.length<10){ //test if the array is less than 10
			for(int k =0; k < byArtistResult.length; k++){
				System.out.println("Results are " + (byArtistResult[k]).toString());  
			}	
			System.out.println("Number of matches: " + byArtistResult.length);
		}
		else{ //if greater than 10, print the 10 lines
			for(int j = 0; j<10;j++){
				System.out.println("Results are " + byArtistResult[j].toString());
			}
			System.out.println("Number of matches: " + byArtistResult.length);
		}
		System.err.println("exiting normally");
	}



}