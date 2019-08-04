package roboticHoover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main (String args[]) throws FileNotFoundException {
		int dim_x=0, dim_y=0; // room dimension X and Y
		int start_x = 0, start_y = 0; // starting point X and Y
		
		// Read File to count lines:
		File inputFile = new File("input.txt");
		Scanner sc = new Scanner(inputFile);
		int lineCounter = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			lineCounter++;
		}
		sc.close();
		
		// Read File to get route, patches, starting point, dimensions:
		int[][] patches = new int[lineCounter - 3][2];
		String[] route = null;
		int tempCounter = 0;
		sc = new Scanner(inputFile);
		while (sc.hasNextLine()) {
			String tempStr = sc.nextLine();
			String[] splitStr = tempStr.split(" ");
			if (tempCounter == 0) {
				dim_x = Integer.parseInt(splitStr[0]);
				dim_y = Integer.parseInt(splitStr[1]);
			} else if (tempCounter == 1) {
				start_x = Integer.parseInt(splitStr[0]);
				start_y = Integer.parseInt(splitStr[1]);
			} else if (tempCounter != lineCounter-1) {
				patches[tempCounter-2][0] = Integer.parseInt(splitStr[0]);
				patches[tempCounter-2][1] = Integer.parseInt(splitStr[1]);
			} else {
				route = tempStr.split("");
			}
			tempCounter++;
		}
		sc.close();
		System.out.println(hoovering(route, patches, dim_x, dim_y, start_x, start_y));	
	}
	
	private static int hoovering(String[] route, int[][] patches, int dim_x, int dim_y, int start_x, int start_y) {
		int noPatches = 0;
		int cur_x=start_x, cur_y =start_y;
		for (int k=0; k<route.length; k++) {
			if (route[k].equals("N") && cur_y!=dim_y) cur_y++;
			else if (route[k].equals("E") && cur_x!=dim_x) cur_x++;
			else if (route[k].equals("S") && cur_y!=0) cur_y--;
			else if (route[k].equals("W") && cur_x!=0) cur_x--;
			
			for (int i=0; i<patches.length; i++){
				if (cur_x== patches[i][0] && cur_y==patches[i][1]) {
					patches[i][0] = dim_x;
					patches[i][1] = dim_y;
					noPatches++;
				}
			}
		}
		System.out.println(cur_x+" "+ cur_y);
		return noPatches;
	}
}
