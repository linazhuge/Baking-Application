public class Ingredient{
	private String ingre;
	private double amount;
	private String measurement;
	
	public Ingredient(double amount, String measurement, String ingre){
		this.ingre = ingre;
		this.measurement = measurement;
		this.amount = amount;
	}
	
	public String getIngre(){
		return ingre;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public String getMeasurement(){
		return measurement;
	}
	
	public void setIngre(String ingre){
		this.ingre = ingre;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public void setMeasurement(String measurement){
		this.measurement = measurement;
	}
	
	public void round(){
		int calcO = (int)Math.round(amount * 100);
		int calc = 0;
		int close = 0;
		boolean roundUp = false;
		if(measurement.equals("cup") || measurement.equals("cups")){
			calc = calcO % 100;
			int num1 = calc % 25;
			int num2 = calc % 33;
			int num3 = calc;
			if(num1 > (25 - num1)){
				num1 = 25 - num1;
				roundUp = true;
			}
			if(num2 > (33 - num2)){
				num2 = 33 - num2;
				roundUp = true;
			}
			if(num3 > (100 - num3)){
				num3 = 100 - num3;
				roundUp = true;
			}
			if(num1 <= num2 && num1 <= num3){
				if(roundUp)
					close = ((calc / 25) + 1) * 25;
				else
					close = (calc / 25) * 25;
			}
			else if(num2 <= num1 && num2 <= num3){
				if(roundUp){
					close = ((calc / 33) + 1) * 33;
					if(close == 99)
						close = 100;
				}
				else
					close = (calc / 33) * 33;
			}
			else{
				if(roundUp)
					close = 100;
				else
					close = 0;
			}
		}
		else if(!measurement.equals("egg") || !measurement.equals("eggs")){
			calc = calcO % 100;
			int num1 = calc % 25;
			int num2 = calc;
			if(num1 > (25 - num1)){
				num1 = 25 - num1;
				roundUp = true;
			}
			if(num2 > (100 - num2)){
				num2 = 100 - num2;
				roundUp = true;
			}
			if(num1 <= calc){
				if(roundUp)
					close = ((calc / 25) + 1) * 25;
				else
					close = (calc / 25) * 25;
			}
			else{
				if(roundUp)
					close = 100;
				else
					close = 0;
			}	
		}
		else{
			calc = calcO % 100;
			if(calc < (100 - calc))
				close = 0;
			else
				close = 100;
		}
		amount = calcO / 100 + close / 100.0;
	}
}