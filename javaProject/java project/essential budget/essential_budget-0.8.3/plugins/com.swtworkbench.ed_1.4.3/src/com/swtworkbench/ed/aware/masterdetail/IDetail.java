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

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;

/**
 * Interface IDetail.  An interface for editors that can be a detail editor
 * in 1:1 or 1:M master-detail relationships.
 * <p>
 * An IDetail object is told about its master.  It is then responsible to
 * register itself as an IMasterObjectChangeListener with its IMaster.
 * When an IMasterObjectChangeListener event is received, it is responsible
 * for retrieving the new master object and setting its input using setInput().
 * 
 * @see com.swtworkbench.ed.aware.interfaces.IMaster
 * 
 * @author daveo
 */
public interface IDetail {
    
    /**
     * Method setMaster.  Sets the master object from which this detail object
     * gets its input.
     * 
     * @param master The IMaster object that will provide the input object
     */
    public void setMaster(IMaster master);
    
    /**
     * Method setInput.  Changes the input object for the IDetail object.
     * 
     * @param bean The new input bean
     * 
     * @throws UnableToSaveException If existing data fails validation checks
     */
    public void setInput(Object bean) throws UnableToSaveException;

}


