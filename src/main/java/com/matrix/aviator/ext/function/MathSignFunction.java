package com.matrix.aviator.ext.function;



import java.util.Map;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;


/**
 * sign(d) function
 * 
 * @author rong yang
 * 
 */
public class MathSignFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        Number num = FunctionUtils.getNumberValue(arg1, env);
        if(num.doubleValue() > 0){
        	return AviatorLong.valueOf(1);
        }else if(num.doubleValue() < 0){
        	return AviatorLong.valueOf(-1);
        }else{
        	return AviatorLong.valueOf(0);
        }

    }


    public String getName() {
        return "sign";
    }

}

