/* This file was generated by SableCC (http://www.sablecc.org/). */

package edu.ksu.cis.bandera.birp.node;

import java.util.*;
import edu.ksu.cis.bandera.birp.analysis.*;

public final class ANotExpr1 extends PExpr1
{
    private TNot _not_;
    private PExpr1 _expr1_;

    public ANotExpr1()
    {
    }

    public ANotExpr1(
        TNot _not_,
        PExpr1 _expr1_)
    {
        setNot(_not_);

        setExpr1(_expr1_);

    }
    public Object clone()
    {
        return new ANotExpr1(
            (TNot) cloneNode(_not_),
            (PExpr1) cloneNode(_expr1_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotExpr1(this);
    }

    public TNot getNot()
    {
        return _not_;
    }

    public void setNot(TNot node)
    {
        if(_not_ != null)
        {
            _not_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _not_ = node;
    }

    public PExpr1 getExpr1()
    {
        return _expr1_;
    }

    public void setExpr1(PExpr1 node)
    {
        if(_expr1_ != null)
        {
            _expr1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expr1_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_not_)
            + toString(_expr1_);
    }

    void removeChild(Node child)
    {
        if(_not_ == child)
        {
            _not_ = null;
            return;
        }

        if(_expr1_ == child)
        {
            _expr1_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_not_ == oldChild)
        {
            setNot((TNot) newChild);
            return;
        }

        if(_expr1_ == oldChild)
        {
            setExpr1((PExpr1) newChild);
            return;
        }

    }
}
