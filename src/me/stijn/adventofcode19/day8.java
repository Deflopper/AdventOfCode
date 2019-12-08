package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day8 {
	
	public static final int Y_SIZE = 25,X_SIZE = 6;
	
	public static void main(String[] args) throws IOException {
		String input = Utils.getString(8);
		
		ArrayList<Layer> layers = new ArrayList();

		for (int i = 0; i < input.length() / (X_SIZE * Y_SIZE); i++) {
			Layer lay = new Layer(X_SIZE, Y_SIZE, input.substring(X_SIZE * Y_SIZE * i, X_SIZE * Y_SIZE * (i + 1)));
			lay.decode();
			layers.add(lay);
		}
		
		Layer finalImage = new Layer(X_SIZE, Y_SIZE, "");
		Collections.reverse(layers);
		
		for (int i = 0;i<X_SIZE;i++) {
			for (int j = 0; j<Y_SIZE;j++) {
				Pixel overpoweringPixel = Pixel.TRANSPARENT;
				for (Layer lay : layers) {
					if (lay.getPixelAt(i, j).code != Pixel.TRANSPARENT.code) {
						overpoweringPixel = lay.getPixelAt(i, j);
					}
				}
				finalImage.setPixel(i, j, overpoweringPixel);
			}
		}
		
		finalImage.printImageToConsole();
	}

	public static void part1(String[] args) throws IOException {
		String input = Utils.getString(8);
		
		Layer highestLayer = null;
		
		for (int i = 0; i < input.length() / (X_SIZE * Y_SIZE); i++) {
			Layer lay = new Layer(X_SIZE, Y_SIZE, input.substring(X_SIZE * Y_SIZE * i, X_SIZE * Y_SIZE * (i + 1)));
			lay.decode();
			if (highestLayer == null || lay.getAmountofDigits(0) < highestLayer.getAmountofDigits(0)) {
				highestLayer = lay;
			}
		}
		
		System.out.println("Number of 1 multiplied by number of 2 digits on the layer with the least 0 digits: " + highestLayer.getAmountofDigits(1) * highestLayer.getAmountofDigits(2));
	}

	public static class Layer {
		
		private Integer[][] data;
		private String encoded;
		private int xsize,ysize;
		
		public Layer(int xsize, int ysize, String encoded) {
			this.data = new Integer[xsize][ysize];
			this.encoded = encoded;
			this.xsize = xsize;
			this.ysize = ysize;
		}
		
		public void decode() {
			int index = 0, row = 0;
			for (char c : encoded.toCharArray()) {
				if (index % ysize == 0 && index != 0) 
					row++;
				data[row][index % ysize] = Character.getNumericValue(c);
				index++;
			}
		}
		
		public Pixel getPixelAt(int x,int y) {
			return Pixel.values()[data[x][y]];
		}
		
		public void setPixel(int x, int y, Pixel p) {
			data[x][y] = p.code;
		}
		
		public int getAmountofDigits(int digit) {
			int sum = 0;
			for (int i = 0; i < xsize;i++) {
				for (int j = 0; j < ysize;j++) {
					if (data[i][j] == digit)
						sum++;
				}
			}
			return sum;
		}
		
		public void printImageToConsole() {
			for (int i = 0; i < xsize;i++) {
				System.out.println();
				for (int j = 0; j < ysize;j++) {
					System.out.print(data[i][j] != null && data[i][j] == Pixel.BLACK.code ? " " : "#");
				}
			}
		}
	}
	
	public static enum Pixel {
		BLACK(0),WHITE(1),TRANSPARENT(2);
		
		private int code;
		
		Pixel(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
}
