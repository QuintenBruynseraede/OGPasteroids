package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Basic;

public class MultiplicationExpression implements Expression<Double> {
	private Expression<Double> leftTerm;
	private Expression<Double> rightTerm;
	
	
	public MultiplicationExpression(Expression<Double> leftTerm, Expression<Double> rightTerm) {
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
	public Double eval() {
		return ((Double) getLeftTerm().eval()).doubleValue() *  (((Double) getRightTerm().eval()).doubleValue());
	}
}
