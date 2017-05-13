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
		// TODO Auto-generated method stub
		
	}
}
