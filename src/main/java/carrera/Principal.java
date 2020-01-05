package carrera;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.*;

public class Principal {
    private static ArrayList<Participante> participantes = new ArrayList();
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        port(8888);
        String opc = "";
        Participante participante = new Participante();
        StringProperty envio = new SimpleStringProperty();
        Participante nvop= new Participante();
        ArrayList nvoa= new ArrayList();
        envio.set(CreaHtml.CreaHtml("participants",nvop,nvoa).toString());
        get("/participants/", (req, res) -> envio.get());
        awaitInitialization();
        StringBuffer nvaPag = new CreaHtml().CreaHtml("pag", participante, participantes );
        participantes.add(new Participante("PEPE-01", 46.0));
        participantes.add(new Participante("JUAN-02", 15.0));
        participantes.add(new Participante("PIPO-03", 89.0));
        do {
            System.out.println("Ingrese Nombre o 0 para Salir");
            opc = scn.next();
            if (!opc.equals("0")) {
                String nombre = opc;
                System.out.println("Ingrese Score");
                Double score = StringToDouble(scn.next());
                Participante otro = new Participante();
                otro.setNombre(nombre);
                otro.setScore(score);
                System.out.println(otro);
                participantes.add(otro);
                //participantes.add(new Participante(nombre,score));
            }
        } while (!opc.equals("0"));
        //System.out.println("Ordena");
        Ordena();
        nvaPag = CreaHtml.CreaHtml("participants", participante, participantes);
        nvaPag.toString();
        System.out.println("Termina" + nvaPag.toString());
    }

    public static void Ordena(){
        System.out.println(String.format("%s\t%s","Nombre", "Score"));
        Collections.sort(participantes, new Comparator<Participante>() {
            @Override
            public int compare(Participante o1, Participante o2) {
                return new Double(o2.getScore()).compareTo(new Double(o1.getScore()));
            }
        });
        Iterator iter = participantes.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
    }

    private static Double StringToDouble(String s) {
        Double d = 0.0;
        //Elimina valores no numericos
        s = s.replaceAll("[^\\d.-]", "");
        try {
            d = (s != null) ? Double.valueOf(s) : 0.0;
        } catch (NumberFormatException e) {
            System.out.println("not a number");
        }
        return d;
    }

}
