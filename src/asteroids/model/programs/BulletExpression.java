package asteroids.model.programs;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends Expression<Entity> {
	
	public BulletExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Entity eval() {
		Ship parent = getStatement().getProgram().getShip();
		World world = parent.getWorld();
		
		for (Entity entity : world.getEntitiesOfType(Bullet.class)) {
			Bullet bullet = (Bullet) entity;
			if (!bullet.isLoaded() && bullet.getParent() == parent) {
				return bullet;
			}
		}
		return null;
	}
	

}