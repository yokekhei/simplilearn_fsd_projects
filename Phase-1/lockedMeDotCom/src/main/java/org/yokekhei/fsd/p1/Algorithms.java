package org.yokekhei.fsd.p1;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class Algorithms {
	
	public Algorithms() {
	}
	
	public static void sort(List<File> list, Comparator<File> c) {
		Object[] a = list.toArray();
		mergeSort(a, c);
		ListIterator<File> i = list.listIterator();
        for (Object o : a) {
            i.next();
            i.set((File) o);
        }
	}
	
	public static int binarySearch(List<File> list, File key, Comparator<File> c) {
		int low = 0;
		int high = list.size() - 1;
		int mid = (low + high)/2;
		
		while (low <= high) {
			File midItem = list.get(mid);
			if (c.compare(midItem, key) == 0) {
				return mid;
			} else if (c.compare(midItem, key) < 0) {
				low = mid + 1;
			} else {
				high = mid -1;
			}
			
			mid = (low + high)/2;
		}
		
		return -(low + 1);
	}
	
	private static void mergeSort(Object[] ar, Comparator<File> c) {
		if(ar.length > 1) {
			int mid = ar.length/2;
			
			Object left[] = new Object[mid];
			for (int i = 0; i < left.length; i++) {
				left[i] = ar[i];
			}
			
			Object right[] = new Object[ar.length - mid];
			for (int i = mid; i < ar.length; i++) {
				right[i - mid] = ar[i];
			}
			
			mergeSort(left, c);
			mergeSort(right, c);
			
			int i = 0;
			int j = 0;
			int k = 0;
			
			while (i < left.length && j < right.length) {
				if (c.compare((File)left[i], (File)right[j]) <= 0) {
					ar[k] = left[i];
					i++;
				} else {
					ar[k] = right[j];
					j++;
				}
				
				k++;
			}
			
			while (i < left.length) {
				ar[k++] = left[i++];
			}
			
			while (j < right.length) {
				ar[k++] = right[j++];
			}
		}
	}
	
}
