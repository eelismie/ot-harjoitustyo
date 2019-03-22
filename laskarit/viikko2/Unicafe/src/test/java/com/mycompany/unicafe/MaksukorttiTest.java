package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test 
    public void saldoOikein(){
        assertTrue(kortti.saldo()==10);
    }
    
    @Test 
    public void saldonLisays(){
        kortti.lataaRahaa(5);
        assertTrue(kortti.saldo()==15);
    }
    
    @Test
    public void vahennysToimii(){
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo()==5);
        kortti.otaRahaa(10);
        assertTrue(kortti.saldo()==5);
    }
    
    @Test
    public void RahanOttoPalautusArvo(){
        assertTrue(kortti.otaRahaa(5));
        assertTrue(!kortti.otaRahaa(10));
    }
    
    @Test 
    public void toStringToimii(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
