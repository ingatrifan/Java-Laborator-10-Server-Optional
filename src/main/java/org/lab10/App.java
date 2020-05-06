package org.lab10;

import GameLogic.Board;
import GameLogic.HtmlBuilder;
import GameLogic.WebServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Board board = new Board();
//        HtmlBuilder htmlBuilder = new HtmlBuilder(board);
//        WebServer webServer = new WebServer();
//        webServer.createConexion();
//        webServer.sendHtml(htmlBuilder.getFilePath());
        GameServer gs = new GameServer();
    }
}
