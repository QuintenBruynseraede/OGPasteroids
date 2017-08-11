package asteroids.model.programs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Character;

import asteroids.model.Entity;
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
		if (body instanceof BlockStatement) {
			List<Statement> statements = ((BlockStatement) body).getStatements();
			Iterator <Statement> i = statements.iterator();
			
			if (!(statements.get(statements.size() - 1) instanceof ReturnStatement))
				throw new IllegalArgumentException("Last statement no Return");
			
		
			while (i.hasNext()) {
				Statement s = i.next();
				if (s instanceof ActionStatement) {
					throw new IllegalArgumentException("Action statement in function");
				}
			}
		}
		else if (body instanceof ReturnStatement) {
			//Single statement: return	
		}
		else if (body instanceof IfThenElseStatement) {
			//Single statement: ifthenelse, return is probably within if or else branch
		}
		else {
			System.out.println(body);
			throw new IllegalArgumentException("Single statement but not of correct type");
		}
		
		setBody(body);
		setName(name);
		this.sourceLocation = sourceLocation;
	}
	
	public Object execute(List<Expression> arguments) {
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
		assert(name.charAt(0) == '$');
		assert(Character.isDigit(name.charAt(1)));
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
}
