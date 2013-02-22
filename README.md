Legacy files for Java
=====================

Are you a Java Developer who needs to parse old times `legacy` flat 
files ? You're bored to write each time a parser for these files ? In this
case, you've come to the right page. `Legacy4j` is a small Java library that
helps you load fixed length files to POJO and saves them the other way.

You can see the same detailed page on my [website](http://grison.me/legacy4j)


So, let's get started.

Enabling The Library
--------------------
That's just a JAR, put it in your classpath, and you're good to go.


Hello world
-----------
Let's say you have this file to read data from.

	Hello     Adam    20100427
	Bonjour   Robert  20110912
	BuongiornoMichele 20041108
	Güten tag Karl    20100315

All you would have to do, is to define your Java object that will
hold your data, and describe how data fits in it.


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

And then call the library and ask it gently to read the file data
into your object.

	public class HelloTest {
	   public static void main(String[] args) {
	      FixedLengthFile<Hello> file = new FixedLengthFile<Hello>("file.txt", Hello.class);
	      for (Hello hello: file) {
	         System.out.println("> " + hello);
	      }
	      file.close();
	   }
	}
	
That's it, you've read data from your file, with no need of any parser.

Here is the output of the Java program:

	> hello[Hello     , Adam    , 2010-04-27]
	> hello[Bonjour   , Robert  , 2011-09-12]
	> hello[Buongiorno, Michele , 2004-11-08]
	> hello[Güten tag , Karl    , 2010-03-15]


##Field Annotations

The library makes huge use of Java annotations, that's how it knows how to
parse the file, and cut the data where it should.

A file is made of records (lines), a Java object needs to have the 
`@FixedLengthRecord` annotation, so that the library knows how to work with it.


###FixedLengthField


The `@FixedLengthField` annotation indicates that a field is delimited in size,
and its size can be set with it.


	public @interface FixedLengthField {
	   /** @return the field length. */
	   public int value();
	}

###DateField

The `@DateField` annotation indicates that a field is a Date or a Calendar object,
and that the data read should be converted to the suitable destination, using a custom 
date format.

	public @interface DateField {
	   /** @return the date format. */
	   public String value() default "yyyyMMdd";
	}
	
###DecimalField

The `@DecimalField` annotation indicates that a field is one of int|Integer, double|Double,
float|Float or BigDecimal and that the data read should be converted to the suitable
destination, using a custom decimal format.

	public @interface DecimalField {
	   /** @return the lenth of int and decimal value. */
	   public int[] value();
	   /** @return the separator if any (must be of length 0 or 1). */
	   public String separator() default "";
	}

###CustomField

The `@CustomField` annotation indicates that a field is of a custom type and that the data read should be converted to the suitable custom type, using a custom object mapper.

	public @interface CustomField {
	   public Class<?> value();
	}
	
##Field feature annotations

###TrimField

The `@TrimField` indicates whether a field should be trimmed and in what direction (left, right or both which is the default value).

	public @interface TrimField {
	   public enum Type { Both, Left, Right }

	   public Type value() defaults Type.Both;
	}

###QuoteField

The `@QuoteField` indicates whether a field should be quoted and with what characters (`[]`, `()`, `{}`, `''` or `""` which is the default value).

	public @interface QuoteField {
	   public enum Type { 
	      Brackets("[", "]"), 
	      Parenthesis("(", ")"),
	      Braces("{", "}"),
	      Quote("\"", "\""),
	      SimpleQuote("'", "'");

	      public String before, after;
	      Type(String before, after) { this.before = before; this.after = after; }
	   }

	   public Type value() defaults Type.Quote;
	}
	
## Sample code

### The file to be parsed

	- Awesome report sample data (ignored)
    # ignored
    0001[Alex    ]12345678991234567,907820121215Alexandre Grison
    0002[Foo     ]12345673478751242,901220121223Foo bar bazz


                                                Page 1

### The Item entity

	// ignore line starting with a # or a - or having only spaces then a Page number (report)
	@FixedLengthRecord(ignoreMatching="^#.*|^-.*|^\\s+Page.*$", ignoreEmpty=true)
	public class Item {
	   @FixedLengthField(4)
	   public int id;
	   @FixedLengthField(10)
	   @TrimField
	   @QuoteField(Type.Brackets)
	   public String name;
	   @DecimalField({8, 2}) // size = 8 + 2 = 10
	   public BigDecimal num1;
	   @DecimalField(value={7, 4}, separator=",") // size = 7 + 4 + 1 = 12
	   public Double num2;
	   @DateField("yyyyMMdd") // size = "yyyyMMdd".length() = 8
	   public Calendar cal;
	   @FixedLengthField(22)
	   @CustomField(PersonMapper.class)
	   public Person person;

	   public String toString() {
	      return String.format("Item [id=%s, name='%s', num1=%s, num2=%s, cal=%s, person=%s]", 
	            id, name, num1, num2, new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()), person
	      );
	   }

	   public String format() {
	      return "";
	   }
	}

### The Person entity

	public class Person {
	   public String firstName, surName;

	   public String toString() { return String.format("p[%s, %s]", firstName, surName); }
	}

### The Person custom mapper

	public class PersonMapper implements FieldMapper {
	   @Override
	   public int fill(Object inst, Field field, String value) {
	      try {
	         FixedLengthField flf = field.getAnnotation(FixedLengthField.class);
	         if (flf == null)
	            return 0;

	         if (field.getType() == Person.class) {
	            Person p = new Person();
	            p.firstName = value.substring(0, 10).trim();
	            p.surName = value.substring(10).trim();
	            field.set(inst, p);
	         }

	         return flf.value();
	      } catch (Throwable e) {
	         throw new RuntimeException(e);
	      }
	   }

	   @Override
	   public String toString(Object inst, Field field) {
	      try {
	         Person p = (Person)field.get(inst);
	         return String.format("%-10s%-10s", p.firstName, p.surName);
	      } catch (Throwable e) {
	         throw new RuntimeException(e);
	      }
	   }
	}

### The main program

	public class Test {
	   public static void main(String[] args) {
	      FixedLengthFile<Item> file = new FixedLengthFile<Item>("positionnal.txt", Item.class);
	      EngineMappers.registerMapper(Person.class, new PersonMapper());
	      for (Item item: file) {
	         System.out.println("> " + item);
	         System.out.println("= " + RecordConverter.toString(item));
	      }
	      file.close();
	   }
	}

### The result

    > Item [id=1, name='Alex', num1=12345678.99, num2=1234567.9078, cal=15/12/2012, person=p[Alexandre, Grison]]
    = 0001[Alex    ]12345678991234567,907820121215Alexandre Grison
    > Item [id=2, name='Foo', num1=12345673.47, num2=8751242.9012, cal=23/12/2012, person=p[Foo bar ba, zz]]
    = 0002[Foo     ]12345673478751242,901220121223Foo bar bazz
