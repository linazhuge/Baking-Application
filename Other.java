public class Other extends Recipe{
	private double Rsize;
	
	public Other(){
		super();
		this.type = "other";
	}
	
	public Other(String name){
		super(name);
		this.type = "other";
	}
	
	public Other(String name, double time){
		super(name, time);
		this.type = "other";
	}
	
	public Other(String name, double time, String instructions){
		super(name, time, instructions);
		this.type = "other";
	}
	
	public Other(String name, double time, double size, String instructions){
		super(name, time, size, instructions);
		this.type = "other";
	}
}