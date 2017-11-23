package org.cgiar.ciat.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class TestReflection {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//clase a instanciar
		String sClass = "java.awt.Frame";

		//Constructor a usar
		Class cArgs0[] = {String.class};
		Object oParams0[] = {new String("Demo Reflection")};
		
		//metodo 1
		String sMetodo1="setSize";
		Class cArgs1[] = {Integer.TYPE,Integer.TYPE};
		Object oParams1[] = {new Integer(300),new Integer(300)};
		
		//metodo 2
		String sMetodo2 = "setVisible";
		Class cArgs2[] = {Boolean.TYPE};
		Object oParams2[] = {new Boolean(true)};
		
		//instancia usando el contructor que necesitamos
		Class clazz = Class.forName(sClass);
		Constructor ctor = clazz.getConstructor(cArgs0);
		Object obj = ctor.newInstance(oParams0);
		
		//invocamos metodo 1
		Method mtd1 = clazz.getMethod(sMetodo1, cArgs1);
		mtd1.invoke(obj, oParams1);
		
		//invocamos metodo 2
		Method mtd2 = clazz.getMethod(sMetodo2, cArgs2);
		mtd2.invoke(obj, oParams2);
		
	}

}
