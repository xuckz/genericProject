package de.genericproject.game.graphic_utils;

/**
 * a infinite plane
 */
public class Plane {
	
	Vector3f base;
	Vector3f normal;

	public Plane(Vector3f point, Vector3f normal)
	{
		base = point;
		this.normal = normal;
	}
}
