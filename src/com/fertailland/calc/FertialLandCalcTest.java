package com.fertailland.calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FertialLandCalcTest {

	
	   @Test
	    public void testFindFertileLand() {
	        String[] strSTDIN = {"0 292 399 307"};
	        String STDOUT = "116800 116800 ";
	        
	        
	        assertEquals(STDOUT, FertialLandCalc.findFertileLand(strSTDIN));
	  
	    }
	    
	    @Test
	    public void testFindFertileLand2() {
	        String[] strSTDIN = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
	        String STDOUT = "22816 192608 ";
	        
	        
	        assertEquals(STDOUT, FertialLandCalc.findFertileLand(strSTDIN));
	  
	    }
	    
	  

}
