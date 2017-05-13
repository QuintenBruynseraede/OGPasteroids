package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement {
	private Expression condition;
	private Statement body;
	
	public WhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		setBody(body);
		setCondition(condition);
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	@Override
	public void eval() {
		boolean ex;
		try {
			ex = (boolean) condition.eval();
		} catch (Exception e) {
			throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
		}
		while(ex) {
			body.eval();
			
			try {
				ex = (boolean) condition.eval();
			} catch (Exception e) {
				throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
			}
		}
	}
}
