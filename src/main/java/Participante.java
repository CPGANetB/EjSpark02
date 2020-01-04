public class Participante {

    private String nombre;
    private Double score;

    public Participante(){}

    public Participante ( String nombre, Double score) {
        this.nombre = nombre;
        this.score = score;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    @Override
    public String toString() {
        //return "Nombre: " + this.nombre + " Score: " + this.score;
        return String.format( "%s\t%.2f", this.nombre, this.score);
    }
}
