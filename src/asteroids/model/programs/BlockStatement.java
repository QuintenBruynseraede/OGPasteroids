package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
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
	public void eval() throws OutOfTimeException {
		for (Statement s : statements)
			s.eval();
	}

	
	public void setProgram(Program program) {
		this.program = program;
		
		for (Statement s : getStatements()) {
			s.setProgram(getProgram());
		}
	}

	@Override
	public void checkForIllegalStatements() {
		for (Statement s: getStatements())
			s.checkForIllegalStatements();
	}
}
