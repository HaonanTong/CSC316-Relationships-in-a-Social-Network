import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This program organizes data with linked list,
 * provide us a String list.
 * @author Ezra Zerihun, Haonan Tong
 */
public class SinglyLinkedList{
	public Node head;
	private Node current;
	private int size;
	public SinglyLinkedList(){
		head = null;
		current = null;
		size = 0;
	}
	
	/**
	 * Return the size of the list.
	 * @return the size.
	 */
	public int size(){ 
		return size; 
	}
	
	/**
	 * Return whether the size is empty.
	 * @return whether the size is empty or not.
	 */
	public boolean isEmpty(){ 
		return size == 0; 
	}
	
	/**
	 * Return head of the list
	 * @return head.
	 */
	public Node getHead(){ 
		return this.head; 
	}
	
	/**
	* Method to check first input.
	* @return if input is empty or not.
	*/
	public boolean first(){
		if( isEmpty() )
			return false;
		return true;
	}
	
	/**
	 * Method to check last input.
	 * Return whether the input is empty or not.
	 */
	public boolean last(){
		if( isEmpty() )
			return false;
		return true;
	}
	
	/**
	 * Adds the first input.
	 * @param PN message number.
	 * @param ON packet number.
	 * @param MS message.
	 */
	public void addFirst(String sName){
		head = new Node(sName);
		if(size == 0){
			current = head;
		}
		size ++;
	}
	
	/**
	 * Initializes the linked list with a distance of -1 (infinity) 
	 * @param map retrieval of the vertex names
	 */
	public void initial(LinkedHashMap<String,Vertex> map){
		for(Map.Entry<String, Vertex > entry : map.entrySet()){
			this.addNode(entry.getKey() , -1 );
		}
	}
	
	/**
	 * Adds the input
	 * @param sName name of vertex
	 */
	public void addNode( String sName ) {// add nodes in our sequeces;
		int tmp1 = this.size();
		if (tmp1 == 0) {
			head = new Node( sName );
			current = head;
			size ++;
		} else {//add node;
			current.next = new Node( sName );
			current = current.next;
			size ++;
		}
	}
	
	/**
	 * Adds the input
	 * @param sName name of vertex
	 * @param distance distance from original node
	 */
	public void addNode( String sName , int distance ) {// add nodes in our sequeces;
		int tmp1 = this.size();
		if (tmp1 == 0) {
			this.head = new Node( sName , distance );
			this.current = head;
			this.size++;
		} else {//add node;
			current.next = new Node( sName , distance);
			current = current.next;
			size ++;
		}
	}
	
	/**
	 * Adds the input
	 * @param sName name of vertex
	 * @param popularity stores the popularity of the node
	 */
	public void addNode( String sName , double popularity ) {// add nodes in our sequeces;
		int tmp1 = this.size();
		if (tmp1 == 0) {
			head = new Node( sName , popularity );
			current = head;
			size ++;
		} else {//add node;
			current.next = new Node( sName , popularity);
			current = current.next;
			size ++;
		}
	}
	
	/**
	 * Checks if the list contains the string s
	 * @param s string to check
	 * @return true or false if it is present
	 */
	public boolean contains( String s ){
		if(head != null){
			Node tmp = head;
			for( int i = 0; i < this.size ; i ++ ){
				if( tmp.sName.equals(s)){
					return true;
				}
				tmp = tmp.next;
			}
		}
		return false;
	}
	
	/**
	 * Puts a new node with name s and distance dis in the list
	 * @param s name
	 * @param dis distance
	 * @return if s was found
	 */
	public int put(String s , int dis){//set value for mapPath and popRate;
		if(head != null){
			if(head.sName.equals(s)){
				head.setDistance(dis);
			}else{
				for ( Node tmp = head ; tmp.next != null ; tmp = tmp.next ){
					if( tmp.next.sName.equals(s)){
						tmp.next.setDistance(dis);
						return 1;//find s and change the value;
					}
				}
			}
		}

		return 0;//fail to find s;
	}
	
	/**
	 * Retrieves the string from the list
	 * @param s name
	 * @return whether the string was found or not
	 */
	public int get( String s ){
		if(head != null){
			if(head.sName.equals(s)){
				return head.distance;
			}else{
				for ( Node tmp = head ; tmp.next != null ; tmp = tmp.next ){
					if( tmp.next.sName.equals(s)){
						return tmp.next.distance;//find s and change the value;
					}
				}
			}		
		}
		return -1;
	}
	
	/**
	 * Prints the path for testing
	 */
	public void printPath(){
		if(this.head != null){
			System.out.println(this.head.sName + this.head.distance);
			for(Node tmp = head; tmp.next != null; tmp = tmp.next){
				System.out.println(tmp.next.sName+tmp.next.distance);
			}
		}
	}
}


