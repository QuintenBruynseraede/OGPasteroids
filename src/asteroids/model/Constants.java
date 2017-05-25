package asteroids.model;

/**
* 	Class containing the game's basic constants in a readable way. 
* 	Class-specific constants are defined in their respective class
* 
* @author Tom De Backer and Quinten Bruynseraede
* @version	1.0
* 
**/
public abstract class Constants {
	/*
	* Boundary types
	**/
	public final static int LEFT = 1;
	public final static int TOP = 2;
	public final static int RIGHT = 3;
	public final static int BOTTOM = 4;
	
	/*
	* GameObject types
	**/
	public final static int BOUNDARY = 0;
	public final static int ENTITY = 1;
	public final static int WORLD = 2;
	
	/*
	* Collision types
	**/
	public final static int ENTITYCOLLISION = 2;
	public final static int BOUNDARYCOLLISION = 1;
}
