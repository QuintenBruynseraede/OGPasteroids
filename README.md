# OGPasteroids
OGP project: Asteroids
<center><img src=http://userpages.umbc.edu/~sohisa1/images/artwork/cosmoknights/backgrounds/asteroid.png" width="100px" height="100px"></center>
##Style guidelines
All code must be written conform to the style guidelines used in Eric Steegmans's 'Object Oriented Programming with Java'. Most basic guidelines can be deducted from the following examples:
```java
class TestClass {
	public void someFunction(int argA, float argB) {
		fooBar();
		
		if ( (a == b) && (c == d) ) {
			System.out.println("Some text.");
			a = c;
			b = d
		}
		
		if (a == b)
			System.out.println("Text.");
		
	}
}
```
Some guidelines to explicitly point out:
* Pascal Case is used for class objects
* CamelCase is used for both variable and method names.
* Each indented block of code containing more than one statement of code is preceded by a curly bracket, which is not on a seperate line.
* If possible, curly brackets are not used (e.g the implication consists of only one statement)
* Adequate whitespace is used in conditional phrases (see example)
* One line of whitespace is used between blocks of code.
