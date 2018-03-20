package com.thoughtworks.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by renwujie on 2018/03/19 at 17:18
 */
public class HomeworkTW {
    static int illegalNum = Integer.MAX_VALUE;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("文本内容:");
        int i = 0;
        String input = "";
        while (!(input = scanner.nextLine()).equals("")) {
            list.add(input);

            if (i == 0) {
                if (!satisfyLawFirst(input)) {
                    illegalNum = 0;
                }
            } else {
                if (!satisfyLawSecond(input, i)) {
                    illegalNum = illegalNum == 0 ? illegalNum : (illegalNum < i ? illegalNum : i);
                }
            }
            i++;
        }

        System.out.println("指定消息ID:");
        while(scanner.hasNext()) {
            input = scanner.nextLine();
            if("".equals(input) || !isLegitimateLocation(input)) {
                System.out.println("输入不合法! 请输入数字!");
                continue;
            }
            int inputNum = string2Int(input);

            if(inputNum < illegalNum) {
                String[] info = list.get(inputNum).split(" ");

                if(inputNum == 0) {
                    System.out.println(info[0] + " " + input + " " + string2Int(info[1]) + " " + string2Int(info[2]) + " " + string2Int(info[3]));
                } else {
                    System.out.println(info[0] + " " + input + " " + (string2Int(info[1]) + string2Int(info[4])) + " " + (string2Int(info[2]) + string2Int(info[5])) + " " + (string2Int(info[3]) + string2Int(info[6])));
                }
            }

            if(inputNum >= illegalNum && inputNum <= list.size()-1) {
                System.out.println("Error: " + input);
            }

            if(inputNum > list.size()-1) {
                System.out.println("Cannot find " + input);
            }
        }
    }

    public static boolean satisfyLawSecond(String input, int i) {
        String[] strs = input.split(" ");

        if (strs.length != 7) {
            return false;
        }

        if(!isDigitOrLetter(strs)) {
            return false;
        }

        if(i == 1) {
            String[] location = list.get(0).split(" ");

            if(!satisfyLawFirst(list.get(0))) {
                return false;
            }

            if(!strs[1].equals(location[1])
                    || !strs[2].equals(location[2])
                    || !strs[3].equals(location[3])) {
                return false;
            }
        } else {
            if (!isLegitimateLocation(strs[4])
                    || !isLegitimateLocation(strs[5])
                    || !isLegitimateLocation(strs[6])) {
                return false;
            }

            String[] location = list.get(i - 1).split(" ");
            if(location.length != 7) {
                return false;
            }
            if (string2Int(strs[1]) != string2Int(location[1]) + string2Int(location[4])
                    || string2Int(strs[2]) != string2Int(location[2]) + string2Int(location[5])
                    || string2Int(strs[3]) != string2Int(location[3]) + string2Int(location[6])) {
                return false;
            }
        }

        return true;
    }

    public static boolean satisfyLawFirst(String input) {
        String[] firstLine = input.split(" ");
        if (firstLine.length != 4) {
            return false;
        }

        if(!isDigitOrLetter(firstLine)) {
            return false;
        }

        return true;
    }

    public static boolean isDigitOrLetter(String[] strs) {
        for (Character letter : strs[0].toCharArray()) {
            if (!Character.isLowerCase(letter)
                    && !Character.isUpperCase(letter)
                    && !Character.isDigit(letter)) {
                return false;
            }
        }

        for (int x = 1; x < strs.length; x++) {
            if (!isLegitimateLocation(strs[x])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isLegitimateLocation(String loc) {
        for (Character num : loc.toCharArray()) {
            if (!Character.isDigit(num)) {
                return false;
            }
        }

        return true;
    }

    public static int string2Int(String str) {
        return Integer.parseInt(str);
    }
}
