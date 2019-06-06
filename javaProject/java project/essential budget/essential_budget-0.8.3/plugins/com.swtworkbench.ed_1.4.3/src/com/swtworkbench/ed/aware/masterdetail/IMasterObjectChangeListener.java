/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.aware.masterdetail;



/**
 * Class IMasterObjectChangeListener.  An interface for objects that listen to
 * object change events on any IMaster object.  This is intended mainly to
 * assist in implementing master/detail relationships although it could
 * be used for data validation in a pinch.  (Data validation would 
 * normally be done through a validator or other mechanism.)
 * 
 * @author daveo
 */
public interface IMasterObjectChangeListener {
    /**
     * Method requestChange.  Request to make an object change.  This method is
     * called before the change actually occurs to provide an opportunity
     * for clients to veto the change action.
     * @param e
     */
    public void requestChange(MasterObjectChangeEvent e);

    /**
     * Method masterObjectChanged.  This method is called after the master
     * object in a master/detail relationship has already been changed.  The 
     * e.doit field is ignored.
     * @param e
     */
    public void masterObjectChanged(MasterObjectChangeEvent e);
    
}
