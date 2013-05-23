package me.grison.legacy4j.model;

@FixedLengthRecord
public class Hello {
	   @FixedLengthField(10)
	   public String greeting;
	   @FixedLengthField(8)
	   public String name;
	   @DateField
	   public Calendar meetDate;
	   public String toString() { 
	      return String.format("hello[%s, %s, %3$tY-%3$tm-%3$td]", greeting, name, 
	                           meetDate); 
	   }
	}