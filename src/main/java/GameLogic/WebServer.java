package GameLogic;

import com.jcraft.jsch.*;

public class WebServer {
    String user;
    String password;
    String host;
    int port;
    JSch jsch;
    Session session;
    public WebServer(){
        user = "user";
        password = "password";
        host = "192.168.100.243";
        jsch = new JSch();
        session = null;
    }
    public void createConexion(){
        try {
            session = jsch.getSession(user, host);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection...");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
    public void sendHtml(String path){
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            sftpChannel.put(path, "/target/remote/path/file.html");
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
}
