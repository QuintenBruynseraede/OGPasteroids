package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class GameObject {
	private final int type;
	
	public GameObject(int type) {
		this.type = type;
	}
	
	public GameObject() {
		type = Constants.BOUNDARY;
	}
	
	@Basic
	public int getType() {
		return this.type;
	}

}
