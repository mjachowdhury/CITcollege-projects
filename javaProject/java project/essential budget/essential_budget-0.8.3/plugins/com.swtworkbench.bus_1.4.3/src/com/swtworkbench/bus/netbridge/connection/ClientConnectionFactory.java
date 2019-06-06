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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import EDU.oswego.cs.dl.util.concurrent.Callable;
import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import EDU.oswego.cs.dl.util.concurrent.QueuedExecutor;

/**
 * Class ClientConnectionFactory.  Connect to a server and return the Connection.
 * 
 * @author daveo
 */
public class ClientConnectionFactory {
    
    /**
     * Method connect.  Connect to a server and return a Connection containing
     * a pair of sockets--one for sending commands to the server, and one for
     * asynchronously receiving commands from the server.<p>
     * 
     * Note that the current method of doing this is insecure: There is a timing
     * window after the client has opened its socket to receive the connect-back
     * from the server but before the server actually connects back during which
     * an attacker could connect to the client, posing as the server, and send
     * the client arbitrary commands.  The proper way to avoid this would be
     * for the client to send a random value to the server, which is then 
     * sent back to the client when the server connects back.  The client then
     * rejects any server connecting back to it with an incorrect value.<p>
     * 
     * However, in many cases an easier way to secure the connection would be to
     * just tunnel the connection through secure shell or SSL. 
     * 
     * @param hostName The remote host.
     * @param basePort The base port to connect to.  basePort+1 will be the call-back port.
     * @return The Connection object denoting the (sender, receiver) socket pair.
     * 
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException if the background thread that accepts the call-back is interrupted.
     */
    public static IConnection connect(final String hostName, final int basePort) 
    throws UnknownHostException, IOException, InterruptedException
    {
        // We use QueuedExecutor and FutureResult to avoid the race condition
        // where we've connected to the server and the server calls us back
        // faster than we can open the call-back port for listening.
        QueuedExecutor executor = new QueuedExecutor();
        try {
            FutureResult receiverSocket = new FutureResult();
            Runnable command = receiverSocket.setter(new Callable() {
                public Object call() throws Exception {
                    Socket result = null;
                    ServerSocket server = new ServerSocket(basePort+1);
                    result = server.accept();
                    server.close();
                    return result;
                }
            });
            // Listen for the call-back in a background thread
            executor.execute(command);
            // Connect to the server
            Socket sender = new Socket(hostName, basePort);
            // Wait for the call-back
            Socket receiver = (Socket) receiverSocket.get();
            executor.shutdownNow();
            return new SocketConnection(sender, receiver);
        } catch (InvocationTargetException e) {
            Exception e1 = (Exception) e.getTargetException();
            if (e1 instanceof UnknownHostException)
                throw (UnknownHostException) e1;
            else if (e1 instanceof IOException)
                throw (IOException) e1;
            else throw new IOException(e1.getMessage());
        } finally {
            executor.shutdownNow();
        }
    }

}
