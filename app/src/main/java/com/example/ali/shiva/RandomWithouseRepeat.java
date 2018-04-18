package com.example.ali.shiva;

import java.util.Random;



public class RandomWithouseRepeat {
    public static int[] getarray(int tehdad){

        int[] number = new int[tehdad];
        int count=0;
        int num;
        Random r = new Random();
        while(count<number.length){
            num = r.nextInt(tehdad+1);
            boolean repeat=false;
            do{
                for(int i=0; i<number.length; i++){
                    if(num==number[i]){
                        repeat=true;
                        break;
                    }
                    else if(i==count){
                        number[count]=num;
                        count++;
                        repeat=true;
                        break;
                    }
                }
            }while(!repeat);

        }
        return number;

    }


}
