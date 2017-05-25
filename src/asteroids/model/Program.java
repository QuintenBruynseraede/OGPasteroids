package asteroids.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import asteroids.model.programs.Expression;
import asteroids.model.programs.Function;
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
	private double timeLeft;
	private List<Function> functions;
	private List<Object> returns = new ArrayList<Object>();
	private List<Variable> globalVariables;
	private Statement main;
	private SourceLocation sourceLocation = new SourceLocation(0, 0);
	private Statement lastExecutedStatement;
	private List<Statement> statements = new ArrayList<Statement>();
	private boolean hasBeenExecuted;
	/**
	 * 	Expresses the name of the function that is being executed right now. 
	 * 	Holds a value of null is this program is currently not executing any function. 
	 */
	private Function currentFunction = null;
	
	public Program(List<Function> functions, Statement main) {
		setMain(main);
		setFunctions(functions);
		main.addStatementsToList(statements);
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
		this.timeLeft = time;
		//main.eval();
		int start = (hasBeenExecuted? this.statements.indexOf(lastExecutedStatement)+1 : 0);
		
		Iterator<Statement> i = statements.iterator();
		
		for(; start>1;--start) {
			i.next();
		}
		
		
		while(i.hasNext()) {
			try {
				i.next().eval();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		
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
	private void setFunctions(List<Function> functions2) {
		this.functions = functions2;
	}
	
	public Function getFunctionByName(String name) {
		List<Function> result = getFunctions().stream().filter(f -> f.getName().equals(name)).collect(Collectors.toList());

		if (result.isEmpty()) 
			return null;
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
		throw new IllegalArgumentException("Non-existent variable.");
		
		//TODO: functional programming
	}
	
	public List<Variable> getVariables() {
		return this.globalVariables;
	}
	
	public double getTimeLeft() {
		return this.timeLeft;
	}
	
	public void setTimeLeft(double time) {
		this.timeLeft = time;
	}

	public List<Statement> getStatementList() {
		return this.statements;
	}

	public Statement getLastExecutedStatement() {
		return lastExecutedStatement;
	}

	public void setLastExecutedStatement(Statement lastExecutedStatement) {
		this.lastExecutedStatement = lastExecutedStatement;
	}
	
	
}
