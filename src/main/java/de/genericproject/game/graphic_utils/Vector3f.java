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
	
	public Vector3f(double x, double y, double z)
	{
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}
	
	public Vector3f(Vector3f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public void normalize()
	{
		float len = length();
		if(len != 1.0f)
		{
			x = x/len;
			y = y/len;
			z = z/len;
		}
	}
	
	public float length()
	{
		return (float)Math.sqrt((x*x + y*y + z*z));
	}
	
	public void addToThis(Vector3f v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public Vector3f add(Vector3f v)
	{
		return new Vector3f(x+v.x, y+v.y, z+v.z);
	}
	
	public void multiplyToThis(float scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}
	
	public Vector3f multiply(float scalar)
	{
		return new Vector3f(scalar*x, scalar*y, scalar*z);
	}
	
	public float dotProduct(Vector3f vec)
	{
		return vec.x * x + vec.y * y + vec.z * z;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getZ()
	{
		return z;
	}
	
	@Override
	public String toString()
	{
		return "("+x+","+y+","+z+")";
	}
}
