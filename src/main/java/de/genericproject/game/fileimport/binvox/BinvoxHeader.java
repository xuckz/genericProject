package de.genericproject.game.fileimport.binvox;

public class BinvoxHeader {
	private int depth, height, width;
	private double translationX, translationY, translationZ;
	private double scale;
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getTranslationX() {
		return translationX;
	}
	
	public void setTranslationX(double tx) {
		this.translationX = tx;
	}
	
	public double getTranslationY() {
		return translationY;
	}
	
	public void setTranslationY(double ty) {
		this.translationY = ty;
	}
	public double getTranslationZ() {
		return translationZ;
	}
	
	public void setTranslationZ(double tz) {
		this.translationZ = tz;
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	
}
