/**
 * Holds the verticies that a vertex object is connected to.
 * @author Ezra
 *
 */
public class Vertex {
	// The linked list of adjacent vertices.
	public SinglyLinkedList values;
	/**
	 * Constructor for Vertex Class
	 */
	public Vertex(){
		values = new SinglyLinkedList();
	}
	
	/**
	 * Retrieves the vertices linked list
	 * @return the vertices as a SinglyLinkedList
	 */
	public SinglyLinkedList getList(){
		return values;
	}
	
	/**
	 * Sets the vertices linked list
	 * @param l list to set values to
	 */
	public void setList( SinglyLinkedList l ){
		values = l;
	}
	
	/**
	 * Controls adding a vertex to the vertices linked list
	 * @param v vertex to be added
	 */
	public void addVertex(String v){
		values.addNode(v);
	}
	
	/**
	 * Retrieves the size of the vertices linked list
	 * @return the amount of adjacent vertices
	 */
	public int sizeVertex(){
		return values.size();
	}
}
