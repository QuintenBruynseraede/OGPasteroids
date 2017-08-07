package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement {
	private Expression angle;
	
	public TurnStatement(Expression angle, SourceLocation location) {
		super(location);
		setAngle(angle);
	}

	public Expression getAngle() {
		return angle;
	}

	public void setAngle(Expression angle) {
		this.angle = angle;
	}

	@Override
	public void eval() {
		if (this.getProgram().getTimeLeft() >= 0.2) {
			this.setLastStatement();
			double a;
			try {
				a = (Double) angle.eval();
			} catch (Exception e) {
				throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
			}
			
			
			try {
				this.getProgram().getShip().turn(a);
			} catch (AssertionError e) {
				throw new IllegalArgumentException();
			}
		}
		else 
			return;
			//throw new OutOfTimeException();
	}

}
