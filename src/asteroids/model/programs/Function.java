package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import be.kuleuven.cs.som.annotate.Basic;

public class Function<R> {
	private Statement body;
	private Program program;
	private List<Expression> variables;
	private String name;
	
	public Function(Statement body, String name) {
		this.setBody(body);
		this.setName(name);
	}
	
	public R eval(){
		return null;		
	}
	
	@Basic
	private void setBody(Statement s) {
		this.body = s;
	}
	
	@Basic
	public Statement getbody() {
		return this.body;
	}
	
	@Basic
	private void setProgram(Program p) {
		this.program = p;
	}
	
	@Basic
	public Program getProgram() {
		return this.program;
	}
	
	@Basic
	public String getName() {
		return this.name;
	}
	
	@Basic
	private void setName(String name) {
		this.name = name;
	}
//			return sqrt($1^2 + $2^2)
//					
//			statement return(e)
//			expression sqrt(expression)
//			expression som(linkerdeel, rechterdeel)
//			expression kwadraat(linkerdeel)
//			expression kwadraat(rechterdeel)
}
