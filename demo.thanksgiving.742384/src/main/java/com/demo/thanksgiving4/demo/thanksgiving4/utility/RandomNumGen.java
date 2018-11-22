package com.demo.thanksgiving4.demo.thanksgiving4.utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Service
public class RandomNumGen {

    private static final int MIN_RANGE = 8;
    private static final int MAX_RANGE = 18;

    public static int genRanNum(){
        Random random = new Random();
        return random.nextInt((MAX_RANGE - MIN_RANGE) + 1) + MIN_RANGE;
    }

    public ArrayList<Integer>  create6RanNum(){

        ArrayList<Integer> randomList = new ArrayList<>();
        for (int i =1; i<=7; i++) {
            randomList.add(genRanNum());
        }
        Collections.sort(randomList);
        System.out.println(" the random generated numbers are ::  " + randomList.toString());

        return randomList;
    }
}
