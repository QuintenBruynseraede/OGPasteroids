package asteroids.model.programs;

import java.util.List;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program>{

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);	
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
			try {
				return new Function(body, functionName, sourceLocation);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		return new ReturnStatement(value,sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfThenElseStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new BlockStatement(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ReadParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		return new NegativeExpression(expression, sourceLocation);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		return new IsNotExpression(expression, sourceLocation);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new ConstantExpression(value, location);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		return new EntityExpression(null, location);
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		return new SelfExpression(location);
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		return new ClosestEntityExpression(Ship.class, location);
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		return new ClosestEntityExpression(Asteroid.class, location);
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		return new ClosestEntityExpression(Planetoid.class, location);
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		return new BulletExpression(location);
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		return new ClosestEntityExpression(MinorPlanet.class, location);
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		return new AnyEntityExpression(location);
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		return new GetXExpression(e, location);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		return new GetYExpression(e, location);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		return new GetVelocityXExpression(e, location);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		return new GetVelocityYExpression(e, location);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		return new GetRadiusExpression(e, location);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		return new IsSmallerThanExpression(e1, e2, location);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		return new IsEqualToExpression(location, e1, e2);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		return new AdditionExpression(e1, e2, location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		return new MultiplicationExpression(e1, e2, location);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		return new SquareRootExpression(e, location);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionExpression(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ThrustStatement(location, true);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ThrustStatement(location, false);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new FireStatement(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		return new TurnStatement(angle, location);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new SkipStatement(location);
	}

}
