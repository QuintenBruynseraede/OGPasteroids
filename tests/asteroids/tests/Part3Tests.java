package asteroids.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class Part3Tests {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	@Test
	public void TestGetEntitiesOfType() throws ModelException {
		Ship ship = facade.createShip(100.0, 120.0, 10.0, 0.0, 50.0, Math.PI, 1.1E18);
	    Bullet bullet1 = facade.createBullet(200.0, 120.0, -10.0, 0.0, 20.0);
	    Bullet bullet2 = facade.createBullet(150.0, 220.0, -10.0, 0.0, 20.0);
	    //Asteroid asteroid = new Asteroid(300, 0, 3, 5, 7);
	    World world = facade.createWorld(3000, 3000);
	    
	    facade.addBulletToWorld(world, bullet1);
	    facade.addBulletToWorld(world, bullet2);
	    facade.addShipToWorld(world, ship);
	    
	    assertEquals(world.getEntitiesOfType(Bullet.class).size(), 2);
	    assertTrue(world.getEntitiesOfType(Bullet.class).contains(bullet1));
	    assertTrue(world.getEntitiesOfType(Bullet.class).contains(bullet2));
	    assertTrue(world.getEntitiesOfType(Ship.class).contains(ship));
	    assertEquals(world.getEntitiesOfType(Ship.class).size(), 1);
	}
	
	@Test
	public void TestGetY_LegalCase() throws ModelException {
	    max_score += 3;
	    String code = "print 5; ";
	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
	    System.out.println(program.toString());
	    System.out.println(program == null);
	    facade.loadProgramOnShip(ship1, program);
	    List<Object> results = facade.executeProgram(ship1, 1.0);
	    System.out.println(results.size());
	  }
}
