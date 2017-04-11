package asteroids.model;

public class Boundary extends GameObject {
	public final int boundaryType;
	
	public Boundary(int type) {
			this.boundaryType = type;
	}
	
	public int getBoundaryType() {
		return this.boundaryType;
	}
	
}

