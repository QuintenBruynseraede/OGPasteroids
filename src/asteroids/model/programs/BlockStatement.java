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
	public void eval() throws Exception {
		for (Statement s : statements)
			s.eval();
	}

	@Override
	public void addStatementsToList(List<Statement> listStatements) {
		for (Statement s : getStatements()) {
			s.addStatementsToList(listStatements);
		}
	}
	
	public void setProgram(Program program) {
		this.program = program;
		
		for (Statement s : getStatements()) {
			s.setProgram(this.getProgram());
		}
	}
}
