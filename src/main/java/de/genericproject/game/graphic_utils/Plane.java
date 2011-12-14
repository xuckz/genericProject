package de.genericproject.game.graphic_utils;

/**
 * a infinite plane
 */
public class Plane {
	
	Vector3f base;
	Vector3f normal;

	public Plane(Vector3f point, Vector3f normal)
	{
		base = new Vector3f(point);
		
		this.normal =  new Vector3f(normal);
		if(this.normal.length() != 1f)
			this.normal.normalize();
	}
	
	public float distanceTo(Vector3f point)
	{
		Vector3f v = new Vector3f(point.x - base.x, point.y - base.y, point.z - base.z);
		return normal.dotProduct(v);
	}
	
	@Override
	public String toString()
	{
		return "p"+base+", n"+normal;
	}
}
