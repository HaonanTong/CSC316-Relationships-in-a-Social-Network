/**
 * ADT to make a single list
 */
public class Node {
	public String sName = null;// String Name;
	public int distance = -1; //distance between each nodes to a certain node.
	public double popularity = -1.0; // the popularity;
	public Node next = null;
	
	/**
	 * Instantiates all the linked list variables.
	 * @param sname name
	 */
	public Node(String sName) {
		this.sName = sName;
	}
	
	/**
	 * Instantiates linked list variables based on name and distance
	 * @param sName name
	 * @param distance distance from root
	 */
	public Node(String sName, int distance ) {
		this.sName = sName;
		this.distance = distance;
	}
	
	/**
	 * Instantiates linked list variables based on name and popularity
	 * @param sName name
	 * @param popularity popularity
 	 */
	public Node(String sName, double popularity ) {
		this.sName = sName;
		this.popularity = popularity;
	}
	
	/**
	 * Method to get Name.
	 * @return sName.
	 */
	public String getName() {
		return this.sName;
	}
	
	/**
	 * Method to get distance.
	 * @return the distance
	 */
	public int getDistance(){ 
		return this.distance;
	}
	
	/**
	 * Method to set Name.
	 * @param new name.
	 * @return the name.
	 */
	public String setName(String sName) {
		return this.sName = sName;
	}

	/**
	 * Sets the distance of the Node
	 * @param distance distance from root
	 */
	public void setDistance(int distance ){
		this.distance = distance;
	}
}