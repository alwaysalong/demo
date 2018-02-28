package com.sh.pri.sort;

/**
 * 折半查找(要求数据必须是有序的)
 * 首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，如果两者相等，则查找成功；
 * 否则利用中间位置记录将表分成前、后两个子表，如果中间位置记录的关键字大于查找关键字，则进一步查找前一子表，
 * 否则进一步查找后一子表。重复以上过程，直到找到满足条件的记录，使查找成功，或直到子表不存在为止，此时查找不成功。
 * Created by admin on 2018/2/28.
 */
public class Bisearch {

    public static int getIndex(int[] a, int key){
        int start = 0;//最小数索引
        int mid = 0;//中间数索引
        int last = a.length-1;//最大数索引
        while(last>=start){
            if (key>a[(start+last)/2]) {
                start = (start+last)/2+1;
            }else if (key<a[(start+last)/2]) {
                last = (start+last)/2 - 1;
            }else if (key == a[(start+last)/2]) {
                return (start+last)/2;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        int[] a = {1, 3, 5, 22, 45, 55, 66};
        int key = 66;
        int index = getIndex(a, key);
        System.out.println(index);
    }

}
