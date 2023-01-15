public class Cake extends Recipe{
	private double Rsize;
	
	public Cake(){
		super();
		this.type = "cake";
	}
	
	public Cake(String name){
		super(name);
		this.type = "cake";
	}
	
	public Cake(String name, double time){
		super(name, time);
		this.type = "cake";
	}
	
	public Cake(String name, double time, String instructions){
		super(name, time, instructions);
		this.type = "cake";
	}
	
	public Cake(String name, double time, double size, String instructions){
		super(name, time, size, instructions);
		this.type = "cake";
		System.out.println("!" + Rsize);
	}
	
	public double compare(double num){
		double temp = Math.pow((num/2), 2) / Math.pow((this.getRSize()/2), 2);
		return temp;
	}
}