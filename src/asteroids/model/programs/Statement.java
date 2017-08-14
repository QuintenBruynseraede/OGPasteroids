package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class Statement {
	private double timeToExecute;
	private SourceLocation sourceLocation = new SourceLocation(0, 0);
	protected Program program;
	
	public Statement(SourceLocation s) {
		this.setSourceLocation(s);	
	}
	
	public abstract void eval() throws OutOfTimeException; //TODO not sure about void
	
		
	@Basic
	private void setSourceLocation(SourceLocation s) {
		this.sourceLocation = s;
	}
	
	@Basic
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	@Basic
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Basic
	public Program getProgram() {
		return this.program;
	}

	@Basic
	private void setTimeToExecute(double dt) {
		this.timeToExecute = dt;
	}

	@Basic
	public double getTimeToExecute() {
		return this.timeToExecute;
	}
	

	
}
