package ca.mcgill.sable.soot.coffi;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Jimple, a 3-address code Java(TM) bytecode representation.        *
 * Copyright (C) 1997, 1998 Raja Vallee-Rai (kor@sable.mcgill.ca)    *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a project of the Sable Research Group,      *
 * School of Computer Science, McGill University, Canada             *
 * (http://www.sable.mcgill.ca/).  It is understood that any         *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * Java is a trademark of Sun Microsystems, Inc.                     *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other Sable Research Group projects, please      *
 * visit the web site: http://www.sable.mcgill.ca/                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Coffi, a bytecode parser for the Java(TM) language.               *
 * Copyright (C) 1996, 1997 Clark Verbrugge (clump@sable.mcgill.ca). *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a project of the Sable Research Group,      *
 * School of Computer Science, McGill University, Canada             *
 * (http://www.sable.mcgill.ca/).  It is understood that any         *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * Java is a trademark of Sun Microsystems, Inc.                     *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other Sable Research Group projects, please      *
 * visit the web site: http://www.sable.mcgill.ca/                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*
 Reference Version
 -----------------
 This is the latest official version on which this file is based.
 The reference version is: $CoffiVersion: 1.1 $
						   $SootVersion: 1.beta.4 $

 Change History
 --------------
 A) Notes:

 Please use the following template.  Most recent changes should
 appear at the top of the list.

 - Modified on [date (March 1, 1900)] by [name]. [(*) if appropriate]
   [description of modification].

 Any Modification flagged with "(*)" was done as a project of the
 Sable Research Group, School of Computer Science,
 McGill University, Canada (http://www.sable.mcgill.ca/).

 You should add your copyright, using the following template, at
 the top of this file, along with other copyrights.

 *                                                                   *
 * Modifications by [name] are                                       *
 * Copyright (C) [year(s)] [your name (or company)].  All rights     *
 * reserved.                                                         *
 *                                                                   *

 B) Changes:

 - Modified on November 2, 1998 by Raja Vallee-Rai (kor@sable.mcgill.ca) (*)
   Repackaged all source files and performed extensive modifications.
   First initial release of Soot.

 - Modified on 15-Jun-1998 by Raja Vallee-Rai (kor@sable.mcgill.ca). (*)
   First internal release (Version 0.1).
*/

import java.io.*;
/** Instruction subclasses are used to represent parsed bytecode; each
 * bytecode operation has a corresponding subclass of Instruction.
 * <p>
 * Each subclass is derived from one of
 * <ul><li>Instruction</li>
 * <li>Instruction_noargs (an Instruction with no embedded arguments)</li>
 * <li>Instruction_byte (an Instruction with a single byte data argument)</li>
 * <li>Instruction_bytevar (a byte argument specifying a local variable)</li>
 * <li>Instruction_byteindex (a byte argument specifying a constant pool index)</li>
 * <li>Instruction_int (an Instruction with a single short data argument)</li>
 * <li>Instruction_intvar (a short argument specifying a local variable)</li>
 * <li>Instruction_intindex (a short argument specifying a constant pool index)</li>
 * <li>Instruction_intbranch (a short argument specifying a code offset)</li>
 * <li>Instruction_longbranch (an int argument specifying a code offset)</li>
 * </ul>
 * @author Clark Verbrugge
 * @see Instruction
 * @see Instruction_noargs
 * @see Instruction_byte
 * @see Instruction_bytevar
 * @see Instruction_byteindex
 * @see Instruction_int
 * @see Instruction_intvar
 * @see Instruction_intindex
 * @see Instruction_intbranch
 * @see Instruction_longbranch
 * @see Instruction_Unknown
 */
class Instruction_Lookupswitch extends Instruction {
   public byte pad;  // number of bytes used for padding
   public int default_offset;
   public int npairs;
   public int match_offsets[];
   public Instruction default_inst;
   public Instruction match_insts[];
   public Instruction_Lookupswitch() { super((byte)ByteCode.LOOKUPSWITCH); name = "lookupswitch"; branches = true; }   
   public Instruction[] branchpoints(Instruction next) {
	  Instruction i[] = new Instruction[npairs+1];
	  int j;
	  i[0] = default_inst;
	  for (j=1;j<npairs+1;j++)
		 i[j] = match_insts[j-1];
	  return i;
   }   
   public int compile(byte bc[],int index) {
	  int i;
	  bc[index++] = code;
	  // insert padding so next instruction is on a 4-byte boundary
	  for (i=0;i<pad;i++)
		 bc[index++] = 0;
	  if (default_inst!=null)
		 index = intToBytes(default_inst.label-label,bc,index);
	  else
		 index = intToBytes(default_offset,bc,index);
	  index = intToBytes(npairs,bc,index);
	  for (i=0;i<npairs;i++) {
		 index = intToBytes(match_offsets[i*2],bc,index);
		 if (match_insts[i]!=null)
			index = intToBytes((match_insts[i]).label-label,bc,index);
		 else
			index = intToBytes(match_offsets[i*2+1],bc,index);
	  }
	  return index;
   }   
   public int nextOffset(int curr) {
	  int i,j,baseindex,siz=0;
	  baseindex = curr;
	  i = (curr+1) % 4;
	  if (i != 0)
		 siz = (4 - i);
	  return (curr + siz + 9 + npairs*8);
   }   
   public void offsetToPointer(ByteCode bc) {
	  int i;
	  default_inst = bc.locateInst(default_offset+label);
	  if (default_inst==null) {
		 System.out.println("Warning: can't locate target of instruction");
		 System.out.println(" which should be at byte address " + (label+default_offset));
	  } else
		 default_inst.labelled = true;
	  if (npairs>0) {
		 match_insts = new Instruction[npairs];
		 for (i=0;i<npairs;i++) {
			match_insts[i] = bc.locateInst(match_offsets[i*2+1]+label);
			if (match_insts[i]==null) {
			   System.out.println("Warning: can't locate target of instruction");
			   System.out.println(" which should be at byte address " +
								  (label+match_offsets[i*2+1]));
			} else
			   match_insts[i].labelled = true;
		 }
	  }
   }   
   public int parse(byte bc[],int index) {
	  // first figure out padding to next 4-byte quantity
	  int i,j,baseindex;
	  baseindex = index;
	  i = index % 4;
	  if (i != 0)
		 pad = (byte)(4 - i);
	  else
		 pad = (byte)0;
	  index += pad;
	  default_offset = getInt(bc,index);
	  index += 4;
	  npairs = getInt(bc,index);
	  index += 4;
	  if (npairs>0) {
		 match_offsets = new int[npairs*2];
		 j = 0;
		 do {
			match_offsets[j] = getInt(bc,index);
			j++;
			index += 4;
			match_offsets[j] = getInt(bc,index);
			index += 4;
			j++;
		 } while(j<npairs*2);
	  }
	  return index;
   }   
   public String toString(cp_info constant_pool[]) {
	  // first figure out padding to next 4-byte quantity
	  String args;
	  int i;
	  args = super.toString(constant_pool) + argsep + "(" +
		 Integer.toString(pad) + ")";
	  args = args + argsep + Integer.toString(default_inst.label);
	  args = args + argsep + Integer.toString(npairs) + ": ";
	  for (i=0;i<npairs;i++)
		 args = args + "case " + Integer.toString(match_offsets[i*2]) +
			": label_" + Integer.toString(match_insts[i].label);
	  return args;
   }   
}
