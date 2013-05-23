package me.grison.legacy4j.model;

public class Person {
	public String firstName, surName;
	public String toString() { return String.format("p[%s, %s]", firstName, surName); }
}