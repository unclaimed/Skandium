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

import cl.niclabs.skandium.system.events.BeforeListener;
import cl.niclabs.skandium.system.events.ConditionListener;
import cl.niclabs.skandium.system.events.IntegerParamListener;
import cl.niclabs.skandium.system.events.WhileListener;

/**
 * Abstract class intended to be extended in order to include a {@link cl.niclabs.skandium.system.events.SkandiumEventListener} to the
 * {@link cl.niclabs.skandium.skeletons.While} {@link When#BEFORE} {@link Where#CONDITION} event.
 * 
 * @param <P> <code>param</code> type during {@link cl.niclabs.skandium.skeletons.While} execution
 */
public abstract class WhileBeforeConditionListener<P> extends IntegerParamListener<P> implements WhileListener<P>, BeforeListener, ConditionListener {

}
