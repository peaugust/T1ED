package br.ufsc.pedro.freddi;

public class Box<E extends IElement> {
    private E data;
    private Box<E> nextBox;
    private Box<E> previousBox;

    public Box(E data, Box<E> nextBox) {
        this.data = data;
        this.nextBox = nextBox;
    }

    public E getData() {
        return data;
    }

    public Box<E> getNextBox() {
        return nextBox;
    }

    public Box<E> getPreviousBox() {
        return previousBox;
    }

    public void setNextBox(Box<E> nextBox) {
        this.nextBox = nextBox;
    }

    public void setPreviousBox(Box<E> previousBox) {
        this.previousBox = previousBox;
    }

}
