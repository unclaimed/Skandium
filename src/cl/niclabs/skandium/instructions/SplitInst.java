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
package cl.niclabs.skandium.instructions;

import java.util.List;
import java.util.Stack;

import cl.niclabs.skandium.events.When;
import cl.niclabs.skandium.events.Where;
import cl.niclabs.skandium.muscles.Merge;
import cl.niclabs.skandium.skeletons.Skeleton;


/**
 * 
 * @author gpabon
 */

public class SplitInst extends AbstractInstruction {
	
	boolean cond;
	List<Stack<Instruction>> substacks;
	@SuppressWarnings("rawtypes")
	Merge merge;
	Stack<Integer> rbranch;

	/**
	 * The main constructor.
	 */
	@SuppressWarnings("rawtypes")
	public SplitInst(List<Stack<Instruction>> substacks, Merge merge, Skeleton<?,?>[] strace){
		super(strace);
		this.substacks = substacks;
		this.merge = merge;
	}
	
	/**
	 * The constructor when reducing DaCInst.
	 */
	@SuppressWarnings("rawtypes")
	public SplitInst(List<Stack<Instruction>> substacks, Merge merge, Skeleton<?,?>[] strace, Stack<Integer> rbranch){
		this(substacks,merge,strace);
		this.rbranch = rbranch;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P> Object interpret(P param, Stack<Instruction> stack, 
			List<Stack<Instruction>> children) throws Exception {
		Object[] params = (Object[])param;
		int subsize = substacks.size();
		if((subsize != 1) && params.length != subsize){
			throw new Exception("Invalid number of divisions. Expected "+ substacks.size() +" but was "+params.length+".");
		}
		
		// For each stack copy all of its elements
		for(int i=0; i < params.length; i++){
			Stack<Instruction> subStack = copyStack(subsize == 1? this.substacks.get(0) : this.substacks.get(i));
			// DaC rbranch calculation
			if (rbranch != null) {
				Stack<Integer> subrbranch = new Stack<Integer>();
				subrbranch.addAll(rbranch);
				subrbranch.push(i);
				((DaCInst)subStack.peek()).rbranch = subrbranch;
				subStack.push(new EventInst(When.BEFORE, Where.CONDITION, strace, subrbranch, cond));				
			} else {
				subStack.add(0,new EventInst(When.AFTER, Where.NESTED_SKELETON, strace, i));
				subStack.push(new EventInst(When.BEFORE, Where.NESTED_SKELETON, strace, i));
			}
			children.add(subStack);
		}
		stack.push(new EventInst(When.AFTER, Where.MERGE, strace, rbranch));
		stack.push(new MergeInst(merge, strace));
		stack.push(new EventInst(When.BEFORE, Where.MERGE, strace, rbranch));
	
		return params;
	}

	@Override
	public Instruction copy() {
		return new SplitInst(substacks, merge, strace);
	}

}