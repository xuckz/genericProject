package de.genericproject.game.graphic_utils;

/**
 * this represents a frustum (6 sides of a camera view)
 */
public class Frustum {

	private Plane front, back, left, right, top, bottom;
	
	Vector3f vBase, vDirection;
	float dist_near, dist_far;
	
	public Frustum (Vector3f base, Vector3f direction, float dist_near, float dist_far)
	{
		vBase = new Vector3f(base);
		vDirection = new Vector3f(direction);
		vDirection.normalize();
		this.dist_near = dist_near;
		this.dist_far = dist_far;
		updatePlanes();
	}
	
	public void updatePosition(Vector3f base, Vector3f direction)
	{
		vBase = new Vector3f(base);
		vDirection = new Vector3f(direction);
		vDirection.normalize();
		updatePlanes();
	}
	
	private void updatePlanes()
	{
		updateFrontPlane();
		updateBackPlane();
	}
	
	private void updateFrontPlane()
	{
		front = new Plane(vBase.add(vDirection.multiply(dist_near)), vDirection);
	}
	
	private void updateBackPlane()
	{
		back = new Plane(vBase.add(vDirection.multiply(dist_far)), vDirection.multiply(-1));
	}
	
	public boolean isPointInside(Vector3f point)
	{
		if(front.distanceTo(point) > 0)
			if(back.distanceTo(point) > 0)
				return true;
		return false;
	}
	
}
