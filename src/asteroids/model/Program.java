package asteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import asteroids.model.programs.ActionStatement;
import asteroids.model.programs.Expression;
import asteroids.model.programs.Function;
import asteroids.model.programs.OutOfTimeException;
import asteroids.model.programs.Statement;
import asteroids.model.programs.Variable;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * @version	1.0
 * @author Tom De Backer and Quinten Bruynseraede
 *
 */
public class Program {
	private Ship ship;
	private double timeLeft = 0;
	private List<Function> functions;
	private List<Object> returns = new ArrayList<Object>();
	private List<Variable<?>> globalVariables = new ArrayList<Variable<?>>();
	private Statement main;
	private SourceLocation sourceLocation = new SourceLocation(0, 0);
	private ActionStatement lastExecutedStatement = null;
	private boolean currentlyInWhile = false;
	private final static boolean assertionChecking = false;

	
	/**
	 * 	Expresses the name of the function that is being executed right now. 
	 * 	Holds a value of null is this program is currently not executing any function. 
	 */
	private Function currentFunction = null;
	
	public Program(List<Function> functions, Statement main) {
		setMain(main);
		setFunctions(functions);
		
		for (Function f: getFunctions()) {
			f.setProgram(this);
		}

		
		main.setProgram(this);
	}
	
	public void loadOnShip(Ship ship) {
		if (canLoadOnShip(ship))
			setShip(ship);
	}
	
	private boolean canLoadOnShip(Ship ship) {
		return (ship != null);
	}
	
	public List<Object> execute(double time) {
		timeLeft += time;
		
		if (timeLeft < 0.2)
			return null;
		
		try { main.eval(); }
		catch (OutOfTimeException e) {
			return null;
		}
		
		return returns;
	}
	
	@Basic
	public Ship getShip() {
		return this.ship;
	}
	
	@Basic
	private void setShip(Ship ship) {
		this.ship = ship;
	}
	
	@Basic
	public Statement getMainStatement() {
		return this.main;
	}
	
	@Basic
	private void setMain(Statement main) {
		this.main = main;
	}
	
	@Basic
	public List<Function> getFunctions() {
		return this.functions;
	}
	
	@Basic
	private void setFunctions(List<Function> functions) {
		this.functions = functions;

		for (Function f: functions) {
			f.setProgram(this);
		}
	}
	
	public Function getFunctionByName(String name) {
		List<Function> result = getFunctions().stream().filter(f -> f.getName().equals(name)).collect(Collectors.toList());

		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);	
		//The collected list will always contain just one element, thus taking the first element yields the correct result.
	}
	
	public Variable<?> getVariableByName(String name) {
		List<Variable<?>> result = getVariables().stream().filter(f -> f.getName().equals(name)).collect(Collectors.toList());
		
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);	
		//The collected list will always contain just one element, thus taking the first element yields the correct result.
	}
	
	public void addReturnItem(Object o) {
		this.returns.add(o);
	}

	
	public Function getCurrentFunction() {
		return this.currentFunction;
	}
	
	public void setCurrentFunction(Function func) {
		this.currentFunction = func;
	}
	
	public void addVariable(String varName, Expression value) {
		this.globalVariables.add(new Variable(varName, value));
	}
	public Object getVariableValue(String varName) {
		for (Variable v : this.getVariables()) {
			if (v.getName().equals(varName))
				return v.eval();
		}
		throw new IllegalArgumentException("Non-existent global variable " + varName);
	}
	
	public List<Variable<?>> getVariables() {
		return this.globalVariables;
	}
	
	public double getTimeLeft() {
		return this.timeLeft;
	}
	
	private void setTimeLeft(double time) {
		this.timeLeft = time;
	}
	
	public void subtractTime() {
		setTimeLeft(getTimeLeft() - 0.2);
	}


	public Statement getLastExecutedStatement() {
		return lastExecutedStatement;
	}

	public void setLastExecutedStatement(ActionStatement lastExecutedStatement) {
		this.lastExecutedStatement = lastExecutedStatement;
	}

	public boolean isCurrentlyInWhile() {
		return currentlyInWhile;
	}

	public void setCurrentlyInWhile(boolean currentlyInWhile) {
		this.currentlyInWhile = currentlyInWhile;
	}
	
	public boolean getAssertionChecking() {
		return assertionChecking;
	}	
	
}
