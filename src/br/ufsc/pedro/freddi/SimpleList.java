package br.ufsc.pedro.freddi;

public class SimpleList<E extends IElement> {
    private int elementAmount;
    private Box<E> firstElement;
    private Box<E> lastElement;
    private Box <E> cursor;

    public SimpleList() {
        this.elementAmount = 0;
    }

    public void insertInFirst(E newData) {
        if(this.isEmpty()){
            this.firstElement = new Box<>(newData, null);
            this.lastElement = firstElement;
            this.cursor = firstElement;
            this.elementAmount++;
        }else{
            Box<E> holder = new Box<>(newData, null);
            this.firstElement.getPreviousBox().setNextBox(holder);
            holder.setNextBox(firstElement);
            holder.setPreviousBox(this.lastElement);
            this.firstElement.setPreviousBox(holder);
            this.firstElement = holder;
            this.cursor = this.firstElement;
            this.elementAmount++;
        }
    }

    public void insertInLast(E newData){
        if(this.isEmpty()){
            this.firstElement = new Box<>(newData, null);
            this.lastElement = firstElement;
            this.cursor = this.lastElement;
            this.elementAmount++;
        }else{
            Box<E> holder = new Box<>(newData, null);
            this.lastElement.getNextBox().setPreviousBox(holder);
            holder.setNextBox(firstElement);
            holder.setPreviousBox(this.lastElement);
            this.lastElement.setNextBox(holder);
            this.lastElement = holder;
            this.cursor = this.lastElement;
            this.elementAmount++;
        }
    }

    //"I want to inspire you
    // I want to be your rock
    // And when I talk
    // It lights a fire in you"

    public boolean search(int id) {
        if (!this.isEmpty()) {
            int checkedElements = 2;

            this.cursorToLast();

            if (cursor.getData().getId() == id) {
                return true;
            }

            this.cursorParaPrimeiroElemento();

            if (cursor.getData().getId() == id) {
                return true;
            }

            while (checkedElements < this.elementAmount) {
                this.avancarCursor(1);
                if (cursor.getData().getId() == id) {
                    return true;
                }
                checkedElements++;
            }
        }

        return false;
    }

    public void inserBeforeActual(E newData) {
        if (this.isEmpty() || this.getSizeList() == 1) {
            this.insertInFirst(newData);
        }else {
            Box<E> holder = new Box<>(newData, null);
            this.cursor.getPreviousBox().setNextBox(holder);
            holder.setPreviousBox(this.cursor.getPreviousBox());
            holder.setNextBox(this.cursor);
            this.cursor.setPreviousBox(holder);
            this.setCursor(holder);
        }
    }
//        if (this.isEmpty()) {
//            this.primeiroElemento = elementoNovo;
//            this.ultimoElemento = elementoNovo;
//            elementoNovo.setProximo(elementoNovo);
//            elementoNovo.setAnterior(elementoNovo);
//            this.cursor = elementoNovo;
//            this.quantidadeElementos = 1;
//        } else {
//            T proximoElemento = this.cursor.getProximo();
//            proximoElemento.setAnterior(proximoElemento);
//
//            T elementoAtual = this.cursor;
//            elementoAtual.setProximo(elementoNovo);
//
//            elementoNovo.setAnterior(elementoAtual);
//            elementoNovo.setProximo(proximoElemento);
//        }
//
//        this.cursor = elementoNovo;
//        quantidadeElementos++;

    public void inserirAntesDoAtual(T elementoNovo) {
        switch (quantidadeElementos) {
            case 0: {
                this.primeiroElemento = elementoNovo;
                this.ultimoElemento = elementoNovo;
                elementoNovo.setProximo(elementoNovo);
                elementoNovo.setAnterior(elementoNovo);
                this.cursor = elementoNovo;
                quantidadeElementos++;
            }
            case 1: {
                this.ultimoElemento = elementoNovo;
                elementoNovo.setAnterior(this.primeiroElemento);
                elementoNovo.setProximo(this.primeiroElemento);
                this.avancarCursor(1);
                quantidadeElementos++;
            }
            case 2: {
                this.ultimoElemento.setAnterior(elementoNovo);
                this.primeiroElemento.setProximo(elementoNovo);
                elementoNovo.setAnterior(this.primeiroElemento);
                elementoNovo.setProximo(this.ultimoElemento);
                retrocederCursor(1);
                quantidadeElementos++;
            }
            default: {
                elementoNovo.setAnterior(cursor.getAnterior());
                cursor.getAnterior().setProximo(elementoNovo);
                cursor.setAnterior(elementoNovo);
                elementoNovo.setProximo(cursor);
                quantidadeElementos++;
            }

        }
//        if (this.isEmpty()) {
//            this.primeiroElemento = elementoNovo;
//            elementoNovo.setProximo(elementoNovo);
//            elementoNovo.setAnterior(elementoNovo);
//        } else {
//            T elementoAnterior = this.cursor.getAnterior();
//            elementoAnterior.setProximo(elementoNovo);
//
//            T elementoAtual = this.cursor.getProximo();
//            elementoAtual.setAnterior(elementoNovo);
//
//            elementoNovo.setProximo(elementoAtual);
//            elementoNovo.setAnterior(elementoAnterior);
//
//            if (this.cursor.equals(this.primeiroElemento)) {
//                this.primeiroElemento = elementoNovo;
//            }
//            if (this.cursor.equals(this.ultimoElemento)) {
//                this.ultimoElemento = elementoNovo;
//            }
//        }
//
//        this.cursor = elementoNovo;
//        quantidadeElementos++;
    }

    public void excluirAtual() {
        if (this.isEmpty()) {
            this.cursor = null;
        } else if (cursor.equals(this.primeiroElemento)) {
            cursor.getAnterior().setProximo(cursor.getProximo());
            cursor.getProximo().setAnterior(cursor.getAnterior());
            this.primeiroElemento = cursor.getProximo();
            this.quantidadeElementos--;
        } else if (cursor.equals(this.ultimoElemento)) {
            cursor.getAnterior().setProximo(cursor.getProximo());
            cursor.getProximo().setAnterior(cursor.getAnterior());
            this.quantidadeElementos--;
        } else {
            cursor.getAnterior().setProximo(cursor.getProximo());
            cursor.getProximo().setAnterior(cursor.getAnterior());
        }
    }

    public boolean excluir(int id) {
        if (this.buscar(id)) {
            this.cursorParaUltimoElemento();
            if (cursor.getAnterior().getId() == id) {
                this.ultimoElemento = cursor.getAnterior();
                cursor.getProximo().setAnterior(cursor.getAnterior());
                cursor.getAnterior().setProximo(cursor.getProximo());
                this.quantidadeElementos--;
                this.setCursor(this.ultimoElemento);
                return true;
            }
            this.cursorParaPrimeiroElemento();
            if (cursor.getId() == id) {
                cursor.getProximo().setAnterior(cursor.getAnterior());
                cursor.getAnterior().setProximo(cursor.getProximo());
                this.quantidadeElementos--;
                this.primeiroElemento = cursor.getProximo();
                this.setCursor(this.primeiroElemento);
                return true;
            } else {
                int elementosPercorridos = 2;
                while (elementosPercorridos < getTamanhoLista()) {
                    this.avancarCursor(1);
                    if (cursor.getId() == id) {
                        cursor.getProximo().setAnterior(cursor.getAnterior());
                        cursor.getAnterior().setProximo(cursor.getProximo());
                        this.quantidadeElementos--;
                        return true;
                    }
                    elementosPercorridos++;
                }
            }
        }
        return false;
    }

    public Box<E> getFirstElement() {
        return this.primeiroElemento;
    }

    public Box<E> getLastElement() {
        return this.lastElement;
    }

    public boolean isEmpty() {
        return this.elementAmount == 0;
    }

    public Box<E> getActual() {
        return this.cursor;
    }

    private void cursorToLast() {
        this.cursor = this.getLastElement();

    }

    private void cursorToFirst() {
        this.cursor = this.getFirstElement();

    }

//    private void avancarCursor(int elementos) {
//        for (int i = 0; i < elementos; i++) {
//            this.cursor = this.cursor.getProximo();
//        }
//    }
//
//    private void retrocederCursor(int elementos) {
//        for (int i = 0; i < elementos; i++) {
//            this.cursor = this.cursor.getAnterior();
//        }
//    }

    public int getSizeList() {
        return this.elementAmount;
    }

    public Box<E> getCursor() {
        return cursor;
    }

    public void setCursor(Box<E> cursor) {
        this.cursor = cursor;
    }

}

