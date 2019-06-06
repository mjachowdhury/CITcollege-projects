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
package com.swtworkbench.bus.netbridge.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.swtworkbench.bus.Choreographer;
import com.swtworkbench.bus.Result;
import com.swtworkbench.bus.SerializableResult;
import com.swtworkbench.bus.netbridge.ISynchronizer;
import com.swtworkbench.bus.netbridge.RunnableWithResult;
import com.swtworkbench.swtutils.logger.Logger;


/**
 * This thread receives update notifications from the remote Choreographer and propogates
 * them to the local Choreographer object.
 * 
 * @author DaveO
 */
public class CallbackThread extends Thread {
    private Socket socket;
    protected final Choreographer choreographer;
    protected final ISynchronizer synchronizer;

	/**
     * Constructor CallbackThread.  Initialize the CallbackThread from the client.
     * 
     * @param host The host name or IP of the remote host
     * @param port The port number to which to connect
     * @param choreographer A reference to the choreographer
     * @param synchronizer An object that can run runnables on the Choreographer's thread if necessary
     * @throws IOException
     */
    public CallbackThread(Socket socket, Choreographer choreographer, ISynchronizer synchronizer) throws IOException {
        // Initialize and open the connection
        setDaemon(true);
        this.socket = socket;
        this.choreographer = choreographer;
        this.synchronizer = synchronizer;
	}

    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Process callbacks
     */
    public void run() {
        while (true) {
	        try {
                in = new ObjectInputStream(socket.getInputStream());
                final Object obj = in.readObject();
                RunnableWithResult action = new RunnableWithResult() {
                    public Object runIt() throws Exception {
                        return choreographer.request(obj);
                    }
                };
                
                SerializableResult returnedResult;
                try {
                    Result result = (Result) synchronizer.execute(action);
                    returnedResult = new SerializableResult(result);
                } catch (Exception e) {
                    returnedResult = new SerializableResult(e);
                }
                
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(returnedResult);
            }
            catch (Exception e) {
	            if (e instanceof EOFException) {
	                // Do nothing, this is normal behavior on connection close
	            	break;
	            } else if (e instanceof SocketException){
                    Logger.log().debug(CallbackThread.class, "Server disconnected");
                    Logger.log().debug(CallbackThread.class, e.toString());
                    break;
                } else {
                    Logger.log().error(e, "Unexpected exception");
	            }
	        }
        }
    }
}

