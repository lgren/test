package jyyh;

import java.lang.reflect.Method;

/** 
 * base64工具类
 * @author: 银海人事人才项目组(XXX)
 * @Copyright: 2013-2014 银海软件所有
 * @date: 2015-12-13 下午09:30:31
 * @vesion: 1.0
 */
public class Base64Util {
	/***
	 * encode by Base64
	 */
	public static String encodeBase64(byte[]input) throws Exception{
		Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod= clazz.getMethod("encode", byte[].class);
		mainMethod.setAccessible(true);
		 Object retObj=mainMethod.invoke(null, new Object[]{input});
		 return (String)retObj;
	}
	/***
	 * decode by Base64
	 */
	public static byte[] decodeBase64(String input) throws Exception{
		Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod= clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		 Object retObj=mainMethod.invoke(null, input);
		 return (byte[])retObj;
	}

}
