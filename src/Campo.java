import java.util.ArrayList;
import java.util.List;

public class Campo {

    private EstadosCampo estadoatual;
    private int valor;
    private boolean visitado = false;

    public Campo() {
        this.estadoatual = EstadosCampo.Fechado;
    }

    public Campo(EstadosCampo valor) {
        this.estadoatual = valor;
    }

    public EstadosCampo getEstadoatual() {
        return estadoatual;
    }

    public void setEstadoatual(EstadosCampo estadoatual) {
        this.estadoatual = estadoatual;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

}
