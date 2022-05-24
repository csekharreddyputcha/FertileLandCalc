package com.fertailland.calc;

public class Coordinate {

	private Integer x;
	private Integer y;
	private boolean isBarren;
	private boolean visited = false;

	public Coordinate() {

	}

	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public boolean isIsBarren() {
		return isBarren;
	}

	public void setIsBarren(boolean isBarren) {
		this.isBarren = isBarren;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
