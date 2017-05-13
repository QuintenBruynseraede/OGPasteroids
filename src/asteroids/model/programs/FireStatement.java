package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class FireStatement extends Statement{
	public FireStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void eval() {
		this.getProgram().getShip().fire();
	}
}
