
package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Collision {
	public Collision(GameObject object1, GameObject object2, double x, double y, double time, int type) {
		this.setObject1(object1);
		this.setObject2(object2);
		this.setX(x);
		this.setY(y);
		this.setTime(time);
		this.setType(type);
	}
	
	
	private double x;
	
	/**
	 * Return the X coordinate of this collision.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Return the Y coordinate of this collision.
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/**
	 * Return the time to this collision.
	 */
	@Basic
	public double getTime() {
		return this.time;
	}
	
	/**
	 * Return the first object of this collision.
	 */
	@Basic
	public GameObject getObject1() {
		return this.object1;
	}
	
	/**
	 * Return the second object of this collision.
	 */
	@Basic
	public GameObject getObject2() {
		return this.object2;
	}
	
	/**
	 * Return the type of this collision.
	 */
	@Basic
	public int getType() {
		return this.type;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setObject1(GameObject object) {
		this.object1 = object;
	}
	
	public void setObject2(GameObject object) {
		this.object2 = object;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	private double y;
	private GameObject object1;
	private GameObject object2;
	private double time;
	private int type;
}
