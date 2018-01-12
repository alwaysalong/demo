package com.sh.pri.recursion;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归查出路径下的所有文件,并取出最大的文件
 * Created by admin on 2018/1/12.
 */
public class RecursionFile {
    //改路径文件夹下所有的文件的绝对路径的集合
    private static List<String> fileList = new ArrayList<String>();
    public static void main(String[] args) {
        String path = "D:\\工具";
        getAllFile(path);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        if ((null == fileList) || 0 == fileList.size()){
            return;
        }
        String bigestFile = getBigestFile(fileList);
        System.out.println("最大文件:" + bigestFile + ",大小:" + new File(bigestFile).length()/1024 +"kb");
    }

    /**
     * 获取所有文件
     * @param path
     */
    private  static  void getAllFile(String path){
        File files = new File(path);
        //判断该路径下的文件是否存在
        if (files.exists()){
            //获取该文件夹下的目录
            File[] files1 = files.listFiles();
            if (null == files1 || 0 == files1.length){
                System.out.println("该路径文件夹是空的！");
                return;
            }
            //遍历改文件下的目录得到所有的文件和文件夹
            for (File file2 : files1){
                //判断是否是文件夹
                if (file2.isDirectory()){
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    //如果是文件夹,继续递归该文件夹查出所有的文件
                    getAllFile(file2.getAbsolutePath());
                }else {
                    System.out.println("文件:" + file2.getName() + ",文件大小:" + file2.length()/1024 + "k");
                    fileList.add(file2.getAbsolutePath());
                }
            }
        }else {
            System.out.println("该路径下文件夹不存在!");
        }
    }

    /**
     * 获取最大的文件
     * @param fileList
     * @return
     */
    private static String getBigestFile(List<String> fileList){
        //用来存放每个文件的大小
        List<Long> longs = new ArrayList<>();
        for(int a = 0 ; a < fileList.size() ; a++){
            //获取对应绝对路径的文件的对象发和大小
            File file = new File(fileList.get(a));
            long size = file.length() / 1024;
            //把文件的大小 按照和文件路径相同的索引存放
            longs.add(a,size);
        }
        //list转数组
        Object[] objects1 = longs.toArray();
        //array.util中带的排序算法  升序排列
        Arrays.sort(objects1);
        //根据最大的值  找出list中的索引(也是存放文件路径的集合中的索引)
        int i = longs.indexOf(objects1[objects1.length - 1]);
        return fileList.get(i);
    }

}
