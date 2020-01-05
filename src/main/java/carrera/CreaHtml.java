package carrera;
import java.util.*;

public class CreaHtml{
    private String html;
    private Participante unParti;
    private ArrayList<Participante> aLst;

    public StringBuffer CreaHtml(String html, Participante unParti, ArrayList aLst){


        StringBuffer buffer = new StringBuffer();
        buffer.append("<!DOCTYPE html><html lang=\"es\">\n");
        buffer.append("<head><meta charset=\"UTF-8\">\n");


        StringBuffer bufferc = new StringBuffer(buffer.toString());
        return bufferc;
    }

}
