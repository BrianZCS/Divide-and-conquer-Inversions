import java.util.ArrayList;
import java.util.Scanner;

public class InversionCount {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while (instances-- > 0) {
			int size = scan.nextInt();
			ArrayList<Integer> sequence = new ArrayList<>();
			while(size-- > 0) {
				sequence.add(scan.nextInt());
			}
			sequence = countSort(sequence);
			System.out.println(sequence.get(sequence.size()-1));
		}
		scan.close();
	}
	
	public static ArrayList<Integer> countSort(ArrayList<Integer> array){
		if(array.size()==1) {
			array.add(0);
			return array;
		}
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		ArrayList<Integer> merge = new ArrayList<>();
	    for (int i = 0; i < array.size(); i++)
	    {
	        if (i <= (array.size()-1)/2) {
	            left.add(array.get(i));
	        }
	        else {
	            right.add(array.get(i));
	        }
	    }
		left = countSort(left);
		right = countSort(right);
		merge = mergeSort(left,right);
		merge.set(merge.size()-1, merge.get(merge.size()-1)+left.get(left.size()-1)+right.get(right.size()-1));
		return merge;
	}
	
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> left,ArrayList<Integer> right){
		ArrayList<Integer> S = new ArrayList<>();
		int a = 0;
		int b = 0;
		int inversion = 0;
		//work like a stack
		while(a<left.size()-1||b<right.size()-1) {
			if(a == left.size()-1) {
				S.add(right.get(b));
				b++;
			}
			else if(b == right.size()-1) {
				S.add(left.get(a));
				a++;
			}
			else if(left.get(a)<=right.get(b)) {
				S.add(left.get(a));
				a++;
			}
			else {
				S.add(right.get(b));
				b++;
				inversion += left.size()-1-a;
			}
		}
		S.add(inversion);
		return S;
	}
}
