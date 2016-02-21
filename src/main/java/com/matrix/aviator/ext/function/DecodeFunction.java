package com.matrix.aviator.ext.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.exception.ExpressionRuntimeException;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;

/**
 * decode 函数 与oracle的decode函数定义一样
 * 
 * @author rong yang
 *
 */
public class DecodeFunction extends AbstractFunction {

	@Override
	public String getName() {
		return "decode";

	}

	private AviatorObject execution(Map<String, Object> env, AviatorObject[] args) {
		Number rs = FunctionUtils.getNumberValue(args[0], env);
		for (int i = 1; i < args.length - 1; i += 2) {
			if (rs.intValue() == FunctionUtils.getNumberValue(args[i], env).intValue()) {
				return args[i + 1];
			}
		}

		if (args.length % 2 == 0) {
			return args[args.length - 1];
		} else {
			throw new ExpressionRuntimeException("decode函数没有匹配到合适的值，并且没有默认值！");
		}

	}

	public static void main(String[] args) {
		AviatorEvaluator.addFunction(new DecodeFunction());
		String[] a = new String[]{"20","21","22","23"};
		Map map = new HashMap();
		map.put("a", a);
		Object rs = AviatorEvaluator.execute("decode(1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21)",map);
		System.out.println(rs);
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3 });
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4 });
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5 });
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 });
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16,
			AviatorObject arg17) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16,
			AviatorObject arg17, AviatorObject arg18) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16,
			AviatorObject arg17, AviatorObject arg18, AviatorObject arg19) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19});
	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16,
			AviatorObject arg17, AviatorObject arg18, AviatorObject arg19, AviatorObject arg20) {
		return execution(env, new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20});

	}

	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4,
			AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8, AviatorObject arg9, AviatorObject arg10,
			AviatorObject arg11, AviatorObject arg12, AviatorObject arg13, AviatorObject arg14, AviatorObject arg15, AviatorObject arg16,
			AviatorObject arg17, AviatorObject arg18, AviatorObject arg19, AviatorObject arg20, AviatorObject... args) {
		
		//测试有问题，超过20个参数后有问题
		AviatorObject[] preParams =  new AviatorObject[] { arg1, arg2, arg3,arg4,arg5,arg6 ,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20};

		AviatorObject[] params = new AviatorObject[args.length+20];
		
		System.arraycopy(preParams, 0, params, 0, preParams.length);
		System.arraycopy(args, 0, params, preParams.length, args.length);
		
		System.out.println(Arrays.toString(params));
		return execution(env, params);
	}

}
