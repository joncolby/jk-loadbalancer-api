class MasterTomcatService {

    boolean transactional = false

    def boolean startRemoteTomcat(TomcatInstance instance) {
        return callRemoteTomcat(instance, 'start')
    }

    def boolean stopRemoteTomcat(TomcatInstance instance) {
        return callRemoteTomcat(instance, 'stop')
    }

    protected boolean callRemoteTomcat(TomcatInstance instance, String cmd) {
        boolean status = false
        def url = "http://${instance.host.ipAddress}:9090/deployagent/tomcatInstance/${cmd}/${instance.id}?mime=txt"
        println "call " + url
        def conn
        try {
            conn = new URL(url).openConnection()
            conn.requestMethod = "GET"
            conn.doOutput = true

            if (conn.responseCode == conn.HTTP_OK &&
                    conn.inputStream.getText().startsWith('Result: type=OK')) {
                status = true;
            }
        } finally {
            if (conn) conn.disconnect()
        }
        return status
    }
}
