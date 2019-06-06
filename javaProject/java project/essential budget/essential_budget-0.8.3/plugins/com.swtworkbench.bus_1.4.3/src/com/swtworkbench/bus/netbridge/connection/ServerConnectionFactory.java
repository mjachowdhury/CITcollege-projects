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
import java.net.ServerSocket;
import java.net.Socket;

import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class ServerConnectionFactory.  This class is intended to be used within
 * a server thread to allow (potentially) multiple clients to connect.<p>
 * 
 * The type of client/server arrangement uses two ports--one for incoming-
 * initiated conversations and one for outgoing-initiated conversations.
 * 
 * @author daveo
 */
public class ServerConnectionFactory {
    private int basePort;
    private ServerSocket server;
    
    /**
     * Constructor ServerConnectionFactory.
     * 
     * @param basePort The base port on which to wait for connections.
     */
    public ServerConnectionFactory(int basePort) throws IOException {
        this.basePort = basePort;
        server = new ServerSocket(basePort);
    }
    
    /**
     * Method connect.  Wait for a new connection.  Blocks until a new
     * connection is received.  Clients of this class must then spawn
     * a new thread in order to handle the new Connection.
     * 
     * @return Connection the new Connection
     */
    public IConnection connect() throws IOException {
        Socket receiver = server.accept();
        Socket sender = new Socket(receiver.getInetAddress(), basePort+1);
        return new SocketConnection(sender, receiver);
    }
    
    /**
     * Method close.  Shut down the server socket and free any system
     * resources allocated.
     */
    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            Logger.log().error(e, "Error closing server socket");
        }
    }
}
