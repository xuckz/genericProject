package de.genericproject.game;

import de.genericproject.game.graphic_utils.Frustum;
import de.genericproject.game.graphic_utils.Vector3f;

public class Camera
{
    private double xPos;
    private double yPos;
    private double zPos;

    private double xLPos;
    private double yLPos;
    private double zLPos;

    private double pitch;
    private double yaw;
    
    private Frustum frustum;

    public Camera()
    {
        xPos = 0;
        yPos = 2;
        zPos = 0;

        xLPos = 0;
        yLPos = 0;
        zLPos = 10;
        
        frustum = new Frustum(new Vector3f(xPos, yPos, zPos), new Vector3f(xLPos - xPos, yLPos - yPos, zLPos - zPos), 1f, 100f);
    }

    public Camera(double xPos, double yPos, double zPos, double xLPos, double yLPos, double zLPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;

        this.xLPos = xLPos;
        this.yLPos = yLPos;
        this.zLPos = zLPos;
        
        frustum = new Frustum(new Vector3f(xPos, yPos, zPos), new Vector3f(xLPos - xPos, yLPos - yPos, zLPos - zPos), 1f, 100f);
    }

    public void setPitch(double pitch)
    {
     this.pitch = pitch;
    }

    public void setYaw(double yaw)
    {
     this.yaw = yaw;
    }

    public void updatePosition(double xPos, double yPos, double zPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public void lookPosition(double xLPos, double yLPos, double zLPos)
    {
        this.xLPos = xLPos;
        this.yLPos = yLPos;
        this.zLPos = zLPos;
    }

    public void moveForward(double magnitude)
    {
        double xCurrent = this.xPos;
        double yCurrent = this.yPos;
        double zCurrent = this.zPos;

        // Spherical coordinates maths
        double xMovement = magnitude * Math.cos(pitch) * Math.cos(yaw);
        double yMovement = magnitude * Math.sin(pitch);
        double zMovement = magnitude * Math.cos(pitch) * Math.sin(yaw);

        double xNew = xCurrent + xMovement;
        double yNew = yCurrent + yMovement;
        double zNew = zCurrent + zMovement;

        updatePosition(xNew, yNew, zNew);
    }

    public void strafeLeft(double magnitude)
    {
        double pitchTemp = pitch;
        pitch = 0;
        yaw = yaw - (0.5 * Math.PI);
        moveForward(magnitude);

        pitch = pitchTemp;
        yaw = yaw + (0.5 * Math.PI);
    }

    public void strafeRight(double magnitude)
    {
        double pitchTemp = pitch;
        pitch = 0;

        yaw = yaw + (0.5 * Math.PI);
        moveForward(magnitude);
        yaw = yaw - (0.5 * Math.PI);

        pitch = pitchTemp;
    }


    public void look(double distanceAway)
    {
        if(pitch > 1.0)
        	pitch = 0.99;

        if(pitch < -1.0)
        	pitch = -0.99;

        moveForward(10);

        double xLook = xPos;
        double yLook = yPos;
        double zLook = zPos;

        moveForward(-10);

        lookPosition(xLook, yLook, zLook);
        frustum.updatePosition(new Vector3f(xPos, yPos, zPos), new Vector3f(xLPos-xPos, yLPos-yPos, zLPos-zPos));
    }

    public double getXPos()
    {
        return xPos;
    }

    public double getYPos()
    {
        return yPos;
    }

    public double getZPos()
    {
        return zPos;
    }

    public double getXLPos()
    {
        return xLPos;
    }

    public double getYLPos()
    {
        return yLPos;
    }

    public double getZLPos()
    {
        return zLPos;
    }

    public double getPitch()
    {
        return pitch;
    }

    public double getYaw()
    {
        return yaw;
    }

    public void pitchUp(double amount)
    {
        this.pitch += amount;
    }

    public void pitchDown(double amount)
    {
        this.pitch -= amount;
    }

    public void yawRight(double amount)
    {
        this.yaw += amount;
    }

    public void yawLeft(double amount)
    {
        this.yaw -= amount;
    }
    
    public boolean isPointVisible(Vector3f point)
    {
    	return frustum.isPointInside(point);
    }
    
    public boolean isBoundingBoxVisible(BoundingBox box)
    {
    	return (frustum.isPointInside(box.getBottomLeftFrontPoint()) || frustum.isPointInside(box.getTopRightBackPoint())
    			|| frustum.isPointInside(box.getBottomLeftBackPoint()) || frustum.isPointInside(box.getBottomRightFrontPoint())
    			|| frustum.isPointInside(box.getBottomRightBackPoint()) || frustum.isPointInside(box.getTopLeftFrontPoint())
    			|| frustum.isPointInside(box.getTopLeftBackPoint()) || frustum.isPointInside(box.getTopRightFrontPoint()) );
    }
}
