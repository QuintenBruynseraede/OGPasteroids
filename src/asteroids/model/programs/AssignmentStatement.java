package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement {
	private String variableName;
	private Expression<?> value;
	
	public AssignmentStatement(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		setValue(value);
		setVariableName(variableName);
		value.setStatement(this);
	}

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	

	@Override
	public void eval() {
		if (getProgram().getLastExecutedStatement() != null)
			return;
		
		if (getProgram().getCurrentFunction() != null) { //Add as local variable	
			//System.out.println("Adding local variable " + variableName);
			getProgram().getCurrentFunction().addVariable(variableName, value);
			return;
		}
		
		if (!(getProgram().getFunctionByName(variableName) == null)) {
			throw new IllegalArgumentException("Variable already exists as function"); //Function with identical name
		}
		
		if (getProgram().getVariableByName(variableName) == null) {
			//System.out.println("Adding global variable " + variableName);
			this.getProgram().addVariable(variableName, value); //New variable
		}
		else {
			//System.out.println("Modifying value of existing global variable " + variableName + " to " + (double) value.eval());
			if (value.eval() instanceof Double)
				this.getProgram().getVariableByName(variableName).setExpression(new ConstantExpression((double) value.eval(), getSourceLocation()));
			else if (value.eval() instanceof Boolean)
				this.getProgram().getVariableByName(variableName).setExpression(new BooleanExpression((boolean) value.eval(), getSourceLocation()));
			else if (value.eval() instanceof Entity)
				this.getProgram().getVariableByName(variableName).setExpression(new EntityExpression((Entity) value.eval(), getSourceLocation()));
		}
		
	}
	
	
}
