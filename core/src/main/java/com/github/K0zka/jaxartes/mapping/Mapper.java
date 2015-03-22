package com.github.K0zka.jaxartes.mapping;

/**
 * Mapper interface.
 * @param <T>
 */
public interface Mapper<T> {
	/**
	 * Converts the source string to a java object of type {@link T}
	 * @param source
	 * @return
	 */
    T map(String source);
}
