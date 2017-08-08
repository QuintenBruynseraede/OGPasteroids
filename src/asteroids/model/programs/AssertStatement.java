package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class AssertStatement extends Statement {
	private Expression<Boolean> predicate;
	
	public AssertStatement(Expression<Boolean> predicate, SourceLocation sourceLocation) {
		super(sourceLocation);
		//System.out.println("Value set for " + v);
		setPredicate(predicate);
		predicate.setStatement(this);
	}

	public Expression<Boolean> getPredicate() {
		return predicate;
	}

	public void setPredicate(Expression<Boolean> predicate) {
		if (canHaveAsPredicate(predicate))
			this.predicate = predicate;
	}
	
	public boolean canHaveAsPredicate(Expression<Boolean> predicate) {
		return (predicate != null);
	}

	@Override
	public void eval() {
		this.setLastStatement();
		if (predicate.eval() == false)
			throw new AssertionError("[Assert] Predicate not true");
	}

}

