package asteroids.model.programs;

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
		try {
			return this.getStatement().getProgram().getFunctionByName(name).eval(arguments);
		} catch (Exception e) {
			//Won't happen, as Exceptions are only thrown by actionstatements, and functions can not contain actions statements.
			//To make Java happy, we return null.
			return null;
		}
	}
	
	
}
