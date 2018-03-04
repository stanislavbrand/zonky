package cz.zonky.demo.model;

import java.io.Serializable;
import java.util.Arrays;

public abstract class AbstractEntity implements Serializable{
	
	protected abstract Object[] getBaseFields();

	@Override
	public boolean equals(Object obj) {
		if( this == obj)
			return true;
		
		if(obj instanceof AbstractEntity){
			AbstractEntity other = (AbstractEntity)obj;
			return obj.getClass().isAssignableFrom(getClass()) && Arrays.deepEquals(other.getBaseFields(), this.getBaseFields());
		}
		
		return false;		
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(getBaseFields());
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+Arrays.toString(getBaseFields());
	}
}
