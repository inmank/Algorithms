package week1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CountInversion {

	//static int[] arrIn = {1, 3, 5, 2, 4, 6};/*{38, 27, 43, 3, 9, 82, 10}*/;
	//static int[] arrIn = {  4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 };

	long count = 0;

	public int[] divAndConq(int[] arrayIn) {

		int elemLen = arrayIn.length;
		int[] outArr = new int[elemLen];

		if ( elemLen > 1) {
			int size = (int) Math.round(elemLen/2);
			int[] arrA = Arrays.copyOfRange(arrayIn, 0, size);
			int[] arrB = Arrays.copyOfRange(arrayIn, size, elemLen);

			if (arrA.length > 1) { 
				arrA = divAndConq(arrA);
			}

			if (arrB.length > 1) {
				arrB = divAndConq(arrB);
			}

			outArr = merge(arrA, arrB);
		} else {
			outArr = arrayIn;
		}



		return outArr;
	}

	private int[] merge(int[] arrA, int[] arrB) {
		int i = 0, j = 0;
		int[] outArr = new int[arrA.length + arrB.length];

		for (int k =0; k < outArr.length; k++) {
			if (i >= arrA.length) {
				outArr[k] = arrB[j];
				j++;
			} else if (j >= arrB.length) {
				outArr[k] = arrA[i];
				i++;
			} else {
				if (arrA[i] < arrB[j]) {
					outArr[k] = arrA[i];
					i++;
				} else if (arrB[j] < arrA[i]){
					outArr[k] = arrB[j];
					j++;
					count = count + (arrA.length-i);
				} else {
					System.out.println("Catch");
				}
			}
		}


		return outArr;
	}

	public static void main(String[] args) {
		CountInversion ci = new CountInversion();
		FileReader fr;
		BufferedReader br;
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		String line;
		int[] dataArr = new int[100000];
		int count = 0;
		try {
			fr = new FileReader(new File("E:\\IntegerArray.txt"));
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null) {
				dataList.add(Integer.parseInt(line));
				dataArr[count++] = Integer.parseInt(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ci.divAndConq(dataArr);
		System.out.println(ci.count);
	}
}
