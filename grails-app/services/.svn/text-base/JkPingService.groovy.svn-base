/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** 
 * Check Tomcat AJP Port is available and working (CPing,CPong)!
 * 
 * @author Peter Rossbach (pr@objektpark.de)
 * @version $Revision:$, $Date:$
 */
class JkPingService {

    boolean transactional = false
    int timeout = 500
    
    def boolean ping( String host, Integer port) {

        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(InetAddress.getByName(host), port);
            socket.setSoTimeout(timeout);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(0x12); // Packet header
            outputStream.write(0x34); // Packet header
            outputStream.write(0);    // Packet length - byte 1
            outputStream.write(1);    // Packet length - byte 2
            outputStream.write(10);   // Ping Command

            // pong (expect AB19 (1 => 2 bytes, 9=Reply of pong)
            byte[] response = new byte[5];
            int bytesRead = inputStream.read(response);
            if (bytesRead == 5 && response[4] == 9) {
                return true
            }
        } catch(IOException e) {
            // No connect or timeout
        } finally {
            try {
                if(inputStream)
                    inputStream.close()
                if(outputStream)
                    outputStream.close()
                if(socket)
                    socket.close()
            } catch (IOException ignore) {}
        }
        return false
    }
}
