package asteroids.model.programs;

import java.util.List;

import javax.swing.text.AsyncBoxView;

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
		this.setLastStatement();
		
		if (!(getProgram().getFunctionByName(variableName) == null))
			throw new IllegalArgumentException(); //Function with identical name
		
		if (getProgram().getCurrentFunction() != null)
			getProgram().getCurrentFunction().addVariable(variableName, value);
		
		if (getProgram().getVariableByName(variableName) == null)
			this.getProgram().addVariable(variableName, value); //New variable
		else {
			if (value.eval() instanceof Double)
				this.getProgram().getVariableByName(variableName).setExpression(new ConstantExpression((double) value.eval(), getSourceLocation()));
			else if (value.eval() instanceof Boolean)
				this.getProgram().getVariableByName(variableName).setExpression(new BooleanExpression((boolean) value.eval(), getSourceLocation()));
			else if (value.eval() instanceof Entity)
				this.getProgram().getVariableByName(variableName).setExpression(new EntityExpression((Entity) value.eval(), getSourceLocation()));
		}
		
	}
	
	
}
