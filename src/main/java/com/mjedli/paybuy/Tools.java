/**
 * 
 */
package com.mjedli.paybuy;

import java.util.regex.Pattern;

/**
 * @author mjedli
 *
 */
public class Tools {

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	
    public String toLikeRegex(String source) {
        return source.replaceAll("\\*", ".*");
    }
	
}
