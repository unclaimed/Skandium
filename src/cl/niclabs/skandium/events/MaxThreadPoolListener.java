/*   Skandium: A Java(TM) based parallel skeleton library. 
 *   
 *   Copyright (C) 2011 NIC Labs, Universidad de Chile.
 * 
 *   Skandium is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Skandium is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.

 *   You should have received a copy of the GNU General Public License
 *   along with Skandium.  If not, see <http://www.gnu.org/licenses/>.
 */
package cl.niclabs.skandium.events;

import cl.niclabs.skandium.system.events.SkandiumEventListener;

/**
 * Abstract class intended to be extended in order to include a Listener to
 * get the MaxThreadPool parameter defined to the current {@link Skandium} instance
 */
public abstract class MaxThreadPoolListener implements SkandiumEventListener {

	/**
	 * Default implementation of compareTo method inherited from Comparable interface
	 * of {@link SkandiumEventListener#compareTo(SkandiumEventListener)} where the {@link Integer.MAX_VALUE} is returned.
	 * {@inheritDoc}
	 * 
	 * @param o {@link SkandiumEventListener} to be compared to 
	 */
	@Override
	public int compareTo(SkandiumEventListener o) {
		return Integer.MAX_VALUE;
	}

	/**
	 * When the event related to this Listener is fired, for each Listener registered,
	 * it executes listener's guard, and if true is returned, then calls the handler.
	 * 
	 * This is the default implementation when true is returned.
	 * @param maxThreadPoolSize the guard is called sending the maxThreadPoolSize as parameter
	 */
	public boolean guard(int maxThreadPoolSize) {
		return true;
	}

	/**
	 * When the event related to this Lostener is fired and the guard returned true, the handler
	 * is called.
	 * 
	 * @param maxThreadPoolSize the handler is called sending the maxThreadPoolSize as parameter
	 */
	public abstract void handler(int maxThreadPoolSize);

}
