package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends ActionStatement {
	
	public SkipStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void eval() {
		if (this.getProgram().getTimeLeft() >= 0.2) {
			this.setLastStatement();
			//Nothing
		}
		else 
			//throw new OutOfTimeException();
			return;
		
	}


}
