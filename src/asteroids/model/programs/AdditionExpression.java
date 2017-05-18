package asteroids.model.programs;



import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

public class AdditionExpression extends Expression<Double> {
	private Expression<Double> leftTerm;
	private Expression<Double> rightTerm;
	
	
	public AdditionExpression(Expression<Double> leftTerm, Expression<Double> rightTerm, SourceLocation sourceLocation) {
		super(sourceLocation);
		setLeftTerm(leftTerm);
		setRightTerm(rightTerm);
	}
	
	@Basic
	public Expression<Double> getLeftTerm() {
		return this.leftTerm;
	}
	
	@Basic
	public Expression<Double> getRightTerm() {
		return this.rightTerm;
	}

	@Basic
	private void setLeftTerm(Expression<Double> val) {
		this.leftTerm = val;
	}
	
	@Basic
	private void setRightTerm(Expression<Double> val) {
		this.rightTerm = val;
	}

	@Override
	public Double eval()  {
		return getLeftTerm().eval() + getRightTerm().eval();
	}
}
