package com.framework.util;

/**
 * ���Ϲ�����
 * 
 * @author Administrator
 * 
 */
public class CollectionUtil {

	/**
	 * ������������Ԫ��
	 * 
	 * @since 1.1
	 * @param ints
	 *            ��Ҫ���н�������������
	 * @param x
	 *            �����е�λ��1
	 * @param y
	 *            �����е�λ��2
	 * @return �����������
	 */
	public static int[] swap(int[] ints, int x, int y) {
		int temp = ints[x];
		ints[x] = ints[y];
		ints[y] = temp;
		return ints;
	}

	/**
	 * ð������ ������������Ԫ�ؽ��бȽ� ���ܣ��Ƚϴ���O(n^2),n^2/2����������O(n^2),n^2/4
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @return ����������
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
     * �������� ��������ʹ�÷��η���Divide and conquer����������һ�����У�list����Ϊ���������У�sub-lists���� ����Ϊ��  
     * 1. ������������һ��Ԫ�أ���Ϊ "��׼"��pivot���� 2.  
     * �����������У�����Ԫ�رȻ�׼ֵС�İڷ��ڻ�׼ǰ�棬����Ԫ�رȻ�׼ֵ��İ��ڻ�׼�ĺ���  
     * ����ͬ�������Ե���һ�ߣ���������ָ�֮�󣬸û�׼���������λ�á������Ϊ�ָpartition�������� 3.  
     * �ݹ�أ�recursive����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������  
     * �ݻص���ײ����Σ������еĴ�С�����һ��Ҳ������Զ���Ѿ����������  
     * ����Ȼһֱ�ݻ���ȥ����������㷨�ܻ��������Ϊ��ÿ�εĵ�����iteration���У������ٻ��һ��Ԫ�ذڵ�������λ��ȥ��  
     *  
     * @since 1.1  
     * @param source  
     *            ��Ҫ�����������������  
     * @return ����������  
     */  
    public static int[] quickSort(int[] source) {   
        return qsort(source, 0, source.length - 1);   
    } 

	/**
	 * ��������ľ���ʵ�֣�������
	 * 
	 * @since 1.1
	 * @param source ��Ҫ�����������������
	 * @param low ��ʼ��λ
	 * @param high ������λ
	 * @return ����������
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
