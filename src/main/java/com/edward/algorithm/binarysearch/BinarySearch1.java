/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BinarySearch1
 * CreationDate: 2020/4/3
 * Author edward
 */
package com.edward.algorithm.binarysearch;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BinarySearch1 {

    public static int search(int[] nums, int value) {
        return searchByRecursive(nums, 0, nums.length - 1, value);

    }

    private static int searchByRecursive(int[] nums, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int middle = left + (right - left) / 2;
        if (nums[middle] == value) {
            return middle;
        } else if (value > nums[middle]) {
            return searchByRecursive(nums, middle + 1, right, value);
        } else {
            return searchByRecursive(nums, left, middle - 1, value);
        }
    }

    private static int searchByNoRecursive(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == value) {
                return middle;
            } else if (value > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] data = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = searchByNoRecursive(data, 9);
        System.out.println(index);
    }
}
