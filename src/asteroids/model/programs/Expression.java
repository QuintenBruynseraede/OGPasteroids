 package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	public SourceLocation sourceLocation;
	public Statement statement;
	
	public Expression(SourceLocation sourceLocation) {
		setStatement(statement);
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;	
	}
	
	public Statement getStatement() {
		return this.statement;
	}
	
	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;	
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

	public abstract T eval();
}
