package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import be.kuleuven.cs.som.annotate.Basic;

public class Function<R> {
	private Statement body;
	private Program program;
	private List<Variable> variables;
	
	public Function(Statement body) {
		this.setBody(body);
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
//			return sqrt($1^2 + $2^2)
//					
//			statement return(e)
//			expression sqrt(expression)
//			expression som(linkerdeel, rechterdeel)
//			expression kwadraat(linkerdeel)
//			expression kwadraat(rechterdeel)
}
