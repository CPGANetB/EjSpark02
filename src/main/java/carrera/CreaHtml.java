package carrera;
import java.util.*;

public class CreaHtml{
    //private String html;
    private Participante unParti;
    private ArrayList<Participante> aLst;

    public static StringBuffer CreaHtml(String html, Participante unParti, ArrayList aLst){
        //String html = html;
        Participante parti = unParti;
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!DOCTYPE html><html lang=\"es\">\n");
        buffer.append("<head><meta charset=\"UTF-8\">\n");
        if (html == "create") {
            buffer.append("<title> Crear Participante </title>\n");
            buffer.append("</head>\n");
            buffer.append("<body><div><div id=\"quever\"><h2> Alta de participante </h2><h3> Datos a ingresar: </h3><table>\n");
            buffer.append("<form method=\"post\" action=\"/participants/create\" >\n");
            buffer.append("<tr><th>Nombre: </th><th><input id=\"datoing1\" name=\"noming1\" type=\"text/html\" ></th></tr>\n");
            buffer.append("<tr><th>Score: </th><td><input id=\"datoing2\" name=\"scoing2\" type=\"text\" value=\"123\"></td></tr>\n");
            buffer.append("<tr><td colspan=\"2\" ><br><input type=\"submit\" name=\"confi3\"value=\"Ingresa o 0 para salir\"></td></tr>\n");
            buffer.append("<tr><td></td></tr></form></table>\n");
            buffer.append("</table></div><br>\n");
            buffer.append("<div ><table ><tr><th id=\"score\"></th><th id=\"nombre\"></th></tr>\n");
            buffer.append("<tr><td align=\"center\"></th><td colspan=\"2\" align=\"left\"></td><div id=\"respuesta\"></div></tr></table></div>\n");
        }

        if (html == "newparticipant") {
            buffer.append("<title> Nuevo participante </title>\n");
            buffer.append("</head>\n");
            buffer.append("<body><div><div id=\"quever\"><h2> Nuevo participante </h2>");
            if (parti.nombre != null) {
                buffer.append("<h3> Se agregó: </h3><table >\n");
                buffer.append("<tr><th>Nombre: </th><th><div id=\"newNom\"><strong>" + parti.nombre  + " </strong></div></th></tr>\n");
                buffer.append("<tr><th>Score: </th><td><div id=\"newSco\"><strong>" + parti.score + " </strong></div></td></tr>\n");
                buffer.append("<tr><td colspan=\"2\" align=\"center\"><br></td></tr></table>\n");
            }
        }

        if (html == "participants") {
            ArrayList aLis = new ArrayList(aLst);
            aLis = aLst;
            buffer.append("<title> Participantes </title>\n");
            buffer.append("</head>\n");
            buffer.append("<body><div><div id=\"quever\"><h2> Participantes </h2><h3><i>Lista ordenada:</i></h3>" +
                    "<a href=\"http://localhost:8888/participants/create\">Agrega nuevo</a><br>\n");
            if (aLis.toString() != "") {
                buffer.append("<br><table ><tr bgcolor=\"lightblue\"><th><u>Nombre</u></th><th><th><u>Score</u></th></th></tr>\n");
                buffer.append("<tr id=\"ordLst\">");
                Collections.sort(aLis, new Comparator<Participante>() {
                    @Override
                    public int compare(Participante p1, Participante p2) {
                        return new Double(p2.getScore()).compareTo(new Double(p1.getScore()));
                    }
                });
                Participante parti1 = new Participante();
                Iterator iter = aLst.iterator();
                while (iter.hasNext()) {
                    parti1 = (Participante) iter.next();
                    buffer.append("<tr><td><b>" + parti1.nombre + "</b></td><td><td><b>" + parti1.score + "</b></td></td></tr></tr>\n");
                }
            }
            buffer.append("<tr><td colspan=\"2\" align=\"center\"><br></td></tr></table>\n");
        }
        buffer.append("</div></body></html>\n");
        StringBuffer bufferc = new StringBuffer(buffer.toString());
        return bufferc;
    }

}
