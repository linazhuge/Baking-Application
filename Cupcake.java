public class Cupcake extends Recipe{
	private double Rsize;
	
	public Cupcake(){
		super();
		this.type = "cupcake";
	}
	
	public Cupcake(String name){
		super(name);
		this.type = "cupcake";
	}
	
	public Cupcake(String name, double time){
		super(name, time);
		this.type = "cupcake";
	}
	
	public Cupcake(String name, double time, String instructions){
		super(name, time, instructions);
		this.type = "cupcake";
	}
	
	public Cupcake(String name, double time, double size, String instructions){
		super(name, time, size, instructions);
		this.type = "cupcake";
	}
}