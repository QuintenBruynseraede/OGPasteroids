package asteroids.model;

import java.util.List;

import asteroids.model.programs.Expression;
import asteroids.model.programs.Function;
import asteroids.model.programs.Statement;
import asteroids.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

public class Program {
	private Ship ship;
	private double timeLeft;
	private List<Function> functions;
	private List<Object> returns;
	private List<Expression> variables;
	private Statement main;
	private SourceLocation sourceLocation = new SourceLocation(0, 0);
	
	public Program(List<Function> functions, Statement main) {
		setMain(main);
		setFunctions(functions);
	
	}
	
	public void loadOnShip(Ship ship) {
		setShip(ship);
	}
	
	public List<Object> execute() {
		//this.sourceLocation = main.getSourceLocation();
		//while (isValidsourceLocation(this.sourcelocation + 1 lijn))
		//	statement.execute(sourcelocation + 1lijn)
		return null;
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
	public Statement getMain() {
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
	}
	
	public Function getFunctionByName(String name) {
		for (Function f : this.getFunctions()) {
			if (f.getName() == name) {
				return f;
			}
		}
		//TODO map filter return
		return null;
	}
}
