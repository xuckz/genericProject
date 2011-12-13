package de.genericproject.game.graphic_utils;

public class Vector3f {

	float x;
	float y;
	float z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public void normalize()
	{
		if(length() != 1.0f)
		x = x/length();
		y = y/length();
		z = z/length();
	}
	
	public float length()
	{
		return (float)Math.sqrt((x*x + y*y + z*z));
	}
	
	public Vector3f add(Vector3f v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
	
	public Vector3f multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public float dotProduct(Vector3f vec)
	{
		return vec.x * x + vec.y * y + vec.z * z;
	}
	
}
