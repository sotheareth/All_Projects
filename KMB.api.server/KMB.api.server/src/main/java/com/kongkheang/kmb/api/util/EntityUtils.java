package com.kongkheang.kmb.api.util;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity utility to copy bean's properties
 * 
 * @author Mr.SAY SEAK LENG
 *
 */
public abstract class EntityUtils {
	private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);
	
	private static DozerBeanMapper mapper = new DozerBeanMapper();
	
	static {
		mapper.setCustomFieldMapper(new LazyLoadSensitiveMapper());
	}
	
	/**
	 * LazyLoadSensitiveMapper
	 */
	private static class LazyLoadSensitiveMapper implements CustomFieldMapper {
		
		public boolean mapField(Object source, Object destination, Object sourceFieldValue, 
				ClassMap classMap, FieldMap fieldMapping) {
			
		    //if field is initialized, Dozer will continue mapping

			if( sourceFieldValue != null) {
				// Check if field is derived from Persistent Collection
			    if (!(sourceFieldValue instanceof AbstractPersistentCollection)) {
			        // Allow dozer to map as normal
			        return false;
			    }

			    // Check if field is already initialized
			    if (((AbstractPersistentCollection) sourceFieldValue).wasInitialized()) {
			        // Allow dozer to map as normal
			        return false;
			    }
			}

		    return true;
		}
	}
	
	/**
	 * Copy values of Not-Null Properties from source to target
	 * <br/>
	 * It will exclude copying NULL and proxy (lazy-loading) properties of hibernate entity
	 * 
	 * @param source
	 * @param target
	 */
	public static void copyNotNullProperties(Object source, Object destination) {
	    mapper.map(source, destination);
	}
	
	
	/**
	 * Initialize all Lazy-loading properties of an array of proxy objects
	 * @param proxyList
	 */
	public static <T> void initializeLazyPropertiesInArray(List<T> proxyList) {
		for (Object proxy : proxyList) {
			initializeLazyProperties(proxy);
		}
	}
	
	
	/**
	 * Initialize all Lazy-loading properties of a proxy object
	 * @param proxy
	 */
	public static void initializeLazyProperties(Object proxy) {
		try {
			
			Class<? extends Object> entityClass = proxy.getClass();
			Field[] declaredFields = entityClass.getDeclaredFields();
			
			for (Field field : declaredFields) {
				OneToMany oneToManyAnnotation = field.getAnnotation(OneToMany.class);
				
				if(oneToManyAnnotation != null) {
					if( oneToManyAnnotation.fetch() != FetchType.EAGER ) {
						String fieldName = field.getName();
						initializeLazyProperty(proxy, fieldName);
					}
					
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Initialize a Lazy-loading property of a proxy object
	 * 
	 * @param proxy
	 * @param property
	 */
	public static void initializeLazyProperty(Object proxy, String property) {
		try {
			String getter = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
			Hibernate.initialize(MethodUtils.invokeExactMethod(proxy, getter));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Initialize a lazy-loading property
	 * @param property
	 */
	public static void initializeLazyProperty(Object property) {
		Hibernate.initialize(property);
	}
}
