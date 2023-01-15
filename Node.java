public class Node{
	private Object data;
	private Node next;
	
	public Node(){
		this(null, null);
	}
	
	public Node(Object data, Node next){
		this.data = data; 
		this.next = next;
	}
	
	public Node(Object data){
		this.data = data;
		this.next = null;
	}
	
	public void setData(Object data){
		this.data = data;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
	
	public boolean hasNext(){
		if(next == null)
			return false;
		else
			return true;
	}
	
	public Object getData(){
		return data;
	}
	
	public Node getNext(){
		return next;
	}
}