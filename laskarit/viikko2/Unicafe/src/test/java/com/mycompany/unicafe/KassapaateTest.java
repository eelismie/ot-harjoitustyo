/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eelismie
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void kassaAlustusOk(){
        assertTrue(kassa!=null);
        assertTrue(kassa.kassassaRahaa()==100000);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void syoEdullisestiOk(){
        assertTrue(60==kassa.syoEdullisesti(300));
        assertTrue(kassa.kassassaRahaa()==100240);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
        assertTrue(200==kassa.syoEdullisesti(200));  
        assertTrue(kassa.kassassaRahaa()==100240);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }
    
    @Test
    public void syoMaukkaastiOk(){
        assertTrue(20==kassa.syoMaukkaasti(420));
        assertTrue(kassa.kassassaRahaa()==100400);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
        assertTrue(200==kassa.syoMaukkaasti(200));
        assertTrue(kassa.kassassaRahaa()==100400);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test
    public void syoEdullisestiKortilla(){
        Maksukortti kortti = new Maksukortti(300);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==60);
        assertTrue(!kassa.syoEdullisesti(kortti));
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==60);
        assertTrue(kassa.kassassaRahaa()==100000);
    }
    
    @Test
    public void syoMaukkaastiKortilla(){
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==100);
        assertTrue(!kassa.syoMaukkaasti(kortti));
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==100);
        assertTrue(kassa.kassassaRahaa()==100000);
    }
    
    @Test
    public void kortinLatausOk(){
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 1);
        assertTrue(kassa.kassassaRahaa()==100001);
        assertTrue(kortti.saldo()==101);
        kassa.lataaRahaaKortille(kortti, -20);
        assertTrue(kassa.kassassaRahaa()==100001);
        assertTrue(kortti.saldo()==101);
    }
    
}
