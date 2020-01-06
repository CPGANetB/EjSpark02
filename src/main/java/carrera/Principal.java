package carrera;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static spark.Spark.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Principal {
    private static ArrayList<Participante> participantes = new ArrayList();
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        port(8888);
        Date date = new Date();
        DateFormat fechorFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
        String opc = "";
        Participante participante = new Participante();
        StringProperty envio = new SimpleStringProperty();
        StringProperty envion = new SimpleStringProperty();
        StringProperty envioc = new SimpleStringProperty();
        StringProperty envios = new SimpleStringProperty();
        StringProperty envioo = new SimpleStringProperty();
        StringProperty envioom = new SimpleStringProperty();
        Participante nvop= new Participante();
        ArrayList<Participante> nvoa= new ArrayList();
        envio.set(CreaHtml.CreaHtml("participants",nvop,nvoa).toString());
        envion.set(CreaHtml.CreaHtml("newparticipant",nvop,nvoa ).toString());
        envioc.set( CreaHtml.CreaHtml("create",nvop,nvoa).toString() );
        envios.set("Fecha y Hora: "+ fechorFormat.format(date) + "\n" + "<br>Finalizar... " + "\n");
        envioo.set("Fecha y Hora: "+ fechorFormat.format(date) + "\n" + "<br>Otro... " + "\n");
        envioom.set("Fecha y Hora: "+ fechorFormat.format(date) + "\n" + "<br>Otro mas... " + "\n");
        get("/participants/", (req, res) -> envio.get());
        get("/participants/newparticipant", (req, res) -> envion.get() );
        get("/participants/create", (req, res) -> envioc.get());
        get("/participants/stop", (req, res) -> envios.get());
        get("/participants/otro", (req, res) -> envioo.get());
        get("/participants/otromas", (req, res) -> envioom.get());
        awaitInitialization();
        Random r = new Random();
        int rnd = r.nextInt(99)+1;
        StringBuffer nvaPag = new CreaHtml().CreaHtml("pag", participante, participantes );
        participantes.add(new Participante("PEPE-01", (r.nextInt(99)+1)*1.0));
        participantes.add(new Participante("JUAN-02", (r.nextInt(99)+1)*1.0));
        participantes.add(new Participante("PIPO-03", (r.nextInt(99)+1)*1.0));
        do {
            System.out.println("Ingrese Nombre o 0 para Salir");

            opc = scn.next();
            if (!opc.equals("0")) {
                String nombre = opc;
                System.out.println("Ingrese Score");
                Double score = StringToDouble(scn.next());
                //Participante otro = new Participante();
                nvop.setNombre(nombre);
                nvop.setScore(score);
                System.out.println(nvop);
                participantes.add(new Participante(nombre, score));
            }

            post("/participants/create", (req, res) -> {
                String nombr = req.queryParams("noming1");
                String sco = req.queryParamOrDefault("scoing2", "0");
                //Participante participan = new Participante();
                nvop.setNombre(nombr);
                nvop.setScore(StringToDouble(sco));
                participantes.add(new Participante(nombr,StringToDouble(sco)));
                res.type("text/html");
                return envioc.get();
            });
                nvoa = participantes;
                envion.set(CreaHtml.CreaHtml("newparticipant", nvop, nvoa).toString());
                get("/participants/newparticipant", (req, res) -> envion.get());

                //Actualiza();
                Ordena();
                envio.set(CreaHtml.CreaHtml("participants",nvop,nvoa).toString());
                get("/participants/", (req, res) -> envio.get());
                //participantes.add(new Participante(nombre,score));

        } while (!opc.equals("0"));

        System.out.println("Ordena");
        //Ordena();
        System.out.println("Fin");
        stop();
    }


    public static void Actualiza(){
        String que;
        Participante ulin = new Participante();

        System.out.println("Se agregaron: " );
        Iterator iter = participantes.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
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
