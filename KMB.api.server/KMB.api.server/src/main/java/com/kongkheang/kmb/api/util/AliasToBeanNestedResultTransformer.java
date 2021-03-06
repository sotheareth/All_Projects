package com.kongkheang.kmb.api.util;

import org.hibernate.HibernateException;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.Getter;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.springframework.util.Assert;

public class AliasToBeanNestedResultTransformer extends AliasedTupleSubsetResultTransformer {
	private static final long serialVersionUID = -9174677139461612125L;
	
	private final Class<?> resultClass;
	
	PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(
			PropertyAccessStrategyBasicImpl.INSTANCE,
			PropertyAccessStrategyFieldImpl.INSTANCE,
			PropertyAccessStrategyMapImpl.INSTANCE
	);

	public AliasToBeanNestedResultTransformer(Class<?> resultClass) {
		Assert.notNull(resultClass, "resultClass cannot be null");
		this.resultClass = resultClass;
	}

	@Override
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		return false;
	}

	@Override
	public Object transformTuple(Object[] tuples, String[] aliases) {
		Object result;

		try {
			result = initialize(tuples, aliases);
		}
		catch ( InstantiationException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}
		catch ( IllegalAccessException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}

		return result;
	}
	
	
	private Object initialize(Object[] tuples, String[] aliases) throws InstantiationException, IllegalAccessException {
		Object result = resultClass.newInstance();
		
		for ( int i = 0; i < aliases.length; i++ ) {
			String alias = aliases[ i ];
			if ( alias != null ) {
				String[] split = alias.split("\\.");
				if(split.length == 1) {
					Setter setter = propertyAccessStrategy.buildPropertyAccess( resultClass, alias ).getSetter();
					setter.set( result, tuples[i], null );
				}
				else if(split.length > 1) {
					String nestedPropertyName = split[0];
					Setter setter = propertyAccessStrategy.buildPropertyAccess( resultClass, nestedPropertyName ).getSetter();
					Getter getter = propertyAccessStrategy.buildPropertyAccess( resultClass, nestedPropertyName ).getGetter();
					Class<?> nestedEntityClass = getter.getReturnType();
					
					Object nestedInstance = getter.get(result);
					if(nestedInstance == null) {
						nestedInstance = nestedEntityClass.newInstance();
						setter.set(result, nestedInstance, null);
					}
					
					nestedPropertyName = split[1];
					setter = propertyAccessStrategy.buildPropertyAccess( nestedEntityClass, nestedPropertyName ).getSetter();
					setter.set(nestedInstance, tuples[i], null);
				}
			}
		}
		
		return result;
	}
}