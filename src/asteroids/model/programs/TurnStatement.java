package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement {
	private Expression<Double> angle;
	
	public TurnStatement(Expression<Double> angle, SourceLocation location) {
		super(location);
		setAngle(angle);
	}

	public Expression<Double> getAngle() {
		return angle;
	}

	public void setAngle(Expression<Double> angle) {
		this.angle = angle;
	}

	@Override
	public void eval() throws OutOfTimeException {
		if (getProgram().getLastExecutedStatement() != null) {
			if (getProgram().getLastExecutedStatement() == this) {
				getProgram().setLastExecutedStatement(null);
				System.out.println("Resuming execution after " + this);
				return;
			}
			else
				return;
		}
		
		double a;
		try {				
			a = (Double) angle.eval();
		} catch (Exception e) {
			throw new ClassCastException("");
		}
			
			
		try {
			this.getProgram().getShip().turn(a);
		} catch (AssertionError e) {
			throw new IllegalArgumentException();
		}
		advanceTime();
	}
}
