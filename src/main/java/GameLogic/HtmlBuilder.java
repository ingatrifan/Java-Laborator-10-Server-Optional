package GameLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlBuilder {
    StringBuilder sb;
    String filePath;
    public HtmlBuilder(Board board){
        createHtml(board);
        writeHtml();
    }
    private StringBuilder createHtml(Board board){
        sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<style>");
        sb.append("td{width:20px;height:20px;}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        createTable(board);
        sb.append("</body>");
        sb.append("</html>");
        return sb;
    }
    private void createTable(Board board){
        sb.append("<table>");
        int [][] table = board.getTable();
        for (int i=1;i<board.getTableSize();i++){
            sb.append("<tr>");
            for (int j=1;j<board.getTableSize();j++){
                if (table[i][j] == 0)sb.append("<td></td>"); else
                if (table[i][j] == 1)sb.append("<td style='background-color:red'></td>"); else
                if (table[i][j] == 2)sb.append("<td style='background-color:black'></td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
    }

    private void writeHtml(){
        FileWriter myWriter = null;
        filePath = "/home/batman/report.html";
        try {
            myWriter = new FileWriter(new File(filePath));
            myWriter.write(sb.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
