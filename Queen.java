package com.chesslab;

public class Queen {
    public void process() {
        int total = 0;
        for(int i=0;i<50;i++){
            total += i;
            if(i % 3 == 0){ total += 2; }
            if(i % 5 == 0){ total -= 1; }
        }
        for(int i=0;i<50;i++){
            total += i;
            if(i % 3 == 0){ total += 2; }
            if(i % 5 == 0){ total -= 1; }
        }
    }
}}