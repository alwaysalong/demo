package com.sh.pri.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。  
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
 *
 */
public class Sort {
	public static void main(String[] args) {
		int [] a = {54,23,22,87,12,27,5,1};
//java自带的排序方法 
//		Arrays.sort(a);
		//外层的每次循环都会把本次最大的树放到循环的最后一个位置(每次循环都会少比较一次,因为每次循环都可以确定出本次循环中最大的一个数)
		for (int i = 0; i < a.length-1; i++) {
			//内层控制两两比较  (j < a.length-1-i是因为每次大循环一次都会排出一个值) 
			for (int j = 0; j < a.length-1-i; j++) {
				//如果前面的大于后一个   就交换两个的位置   把大的一个数放到后面   以此类推   最后大的都在后面
				if (a[j] > a[j+1]) {
					a[j] = a[j] + a[j+1];
					a[j+1] = a[j] -a[j+1];
					a[j] = a[j] - a[j+1];
				}
			}
		}
		//1  5  12  22  23  27  54  
		for (int x = 0; x < a.length-1; x++) {
			System.out.print(a[x]+"  ");
		}
	}
}
