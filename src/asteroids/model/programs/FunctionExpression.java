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
	public Object eval() {
		return this.getStatement().getProgram().getFunctionByName(name).eval(arguments);
	}
	
	
}
