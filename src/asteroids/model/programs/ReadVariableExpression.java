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
		if (p.getCurrentFunction() == null) { //GLOBAL VARIABLE
			if (p.getVariableValue(varName) != null) {
				return p.getVariableValue(varName);
			}	
			else throw new IllegalArgumentException("");
		}
		else { //LOCAL VARIABLE
			try {
				return p.getCurrentFunction().getVariableValue(varName);
			}
			catch (IllegalArgumentException e) {
				try {
					return p.getVariableValue(varName);
				} 
				catch (IllegalArgumentException f) {
						throw new IllegalArgumentException("Variable not found locally or globally");
				}
					
			}
		}
		
				
	}

}