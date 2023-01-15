import javax.swing.*;

public class List{
	Node header;
    int size;
   
    public List(){
        header = null;
        size = 0;
    }
    
	public void add(Object item){
		if(header == null){
			header = new Node(item);
		}
		else{
			Node n = header;
			while(n.getNext() != null){
				n = n.getNext();
			}
			
			n.setNext(new Node(item));
		}
		size++;
	}
	
	public void addR(Recipe item){
		int num = 0;
		if(header == null){
			header = new Node(item);
		}
		else if(item.getName().compareTo(((Recipe)header.getData()).getName()) < 0){
			Node temp = new Node(item);
			temp.setNext(header);
			header = temp;
		}
		else{
			Node n = header;
			num++;
			while(n.getNext() != null && item.getName().compareTo(((Recipe)n.getNext().getData()).getName()) > 0){
				n = n.getNext();
				num++;
			}
			Node temp = new Node(item);
			temp.setNext(n.getNext());
			n.setNext(temp);
		}
		size++;
		Baking.order = Baking.order.substring(0, num) + size + Baking.order.substring(num);
	}

	public void addP(JPanel item){
		if(header == null){
			header = new Node(item);
		}
		else if(item.getName().compareTo(((JPanel)header.getData()).getName()) < 0){
			Node temp = new Node(item);
			temp.setNext(header);
			header = temp;
		}
		else{
			Node n = header;
			while(n.getNext() != null && item.getName().compareTo(((JPanel)n.getNext().getData()).getName()) > 0){
				n = n.getNext();
			}
			
			Node temp = new Node(item);
			temp.setNext(n.getNext());
			n.setNext(temp);
		}
		size++;
	}
	
	public void rearrange(){
		Node n = header;
		while(n.getNext().getNext() != null){
			n = n.getNext();
		}
		addP((JPanel)n.getNext().getData());
		n.setNext(null);
	}

	
    public Node traverse(double i){
        Node n = header;
      
        if(i < 0)
           return null;
      
        for(int j = 0 ; j < i ; j++){
            if (n == null)
               return null;
            n = n.getNext ();
         }
         return n;
     }
   
    public boolean insert(double i, Object item){
        Node prev;
        Node newNode = new Node(item);
      
        if(i == 0){
            if(!isEmpty())
               newNode.setNext(header);
            header = newNode;
        }
        else{
            prev = traverse(i - 1);
            if (prev == null)
               return false;
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
        }
        size++;
        return true;
    }
   
    public int size(){
        return size;
    }
   
    public boolean isEmpty(){
        return header == null;
    }  
   
    public Object lookUp(int i){
        Node n = traverse(i);
        if(n == null)
			return null;
        return n.getData();
    }
   
    public boolean delete(double i){
        Node prev;
      
        if(isEmpty())
           return false;
      
        if(i == 0)
			header = header.getNext ();
        else{
            prev = traverse (i - 1);
            if (prev.getNext () == null)
               return false;
            prev.setNext (prev.getNext().getNext());
        }
        size--;
        return true;
    }
   
    public boolean replace(double i, Object item){
        Node n = traverse(i);
        if(n == null)
			return false;
        n.setData(item);
        return true;
    }
	
	public boolean containsB(String name){
		if(header == null){
			return false;
		}
		Node n = header;
		while(n.getNext() != null){
			if(((JButton)n.getData()).getText().equals(name))
				return true;
			else
				n = n.getNext();
		}
		if(((JButton)n.getData()).getText().equals(name)){
			return true;
		}
		return false;
	}
	
	public boolean containsR(String name){
		if(header == null){
			return false;
		}
		Node n = header;
		while(n.getNext() != null){
			if(((Recipe)n.getData()).getName().equals(name))
				return true;
			else
				n = n.getNext();
		}
		if(((Recipe)n.getData()).getName().equals(name)){
			return true;
		}
		return false;
	}
	
	public boolean containsI(String name){
		if(header == null){
			return false;
		}
		Node n = header;
		while(n.getNext() != null){
			if(((Ingredient)n.getData()).getIngre().equals(name))
				return true;
			else
				n = n.getNext();
		}
		if(((Ingredient)n.getData()).getIngre().equals(name)){
			return true;
		}
		return false;
	}
	
	public int pos(JButton b){
		Node n = header;
		
		/*while(true){
			if(b == (JButton)n.getData()){
				return i;
			}
			n = n.getNext();
			i++;
		}*/
		
		for(int i=0; i<size; i++){
			if(b == (JButton)n.getData())
				return i;
			n = n.getNext();
		}
		
		return -1;
	}
	
	public int posP(JPanel p){
		Node n = header;
		int i = 0;
		
		while(true){
			if(p == (JPanel)n.getData()){
				return i;
			}
			n = n.getNext();
			i++;
		}
	}
	
	public int posI(Ingredient p){
		Node n = header;
		int i = 0;
		
		while(true){
			if(p == (Ingredient)n.getData()){
				return i;
			}
			if(n.hasNext() == true)
				n = n.getNext();
			else
				return -1;
			i++;
		}
	}
	
	public int posNum(int p){
		Node n = header;
		int i = 0;
		
		while(true){
			if(p == (int)n.getData()){
				return i;
			}
			if(n.hasNext() == true)
				n = n.getNext();
			else
				return -1;
			i++;
		}
	}
	
	public int posI(String p){
		Node n = header;
		int i = 0;
		
		while(true){
			if(p.equals(((Ingredient)n.getData()).getIngre())){
				return i;
			}
			if(n.hasNext() == true)
				n = n.getNext();
			else
				return -1;
			i++;
		}
	}
	
	public int posI2(String p){
		Node n = header;
		int i = 0;
		
		while(true){
			if(p.equals(((Recipe)n.getData()).getName())){
				return i;
			}
			if(n.hasNext() == true)
				n = n.getNext();
			else
				return -1;
			i++;
		}

	}
}


