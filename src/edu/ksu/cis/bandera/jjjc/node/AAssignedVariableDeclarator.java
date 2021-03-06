package edu.ksu.cis.bandera.jjjc.node;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Bandera, a Java(TM) analysis and transformation toolkit           *
 * Copyright (C) 1998-2001 SAnToS Laboratories (santos@cis.ksu.edu)  *

 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a project in the SAnToS Laboratory,         *
 * Department of Computing and Information Sciences, Kansas State    *
 * University, USA (http://www.cis.ksu.edu/santos).                  *
 * It is understood that any modification not identified as such is  *
 * not covered by the preceding statement.                           *
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
 * License along with this toolkit; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * Java is a trademark of Sun Microsystems, Inc.                     *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other SAnToS projects, please visit the web-site *
 *                http://www.cis.ksu.edu/santos                      *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import ca.mcgill.sable.util.*;
import edu.ksu.cis.bandera.jjjc.analysis.*;

public final class AAssignedVariableDeclarator extends PVariableDeclarator
{
	private PVariableDeclaratorId _variableDeclaratorId_;
	private TAssign _assign_;
	private PVariableInitializer _variableInitializer_;

	public AAssignedVariableDeclarator()
	{
	}
	public AAssignedVariableDeclarator(
		PVariableDeclaratorId _variableDeclaratorId_,
		TAssign _assign_,
		PVariableInitializer _variableInitializer_)
	{
		setVariableDeclaratorId(_variableDeclaratorId_);

		setAssign(_assign_);

		setVariableInitializer(_variableInitializer_);

	}
	public void apply(Switch sw)
	{
		((Analysis) sw).caseAAssignedVariableDeclarator(this);
	}
	public Object clone()
	{
		return new AAssignedVariableDeclarator(
			(PVariableDeclaratorId) cloneNode(_variableDeclaratorId_),
			(TAssign) cloneNode(_assign_),
			(PVariableInitializer) cloneNode(_variableInitializer_));
	}
	public TAssign getAssign()
	{
		return _assign_;
	}
	public PVariableDeclaratorId getVariableDeclaratorId()
	{
		return _variableDeclaratorId_;
	}
	public PVariableInitializer getVariableInitializer()
	{
		return _variableInitializer_;
	}
	void removeChild(Node child)
	{
		if(_variableDeclaratorId_ == child)
		{
			_variableDeclaratorId_ = null;
			return;
		}

		if(_assign_ == child)
		{
			_assign_ = null;
			return;
		}

		if(_variableInitializer_ == child)
		{
			_variableInitializer_ = null;
			return;
		}

	}
	void replaceChild(Node oldChild, Node newChild)
	{
		if(_variableDeclaratorId_ == oldChild)
		{
			setVariableDeclaratorId((PVariableDeclaratorId) newChild);
			return;
		}

		if(_assign_ == oldChild)
		{
			setAssign((TAssign) newChild);
			return;
		}

		if(_variableInitializer_ == oldChild)
		{
			setVariableInitializer((PVariableInitializer) newChild);
			return;
		}

	}
	public void setAssign(TAssign node)
	{
		if(_assign_ != null)
		{
			_assign_.parent(null);
		}

		if(node != null)
		{
			if(node.parent() != null)
			{
				node.parent().removeChild(node);
			}

			node.parent(this);
		}

		_assign_ = node;
	}
	public void setVariableDeclaratorId(PVariableDeclaratorId node)
	{
		if(_variableDeclaratorId_ != null)
		{
			_variableDeclaratorId_.parent(null);
		}

		if(node != null)
		{
			if(node.parent() != null)
			{
				node.parent().removeChild(node);
			}

			node.parent(this);
		}

		_variableDeclaratorId_ = node;
	}
	public void setVariableInitializer(PVariableInitializer node)
	{
		if(_variableInitializer_ != null)
		{
			_variableInitializer_.parent(null);
		}

		if(node != null)
		{
			if(node.parent() != null)
			{
				node.parent().removeChild(node);
			}

			node.parent(this);
		}

		_variableInitializer_ = node;
	}
	public String toString()
	{
		return ""
			+ toString(_variableDeclaratorId_)
			+ toString(_assign_)
			+ toString(_variableInitializer_);
	}
}
