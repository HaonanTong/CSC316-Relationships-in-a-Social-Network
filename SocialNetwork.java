/**
 * @author Ezra Zerihun (eczerihu), HaoNan Tong (hton), Zewdu Beshah (zbeshah)
 * Reads and creates a network of friends.
 */
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
/**
 * @author Ezra Zerihun (eczerihu), HaoNan Tong (hton), Zewdu Beshah (zbeshah)
 * Reads and creates a network of friends.
 */
public class SocialNetwork {
	// The map representing the Social Network
	private static LinkedHashMap<String,Vertex> map;
	// Determines what information about the Social Network certain commands give you
	private static FriendShip friendship;
	// The input graph file
	private static File fileIn;
	
	/**
	 * 
	 */
    public static void main(String[] args) throws IOException{
		map = new LinkedHashMap<String,Vertex>();
		fileIn = new File(args[0]);
		readInput(fileIn);
		friendship = new FriendShip(map);		
		readCommand();
	}
	
	/**
	 * Reads the file name and inputs the data into a list.
	 * @param name of file.
	 * @throws IOException if file can't be written or read.
	 */
	public static void readInput(File fileIn) throws IOException {
		Scanner scan = new Scanner(fileIn);
		String tmp;
		boolean name = true;
		while(scan.hasNextLine()){
			tmp = scan.nextLine();
			
			
			if(tmp.equals("$")) {
				name = false;
				if(scan.hasNextLine())
					tmp = scan.nextLine();
				else
					tmp = null;
			}
			if(name) 
				map.put(tmp, new Vertex());
			else if(tmp != null) {
				Scanner scanLine = new Scanner(tmp);

				String v1 = null, v2 = null;

				if (scanLine.hasNext())
					v1 = scanLine.next();

				if (scanLine.hasNext())
					v2 = scanLine.next();

				if(v1 != null && v2 != null) {
					map.get(v1).addVertex(v2);
					map.get(v2).addVertex(v1);
				}

				scanLine.close();
			}
		}
		scan.close();
	}
	
	/**
	 * Reads the from standard input and interprets the data to standard output.
	 * @throws IOException if file can't be written or read.
	 */
	public static void readCommand() throws IOException {
		Scanner scan = new Scanner(System.in);
		String line;
		System.out.print("$\n");
		while(scan.hasNextLine()){
			line = scan.nextLine();
			Scanner scanLine = new Scanner(line);
			
			String function = null;
			String result = "";
			if(scanLine.hasNext()) {
				function = scanLine.next();
				if(function.equals("isfriend")) {
					String a = scanLine.next();
					String b = scanLine.next();
					result += friendship.isFriend(a, b);
				} else if(function.equals("mutual")) {
					String a = scanLine.next();
					String b = scanLine.next();
					result += friendship.mutual(a, b);
				} else if(function.equals("relation")) {
					String a = scanLine.next();
					String b = scanLine.next();
					result += friendship.relation(a, b);
				} else if(function.equals("notconnected")) {
					result += friendship.notConnected()+"\n";
				} else if(function.equals("popular")) {
					result += friendship.popular();
				}
				System.out.print(result + "$\n");
			}
			scanLine.close();
		}
		scan.close();
	}
}