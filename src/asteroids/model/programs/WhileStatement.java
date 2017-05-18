package asteroids.model.programs;

import java.util.List;

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
	public void eval() throws Exception {
		this.setLastStatement();
		boolean ex;
		try {
			ex = (boolean) condition.eval();
		} catch (Exception e) {
			throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
		}
		while(ex) {
			try {
				body.eval();
			} catch (BreakException e1) {
				break;
			}
			try {
				ex = (boolean) condition.eval();
			} catch (Exception e) {
				throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
			}
		}
	}

	@Override
	public void addStatementsToList(List<Statement> statements) {
		statements.add(this);
	}
}
