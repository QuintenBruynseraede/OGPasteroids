package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class BlockStatement extends Statement{
	private List<Statement> statements;
	
	 public BlockStatement(List<Statement> statements, SourceLocation sourceLocation) {
		 super(sourceLocation);
		 setStatements(statements);
	 }

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public void eval() {
		for (Statement s : statements)
			s.eval();
	}
}
