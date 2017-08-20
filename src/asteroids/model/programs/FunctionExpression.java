package asteroids.model.programs;

import java.util.ArrayList;
import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionExpression extends Expression {
	private String name;
	private List<Expression> arguments;
	
	public FunctionExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.name = functionName;
		this.arguments = actualArgs;
	}

	@Override
	public Object eval()  {
		for (Expression e : arguments) {
				e.setStatement(getStatement());
		}
		
		if (getStatement().getProgram().getFunctionByName(name) == null) 
			throw new IllegalArgumentException();
		try {
			return this.getStatement().getProgram().getFunctionByName(name).execute(arguments);
		} catch (OutOfTimeException e1) {
			return null; //Will never happen, as the time left for a program won't be changed when executing a function.
		}
	}
	
	
}
