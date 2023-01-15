public class Recipe{
	private double time;
	private List ingredients;
	private String name, instructions;
	protected String type;
	private int size;
	protected double Rsize = 0;
	
	public Recipe(){
		name = "";
		time = 0;
		ingredients = new List();
		instructions = "";
		size = 0;
		type = "";
	}
	
	public Recipe(String name){
		this.name = name;
		time = 0;
		ingredients = new List();
		instructions = "";
		size = 0;
		type = "";
	}
	
	public Recipe(String name, double time){
		this.name = name;
		this.time = time;
		ingredients = new List();
		instructions = "";
		size = 0;
		type = "";
	}
	
	public Recipe(String name, double time, String instructions){
		this.name = name;
		this.time = time;
		ingredients = new List();
		this.instructions = instructions;
		size = 0;
		type = "";
	}
	
	public Recipe(String name, double time, double size, String instructions){
		this.name = name;
		this.time = time;
		ingredients = new List();
		this.instructions = instructions;
		size = 0;
		type = "";
		this.Rsize = size;
	}
	
	public void addIngredient(Ingredient ingre){
		size++;
		ingredients.add(ingre);
	}
	
	public String getName(){
		return name;
	}
	
	public double getTime(){
		return time;
	}
	
	public int getSize(){
		return size;
	}
	
	public String getType(){
		return type;
	}
	
	public Ingredient getIngredient(int num){
		return (Ingredient)ingredients.lookUp(num);
	}
	
	public List getIngredients(){
		return ingredients;
	}
	
	public String getInstructions(){
		return instructions;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setTime(double time){
		this.time = time;
	}
	
	
	public void setIngredients(List ingredients){
		this.ingredients = ingredients;
	}
	
	public void setInstructions(String instructions){
		this.instructions = instructions;
	}
	
	public double compare(double num){
		return 1;
	}
	
	public double getRSize(){
		return Rsize;
	}
	
	public void setRSize(double num){
		Rsize = num;
	}
}