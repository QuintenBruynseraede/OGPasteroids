package asteroids.model.programs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Character;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

public class Function {
	private Statement body;
	private Program program;
	private List<Variable<?>> variables = new ArrayList<Variable<?>>();
	private String name;
	private List<Expression> arguments;
	private SourceLocation sourceLocation;
	private Object returnValue;
	
	public Function(Statement body, String name, SourceLocation sourceLocation) throws Exception {
		setBody(body);
		setName(name);
		this.setSourceLocation(sourceLocation);
	}
	
	public Object execute(List<Expression> arguments) throws OutOfTimeException {
		body.checkForIllegalStatements(); //
		//No print or action statements in function.
		
		this.arguments = arguments;	
		
		getProgram().setCurrentFunction(this);
		getBody().setProgram(getProgram());
		
		getBody().eval();
		
		getProgram().setCurrentFunction(null);
		return getReturnValue();
		
	}
	
	@Basic
	private void setBody(Statement s) {
		this.body = s;
	}
	
	@Basic
	public Statement getBody() {
		return this.body;
	}
	
	@Basic
	public void setProgram(Program p) {
		this.program = p;
	}
	
	@Basic
	public Program getProgram() {
		return this.program;
	}
	
	@Basic
	public String getName() {
		return this.name;
	}
	
	@Basic
	private void setName(String name) {
		this.name = name;
	}

	public Expression<?> getParameterValue(String parameterName) {
		assert(isValidParameterName(parameterName));

		int argNb = Character.getNumericValue(parameterName.charAt(1));
		
		try {
			return this.arguments.get(argNb-1);
		} catch (IndexOutOfBoundsException e) { //Not enough arguments provided.
			throw new IllegalArgumentException();
		} 
		
	}
	
	public boolean isValidParameterName(String name) {
		if (name.charAt(0) != '$') 
			return false;
		if (!Character.isDigit(name.charAt(1))) 
			return false;
		
		return true;
	}
	
	public Object getVariableValue(String varName) {
		for (Variable v : this.getVariables()) {
			if (v.getName().equals(varName))
				return v.eval();
		}
		throw new IllegalArgumentException("Non-existent variable.");
	}
	
	public List<Variable<?>> getVariables() {
		return this.variables;
	}
	
	public void addVariable(String varName, Expression<?> variable) {
		this.variables.add(new Variable(varName, variable));
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
