package com.framework.util;

/**
 * 集合工具类
 * 
 * @author Administrator
 * 
 */
public class CollectionUtil {

	/**
	 * 交换数组中两元素
	 * 
	 * @since 1.1
	 * @param ints
	 *            需要进行交换操作的数组
	 * @param x
	 *            数组中的位置1
	 * @param y
	 *            数组中的位置2
	 * @return 交换后的数组
	 */
	public static int[] swap(int[] ints, int x, int y) {
		int temp = ints[x];
		ints[x] = ints[y];
		ints[y] = temp;
		return ints;
	}

	/**
	 * 冒泡排序 方法：相邻两元素进行比较 性能：比较次数O(n^2),n^2/2；交换次数O(n^2),n^2/4
	 * 
	 * @since 1.1
	 * @param source
	 *            需要进行排序操作的数组
	 * @return 排序后的数组
	 */
	public static int[] bubbleSort(int[] source) {
		for (int i = 1; i < source.length; i++) {
			for (int j = 0; j < i; j++) {
				if (source[j] > source[j + 1]) {
					swap(source, j, j + 1);
				}
			}
		}
		return source;
	}
	
	/**  
     * 快速排序 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。 步骤为：  
     * 1. 从数列中挑出一个元素，称为 "基准"（pivot）， 2.  
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面  
     * （相同的数可以到任一边）。在这个分割之后，该基准是它的最后位置。这个称为分割（partition）操作。 3.  
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。  
     * 递回的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了  
     * 。虽然一直递回下去，但是这个算法总会结束，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。  
     *  
     * @since 1.1  
     * @param source  
     *            需要进行排序操作的数组  
     * @return 排序后的数组  
     */  
    public static int[] quickSort(int[] source) {   
        return qsort(source, 0, source.length - 1);   
    } 

	/**
	 * 快速排序的具体实现，排正序
	 * 
	 * @since 1.1
	 * @param source 需要进行排序操作的数组
	 * @param low 开始低位
	 * @param high 结束高位
	 * @return 排序后的数组
	 */
	private static int[] qsort(int source[], int low, int high) {
		int i, j, x;
		if (low < high) {
			i = low;
			j = high;
			x = source[i];
			while (i < j) {
				while (i < j && source[j] > x) {
					j--;
				}
				if (i < j) {
					source[i] = source[j];
					i++;
				}
				while (i < j && source[i] < x) {
					i++;
				}
				if (i < j) {
					source[j] = source[i];
					j--;
				}
			}
			source[i] = x;
			qsort(source, low, i - 1);
			qsort(source, i + 1, high);
		}
		return source;
	}

}
