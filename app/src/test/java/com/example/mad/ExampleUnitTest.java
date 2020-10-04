package com.example.mad;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
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