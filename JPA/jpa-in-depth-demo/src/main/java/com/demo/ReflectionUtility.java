package com.demo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

public class ReflectionUtility {

	public static <T> List<T> createInstances(Class targetClass, List<Tuple> tupleList, List<String> fields)
			throws RuntimeException {

		List<T> instances = new ArrayList<T>();
		try {
			Constructor<T>[] constructors = targetClass.getDeclaredConstructors();

			Constructor<T> defaultConstructor = null;
			for (int i = 0; i < constructors.length; i++) {
				if (constructors[i].getGenericParameterTypes().length == 0) {
					defaultConstructor = constructors[i];
					break;
				}
			}

			Map<String, Method> fieldMethodMap = new HashMap<String, Method>();
			Arrays.asList(targetClass.getDeclaredMethods()).forEach(method -> {
				String methodName = method.getName();
				fields.forEach(field -> {
					if (("set" + field).equalsIgnoreCase(methodName)) {
						fieldMethodMap.put(field, method);
					}
				});
			});

			for (Tuple tuple : tupleList) {
				T newInstance = defaultConstructor.newInstance();
				int i = 0;
				for (String field : fields) {
					fieldMethodMap.get(field).invoke(newInstance, tuple.get(i));
					i++;
				}
				instances.add(newInstance);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return instances;
	}
}
