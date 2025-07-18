package by.antohakon.testproject.test;

import by.antohakon.testproject.entity.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Ktest {

    public static void main(String[] args) {

        int[] oldArray = {1,2,3,4};
        int[] newArray = reverse(oldArray);
        System.out.println(Arrays.toString(newArray));

    }

    public static int[] reverse(int[] array) {

        int left = 0, right = array.length - 1;

        while (left < right) {

            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
        return array;
    }

    public static char[] reverse(char[] array) {

        int left = 0, right = array.length - 1;

        while (left < right) {

            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
        return array;
    }

}

