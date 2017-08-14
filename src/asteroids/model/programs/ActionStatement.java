package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {
	public ActionStatement(SourceLocation s) {
		super(s);
	}

	@Override
	public abstract void eval() throws OutOfTimeException;
	
	public void advanceTime() throws OutOfTimeException {
		getProgram().subtractTime();
		if (getProgram().getTimeLeft() < 0.2 && getProgram().getCurrentFunction() == null) {
			setLastStatement();
			throw new OutOfTimeException();
		}
			
	}
	
	public void setLastStatement() {
		this.getProgram().setLastExecutedStatement(this);
	}
}
