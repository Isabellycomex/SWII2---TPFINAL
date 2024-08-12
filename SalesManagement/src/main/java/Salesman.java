/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

public class Salesman {
	private int id;
	private String name;
	private String city;
	private float commission;
	
	public Salesman() {
		
	}
	
	public Salesman(int id) {
		this.id = id;
	}
	
	public Salesman(String name, String city, float commission) {
		this.name = name;
		this.city = city;
		this.commission = commission;
	}
	
	public Salesman(int id, String name, String city, float commission) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.commission = commission;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCity() {
		return city;
	}
	
	public float getCommission() {
		return commission;
	}
}
