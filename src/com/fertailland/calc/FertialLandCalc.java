package com.fertailland.calc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class FertialLandCalc {	

    private static final int X_TILES = 400 ;
    private static final int Y_TILES = 600;

    private static Coordinate[][] grid = new Coordinate[X_TILES][Y_TILES];
    
    public static void main(String[] args) {
        String[] STDIN = {"0 292 399 307"};
//        String[] STDIN = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};

        String STDOUT = findFertileLand(STDIN);
        System.out.println(STDOUT);

    }

	public static String findFertileLand(String[] rectangleCornersArray) {
        List<Integer> fertileLand = new ArrayList<>();
        List<Integer[]> barrenLandEndPoints = getBarrenLandCoordinates(rectangleCornersArray);
        List<Coordinate> totalBarrenLand = new ArrayList<>();
        for (Integer[] rectangle : barrenLandEndPoints) {
            totalBarrenLand.addAll(findTotalBarrenLandForRectangle(rectangle));
        }
        
        for(int x=0;x<X_TILES;x++) {
        	for(int y=0;y<Y_TILES;y++) {
        		Coordinate co = new Coordinate(x, y);
        		for(Coordinate c: totalBarrenLand) {
        			if(c.getX()==x && c.getY()==y) {
        				co.setIsBarren(true);
        				co.setVisited(true);
        				break;
        			}
        			else {
        				co.setVisited(false);
        			}
        		}
        		grid[x][y]=co;
        	}
        }
        fertileLand = checkForUnvisitedAreasAndCountFertileLand(fertileLand, 0, 0);
		Collections.sort(fertileLand);

		String STDOUT = "";

		if (!fertileLand.isEmpty()) {
			for (Integer land : fertileLand) {
				STDOUT += land.toString() + " ";
			}
		} else {
			STDOUT = "No fertile land available.";
		}

		return STDOUT;

	}

	public static List<Integer> checkForUnvisitedAreasAndCountFertileLand(List<Integer> fertileLand, int xVal, int yVal) {
	    for (int y = yVal; y < Y_TILES; y++) {
            for (int x = xVal; x < X_TILES; x++) {
        		Coordinate co = grid[x][y];
        		if(!co.isVisited()) {
        			int totalFertileArea = fertileLandFill(grid, x, y);
        			fertileLand.add(totalFertileArea);
                  //  checkForUnvisitedAreasAndCountFertileLand(fertileLand, x, y);
        		}
            }
	    }
		return fertileLand;
	}

	public static int fertileLandFill(Coordinate[][] grid, int x, int y) {
		int count=0;
		Stack<Coordinate> stack = new Stack<>();
		stack.push(new Coordinate(x, y));
		while(!stack.empty()) {
			Coordinate c = stack.pop();
			if(isCoordinateUnvisited(grid, c)) {
				count ++;
				if (c.getY() - 1 >= 0 && !grid[c.getX()][c.getY() - 1].isVisited()) {
					stack.push(new Coordinate(c.getX(), c.getY() - 1));
				}
				if (c.getY() + 1 < Y_TILES && !grid[c.getX()][c.getY() + 1].isVisited()) {
					stack.push(new Coordinate(c.getX(), c.getY() + 1));
				}
				if (c.getX() - 1 >= 0 && !grid[c.getX() - 1][c.getY()].isVisited()) {
					stack.push(new Coordinate(c.getX() - 1, c.getY()));
				}
				if (c.getX() + 1 < X_TILES && !grid[c.getX() + 1][c.getY()].isVisited()) {
					stack.push(new Coordinate(c.getX() + 1, c.getY()));
				}
				
			}
		}
		return count;
	}

	public static boolean isCoordinateUnvisited(Coordinate[][] grid, Coordinate c) {
	    if (c.getX() < 0 || c.getY() < 0 || c.getX() >= X_TILES || c.getY() >= Y_TILES) {
            return false;
        }
        Coordinate coordinateToCheck = grid[c.getX()][c.getY()];
        if (coordinateToCheck.isVisited()) {
            return false;
        }
        coordinateToCheck.setVisited(true);
        return true;
    }

	public static Collection<? extends Coordinate> findTotalBarrenLandForRectangle(Integer[] rectangle) {
        List<Coordinate> allBarrenLandCoordinates = new ArrayList<>();
		for (int x = rectangle[0]; x <= rectangle[2]; x++) {
			for (int y = rectangle[1]; y <= rectangle[3]; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				allBarrenLandCoordinates.add(coordinate);
			}
		}
		return allBarrenLandCoordinates;
	}

	public static List<Integer[]> getBarrenLandCoordinates(String[] rectangleCornerArray) {
		List<Integer[]> rectanglePoints = new ArrayList<>();
		for(int h=0;h<rectangleCornerArray.length;h++) {
			String[] strRectangleCorner = rectangleCornerArray[h].split(" ");
			Integer[] intRectangleCorner = new Integer[strRectangleCorner.length];
			for(int i=0; i<strRectangleCorner.length;i++	) {
				intRectangleCorner[i] =  Integer.parseInt(strRectangleCorner[i]);
			}
			rectanglePoints.add(intRectangleCorner);
		}
		
		return rectanglePoints;
	}

}
