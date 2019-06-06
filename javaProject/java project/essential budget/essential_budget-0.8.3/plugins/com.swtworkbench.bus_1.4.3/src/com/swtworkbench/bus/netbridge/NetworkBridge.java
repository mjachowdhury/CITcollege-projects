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
package com.swtworkbench.bus.netbridge;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import com.swtworkbench.bus.Choreographer;
import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.bus.SerializableResult;
import com.swtworkbench.bus.netbridge.connection.IConnection;
import com.swtworkbench.bus.netbridge.internal.CallbackThread;

/**
 * Class NetworkBridge.  Creates a bridge between two Choreographer busses
 * running in separate JVMs, possibly on different physical computers.  This
 * implementation just copies Choreographer commands it receives to the remote
 * Choreographer and vice versa.
 * 
 * <p>This is useful if you want to use Prevayler (http://www.prevayler.org)
 * with Essential Data.</p>
 * 
 * @author djo
 */
public class NetworkBridge implements IActor {

    // Private members
    private CallbackThread callbackThread;
    private Choreographer choreographer;
    
    // I/O streams
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    /**
     * Constructor NetworkBridge.  Construct a NetworkBridge between Choreographer
     * busses using the specified synchronization policy and network connection.
     * 
     * @param connection The IConnection on which to communicate
     * @param choreographer The choreographer bus to bridge
     * @param synchronizer The ISyncronizer implementing the policy of on what
     * thread to run Choreographer requests that are received in the background
     * from the remote Choreographer.
     *  
     * @throws IOException
     */
    public NetworkBridge(IConnection connection, Choreographer choreographer, ISynchronizer synchronizer) throws IOException {
        this.choreographer = choreographer;

        // Connect to remote Choreographer server
        socket = connection.getSendConnection();

        // Initialize the CallbackThread
        callbackThread = new CallbackThread(connection.getReceiveConnection(), choreographer, synchronizer);
        callbackThread.start();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.core.IActor#perform(java.lang.Object)
     */
    public Object perform(Request r) throws Exception {
        if (r.action instanceof Serializable) {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(r);
            in = new ObjectInputStream(socket.getInputStream());
            SerializableResult result = (SerializableResult) in.readObject();
            Exception e = result.exception;
            if (e != null) throw e;
            if (result.handled) r.fulfilled = true;
            return result.result;
        }
        return null;
    }

}
