package com.example.fitzky2;

import com.example.fitzky2.paymnet.CurencyConv;

import org.junit.Test;

import static org.junit.Assert.*;

//IT19001326
public class ExampleUnitTest {

    private CurencyConv curencyConv;

    public void setup(){
        curencyConv=new CurencyConv();
    }

    @Test
    public void USI_isCorrect() {
        float result=curencyConv.convertToUSI(180);
        assertEquals(1,result,0);
    }



    @Test
    public void Srilanka_isCorrect() {
        float result=curencyConv.convertToSriLanka(180);
        assertEquals(32400,result,0);
    }

}