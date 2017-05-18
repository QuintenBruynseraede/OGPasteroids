package asteroids.model.programs;

import java.util.Iterator;
import java.util.List;
import java.lang.Character;

import asteroids.model.Program;
import be.kuleuven.cs.som.annotate.Basic;

public class Function {
	private Statement body;
	private Program program;
	private List<Variable> variables;
	private String name;
	private List<Expression> arguments;
	
	public Function(Statement body, String name) throws Exception {
		if (body instanceof BlockStatement) {
			List<Statement> statements = ((BlockStatement) body).getStatements();
			Iterator <Statement> i = statements.iterator();
			
			if (!(statements.get(statements.size() - 1) instanceof BreakStatement))
				throw new Exception();
			
		
			while (i.hasNext()) {
				Statement s = i.next();
				if (s instanceof PrintStatement)
					throw new Exception();
				if (s instanceof ActionStatement) {
					throw new Exception();
				}
			}
		}
		else if (body instanceof ReturnStatement) {
			//Single statement: return	
		}
		else {
			throw new Exception();
		}
		
		this.setBody(body);
		this.setName(name);
	}
	
	public Expression eval(List<Expression> arguments) throws Exception{
		this.arguments = arguments;	
		if (this.getBody() instanceof BlockStatement) {
			List<Statement> s = ((BlockStatement) this.getBody()).getStatements();
			
			for (int i=0; i<s.size()-1; i++) {
				s.get(i).eval();
			}
			//Reached return statement
			return ((ReturnStatement) s.get(s.size()-1)).evaluate();
			
		}
		else { //Single statement return
			return ((ReturnStatement) body).evaluate();
		}
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
	private void setProgram(Program p) {
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
		return this.arguments.get((int) parameterName.charAt(1)); 
		
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
	
	public List<Variable> getVariables() {
		return this.variables;
	}
}
