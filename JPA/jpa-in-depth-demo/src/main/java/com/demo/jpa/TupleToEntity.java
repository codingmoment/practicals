package com.demo.jpa;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

public class TupleToEntity {
	public static <T> List<T> transformTupleListIntoEntityList(Class targetClass, List<Tuple> tupleList,
			List<String> fields) {
		List<T> instances = new ArrayList<T>();
		try {
			Constructor<T> defaultConstructor = targetClass.getConstructor();

			Map<String, Method> propertySetterMethodMap = new HashMap<String, Method>();

			fields.forEach(field -> {
				try {
					propertySetterMethodMap.put(field, new PropertyDescriptor(field, targetClass).getWriteMethod());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});

			for (Tuple tuple : tupleList) {
				T newInstance = defaultConstructor.newInstance();
				for (String field : fields) {
					propertySetterMethodMap.get(field).invoke(newInstance, tuple.get(field));
				}
				instances.add(newInstance);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return instances;
	}
}
