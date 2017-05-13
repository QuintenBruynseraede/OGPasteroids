package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends Statement{
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
		double a;
		try {
			a = (Double) angle.eval();
		} catch (Exception e) {
			throw new ClassCastException("Expression within while statement must evaluate to a boolean value");
		}
		this.getProgram().getShip().turn(a);
	}
}
