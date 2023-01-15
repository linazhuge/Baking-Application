public class Frosting extends Recipe{
	private double Rsize;
	
	public Frosting(){
		super();
		this.type = "frosting";
	}
	
	public Frosting(String name){
		super(name);
		this.type = "frosting";
	}
	
	public Frosting(String name, double time){
		super(name, time);
		this.type = "frosting";
	}
	
	public Frosting(String name, double time, String instructions){
		super(name, time, instructions);
		this.type = "frosting";
	}
	
	public Frosting(String name, double time, double size, String instructions){
		super(name, time, size, instructions);
		this.type = "frosting";
	}
	
	public double compare(double num){
		double temp = Math.pow((num/2), 2) / 16;
		temp = temp / this.getRSize();
		return temp;
	}
}