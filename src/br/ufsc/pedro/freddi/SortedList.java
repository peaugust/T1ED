package br.ufsc.pedro.freddi;

public class SortedList {
    private SimpleList<T> lista;

    public SortedList() {
        this.lista = new SimpleList<>();
    }

    public boolean inserirOrdenado(T elemento) {
        if (this.lista.isEmpty()) {
            this.lista.inserirAposAtual(elemento);
            return true;
        } else {
            T inicioLista = lista.getPrimeiroElemento();

            for (int i = 0; i < lista.getTamanhoLista(); i++) {
                if (elemento.getId() < inicioLista.getId()) {
                    this.lista.setCursor(inicioLista);
                    this.lista.inserirAntesDoAtual(elemento);

                    return true;
                }

                inicioLista = inicioLista.getProximo();
            }

            inicioLista = inicioLista.getAnterior();

            this.lista.setCursor(inicioLista);
            this.lista.inserirAposAtual(elemento);

            return false;
        }
    }

    public void excluir(int id) {
        this.lista.excluir(id);
    }

    public T buscar(int id) {
        if (this.lista.buscar(id)) {
            return this.lista.getCursor();
        }

        return null;
    }

    public SimpleList getListaOrdenada() {
        return lista;
    }
}
