/*
 * This file is part of com.swtworkbench.bus.
 *
 * com.swtworkbench.bus is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.bus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.bus; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.bus.netbridge.connection;

import java.net.Socket;

/**
 * Class IConnection.  
 * 
 * @author daveo
 */
public interface IConnection {
    /**
     * Method getSendConnection.  Gets the socket used for sending requests
     * to the remote side.
     *   
     * @return Socket the sending socket
     */
    public abstract Socket getSendConnection();
    /**
     * Method getReceiveConnection. Gets the socket used to asynchronously
     * receive requests originating at the remote side of the connection.
     *   
     * @return Socket the receiving socket 
     */
    public abstract Socket getReceiveConnection();
}