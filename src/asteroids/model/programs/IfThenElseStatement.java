package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class IfThenElseStatement extends Statement{

	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public IfThenElseStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);	
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getIfBody() {
		return ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}

	public Statement getElseBody() {
		return elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	@Override
	public void eval() {
		boolean ex;
		try {
			ex = (boolean) condition.eval();
		} catch (Exception e) {
			throw new ClassCastException("Expression within if statement must evaluate to a boolean value");
		}
		
		if (ex)
			ifBody.eval();
		else
			elseBody.eval();
	}
}
