import java.util.*;

/**
 * @author Haonan Tong, Ezra Zuritun
 * @version 3.4
 * since 2015/12
 * Friendship provided all the function that we need;
 */
public class FriendShip {
	//List of distances from root
	private SinglyLinkedList mapPath;
	//the higher the popular with maximum to be "1"
	private SinglyLinkedList popRate;
	//give infomation of connected path
	private SinglyLinkedList connectPath;
	//the social network map
	private LinkedHashMap< String, Vertex > map;
	//counter for the connected items
	private int counter;
	//Stores the answer for popular
	String popularAnswer;
	//Stores the answer for notConnected
	int notConnectedAnswer;
	/**
	 * Initializes the Friendship object
	 * @param map social network
	 */
	public FriendShip( LinkedHashMap<String, Vertex> map ){
		mapPath = new SinglyLinkedList();
		connectPath = new SinglyLinkedList();
		this.map = map;
		mapPath.initial(map);
		connectPath.initial(map);//set all elements in connectPath to -1;
		popRate = new SinglyLinkedList();
		popularAnswer = "";
		notConnectedAnswer = -1;
	}
	
	/**
	 * Initialize popRate
	 */
	public void initpopRate(){
		for ( Map.Entry<String , Vertex> entry : map.entrySet() ){
			popRate.addNode(entry.getKey());
		}		
	}	
	
	/**
	 * Initialize mapPath
	 */
	public void initmapPath(){//initialize mathPath;
		for ( Map.Entry<String , Vertex> entry : map.entrySet() ){
			mapPath.addNode(entry.getKey(), -1);
		}
	}
	
	/**
	 * Reset mapPath
	 */
	public void resetmapPath(){//initialize mathPath;
		if(mapPath.head != null ){
			mapPath.head.distance = -1;
			for ( Node tmp = mapPath.getHead(); tmp.next != null ; tmp = tmp.next ){
				tmp.next.distance = -1 ;
			}			
		}

	}
	
	/**
	 * Reset connectPath
	 */
	public void resetconnectPath(){//initialize mathPath;
		if(connectPath.head != null ){
			connectPath.head.distance = -1;
			for ( Node tmp = connectPath.getHead(); tmp.next != null ; tmp = tmp.next ){
				tmp.next.distance = -1 ;
			}			
		}

	}
	

	/**
	 * Gives us whether or not a b is friend
	 * @param String name of two person
	 * @return where or not they are friend 
	 */
	String isFriend(String a, String b){
		if (map.get(a).getList().contains(b))
			return "yes\n";
		else
			return "no\n";
	}
	
	/**
	 * Give us the mutual relation of a,b
	 * @param name a b
	 * @return  the common relations
	 */
	String mutual(String a, String b){
		String same = "";
		SinglyLinkedList tmpa, tmpb;
		tmpa = map.get(a).getList();
		tmpb = map.get(b).getList();
	
		String aName;
		Node it_a = tmpa.getHead();
		// check values
		for(int i = 0; i < tmpa.size(); i ++) {
			aName = it_a.getName();
			if( tmpb.contains(aName) )
				same += aName + "\n";
			it_a = it_a.next;
		}
		
		return same;
	}
	/**
	 * Give us the most popular person
	 * @return  the name
	 */
	public String popular(){//return the most popular person;
      if(popularAnswer.equals("")) {
		   String aName;

		   for( Map.Entry<String , Vertex> entry : map.entrySet() ){
   			aName = entry.getKey();
	   		popRate.addNode(aName, (double)getPopulate(aName));
		   }
		   aName = getMax( popRate ) + "\n";
         popularAnswer = aName;
		return aName;
      } else {
         return popularAnswer;
      }
	}
	
	/**
	 * Give us the most popular has max popRate
	 * @return  the name
	 */
	public String getMax( SinglyLinkedList popRate ){//help function, return the most popular person;
		double max = -1.0;
		String s = null;
		if(popRate.getHead() != null){
			if( popRate.getHead().popularity > max ){
				s = popRate.getHead().getName();
				max = popRate.getHead().popularity;
			}
			for(Node tmp = popRate.getHead(); tmp.next != null ; tmp = tmp.next){
				if ( tmp.next.popularity > max )
				{
					max = tmp.next.popularity;
					s = tmp.next.sName ;
				}
				else if ( tmp.next.popularity == max ){
					s = s + "\n" + tmp.next.sName ;
				}
			}
			return s;
		}
		else
			return null;
	}
	
	/**
	 * Give us the person's popRate
	 * @return  the score
	 */
	public double getPopulate(String s ){//get the popularity for a given name.
		int num = shortestPath( s );//numerator for popularity
		int denum = 0;//denumerator for popularity
		double doubleValue;

		if(mapPath.getHead() != null){
			if( mapPath.getHead().distance != -1 ){
				denum = denum + mapPath.getHead().distance;
			}

			for(Node tmp = mapPath.getHead(); tmp.next != null ; tmp = tmp.next){
				if ( tmp.next.distance != -1 )
					denum = denum + tmp.next.distance;
			}
		}

		if( num == 0 ){
			return 0;
		}else
			doubleValue = ( ( double )num  / (double)denum );
		return  doubleValue;
	}	
	/**
	 * Sets value for mapPath
	 * name s and its value
	 */
	public void setValue( String s , int value){
		mapPath.put(s, value);
	}
	
	public int notConnected(){
		if(notConnectedAnswer == -1) {
			int size = map.size();//the total num of elements
			int total = size * ( size - 1 ) / 2;//the possible connections;
			int connected = 0 ;// initialize;
			for(Map.Entry< String , Vertex> entry : map.entrySet()){
				connected += shortestPath( entry.getKey() );
			}
			int notconnected = total - connected/2;
			notConnectedAnswer = notconnected;
		}
		return notConnectedAnswer;
	}
	
	/**
	 * Give the shortest path change the value in mapPath
	 * @para s name
	 * @return number of the items connected
	 */
	public int shortestPath( String s ){
		resetmapPath();
		counter = 0;
		setValue(s, 0);
		
		String aName;
		int value = 1 ;

		Node it_a = map.get(s).getList().getHead();
		//Iterator<String> tmp = map.get(s).iterator();
		// check values
		
		for(int i = 0 ; i < map.get(s).getList().size(); i++) {
			aName = it_a.getName();
			if( mapPath.get(aName) == -1 ){
				setValue( aName, value );
				counter ++;
			}
			it_a = it_a.next;
		}
		
		for(Node keyPath = mapPath.getHead() ; keyPath != null ; keyPath = keyPath.next ) {
			aName = keyPath.getName();
			if( mapPath.get(aName) == value )
				shortestPath( aName , value );
		}
		return counter;// the # of items that s has a path to.
	}
	
	/**
	 * Helper function to give the shortest path change the value in mapPath
	 * @para s name
	 * @param value distances
	 * @return number of the items connected
	 */
	public void shortestPath( String s , int value ){
		setValue( s , value );
		//counter = 0;
		
		Node it_a = map.get(s).getList().getHead();
		//Iterator<String> tmp = map.get(s).iterator();
		
		String aName;
		value ++;
		// check values
		for(int i = 0 ; i < map.get(s).getList().size(); i++) {
			aName = it_a.getName();
			if( mapPath.get(aName) == -1 ){
				setValue( aName, value );				
				counter ++;
			}
			it_a = it_a.next;
		}
		
		for(Node keyPath = mapPath.getHead() ; keyPath != null ; keyPath = keyPath.next ) {
			aName = keyPath.getName();
			if( mapPath.get(aName) == value )
				shortestPath( aName , value);
		}
	}
	
	/**
	 * Finds the shortest path from a to b
	 * @param a first name
	 * @param b last name
	 * @return shortest path from first name to last name
	 */
	public String relation(String a, String b) {
		shortestPath(a);
		int count = 1;
		String path = null;
		String aName;
		if(mapPath.get(b) == -1)
			return "";
		else if(a.equals(b)) {
			return b + "\n";
		}
		// check values
		for(Node valuePath = map.get(a).getList().getHead(); valuePath != null; valuePath = valuePath.next ){
			aName = valuePath.getName();
			if( mapPath.get(aName) == count ) {
				path = a + "\n" + relation(aName, b, count+1);
			}
			if(!path.contains("error"))
				break;
		}
		if(path.contains("error"))
			return "";
		else
			return path;
	}

	/**
	 * Helper function to find the shortest path from a to b
	 * @param a first name
	 * @param b last name
	 * @param count the mapPath value showing the distance from a
	 * @return shortest path from first name to last name
	 */
	public String relation(String a, String b, int count) {
		String path = "";
		boolean match = false;
		boolean matchValue = false;
		String aName;
		if(a.equals(b)) {
			return b + "\n";
		}
		// check values
		for(Node valuePath = map.get(a).getList().getHead(); valuePath != null; valuePath = valuePath.next ) {
			aName = valuePath.getName();
			if( aName.equals(b) && mapPath.get(aName) == count ) {
				matchValue = true;
				path += a + "\n" + b + "\n";
				break;
			}
		}
		if(matchValue == false){
			// check values
			for(Node valuePath = map.get(a).getList().getHead(); valuePath != null; valuePath = valuePath.next ){
				aName = valuePath.getName();
				if( mapPath.get(aName) == count ) {
					match = true;
					path += a + "\n" + relation(aName, b, count+1);
				}
				if(match == true && !path.contains("error"))
					break;
			}
			if(match == false)
				return "error";
		}
		return path;	
	}
}
