package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {
	public ActionStatement(SourceLocation s) {
		super(s);
	}

	@Override
	public abstract void eval() throws Exception;
	
}
