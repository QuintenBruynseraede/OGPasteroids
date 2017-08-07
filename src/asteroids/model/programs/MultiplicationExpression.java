package asteroids.model.programs;



import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

public class MultiplicationExpression extends Expression<Double> {
	private Expression<Double> leftFactor;
	private Expression<Double> rightFactor;
	
	
	public MultiplicationExpression(Expression<Double> leftFactor, Expression<Double> rightFactor, SourceLocation sourceLocation) {
		super(sourceLocation);
		setLeftFactor(leftFactor);
		setRightFactor(rightFactor);
	}
	
	@Basic
	public Expression<Double> getLeftFactor() {
		return this.leftFactor;
	}
	
	@Basic
	public Expression<Double> getRightFactor() {
		return this.rightFactor;
	}

	@Basic
	private void setLeftFactor(Expression<Double> val) {
		this.leftFactor = val;
	}
	
	@Basic
	private void setRightFactor(Expression<Double> val) {
		this.rightFactor = val;
	}

	@Override
	public Double eval() {
		getLeftFactor().setStatement(getStatement());
		getRightFactor().setStatement(getStatement());
		return getLeftFactor().eval() * getRightFactor().eval();
	}
}
