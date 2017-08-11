package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object> {
	String varName;
	
	public ReadVariableExpression(String varName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.varName = varName;
	}

	public Object eval() {
		Program p = getStatement().getProgram();
		if (p.getCurrentFunction() == null) {
			if (p.getVariableValue(varName) != null) {
				System.out.println("Found " + varName + " here.");
				return p.getVariableValue(varName);
			}
				
			else throw new IllegalArgumentException();
		}
		else {
			if (p.getVariableValue(varName) != null) {
				System.out.println("Found " + varName + " here2.");
				return p.getVariableValue(varName);
			}
			else if (p.getCurrentFunction().getVariableValue(varName) != null) {
				System.out.println("Found " + varName + " here3.");
				return p.getCurrentFunction().getVariableValue(varName);
			}	
			else
				throw new IllegalArgumentException();
		}
				
	}

}