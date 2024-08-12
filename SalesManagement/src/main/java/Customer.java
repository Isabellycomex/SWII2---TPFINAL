/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

public class Customer {
	private int id;
	private String name;
	private String city;
	private int grade;

	public Customer() {

	}

	public Customer(int id) {
		this.id = id;
	}

	public Customer(String name, String city, int grade) {
		this.name = name;
		this.city = city;
		this.grade = grade;
	}

	public Customer(int id, String name, String city, int grade) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.grade = grade;
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

	public int getGrade() {
		return grade;
	}
}
