public class Song implements Comparable<Song>{
	static int cmpcount = 0;
	private String artist, title, lyrics;


	public Song(String a, String t, String l){
		artist = a;
		title = t;
		lyrics = l;
	}

	public String getArtist(){ 
		return artist;		
	}

	public String getTitle(){
		return title;
	}

	public String getLyrics(){
		return lyrics;
	}

	public String toString(){ //Displays the artist and title of the song
		return this.getArtist() + ", \"" + this.getTitle() + "\"" ;
	}

	public int compareTo(Song song2){ //Compares song1 to song 2	
		cmpcount++; //counter for each compareto call
		if((this.artist.compareToIgnoreCase(song2.getArtist()))==0){
			if(this.title.compareToIgnoreCase(song2.getTitle())==0){
				return 0; //the title and artist both match
			}
			else if(this.title.compareToIgnoreCase(song2.getTitle())>0){
				return 1; 
			}
			else{
				return -1;
			}
		}
		else if(this.artist.compareToIgnoreCase(song2.getArtist())>0){
			return 1;
		}
		else
			return -1;
	}

	//Testing in Main Method
	/*	public static void main(String[] args) throws FileNotFoundException {

	//	Scanner sc = new Scanner(new File("shortSongs.txt"));

			Song song3 = new Song("Rya" , "tur" , "ner");
			Song song4 = new Song("rya" , "zoog" , "er");
			int x = 99;
			x	= song3.compareTo(song4);
			System.out.println(x);
	}*/

}
