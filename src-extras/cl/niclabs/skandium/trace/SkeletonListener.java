/*   Skandium: A Java(TM) based parallel skeleton library.
 *   
 *   Copyright (C) 2009 NIC Labs, Universidad de Chile.
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

package cl.niclabs.skandium.trace;

import cl.niclabs.skandium.events.GenericListener;
import cl.niclabs.skandium.events.When;
import cl.niclabs.skandium.events.Where;
import cl.niclabs.skandium.skeletons.Skeleton;

class SkeletonListener extends GenericListener {

	private EventHandler handler;
	
	public SkeletonListener(EventHandler handler) {
		super();
		this.handler = handler;
	}


	@Override
	public Object handler(Object param, @SuppressWarnings("rawtypes") Skeleton[] strace, int index,
			boolean cond, int parent, When when, Where where) {
		return handler.handler(param, strace, when, where, index, cond);
	}

}
