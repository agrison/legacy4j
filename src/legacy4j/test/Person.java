package legacy4j.test;

public class Person {
	public String firstName, surName;
	public String toString() { return String.format("p[%s, %s]", firstName, surName); }
}