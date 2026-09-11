public class Queue implements QueueInterface {

    QueueNode head;
    QueueNode tail;
    int size = 0;
    boolean isPriorityQueue;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.isPriorityQueue = false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.isPriorityQueue = false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object extract() {
        if (this.head == null) {
            return null;
        }

        Object object = this.head.object;
        this.head = this.head.next;
        this.size--;

        if (this.head == null) {
            this.tail = null;
        }

        return object;
    }

    @Override
    public boolean insert(Object object) {
        QueueNode nuevoNodo = new QueueNode();
        nuevoNodo.object = object;

        if (this.head == null) {
            this.head = nuevoNodo;
            this.tail = nuevoNodo;
        } else {
            this.tail.next = nuevoNodo;
            this.tail = nuevoNodo;
        }

        this.size++;
        return true;
    }

    @Override
    public boolean insert(Object object, int prioridad) {
        QueueNode nuevoNodo = new QueueNode();
        nuevoNodo.object = object;
        nuevoNodo.priority = prioridad;

        if (this.head == null) {
            this.head = nuevoNodo;
            this.tail = nuevoNodo;
            this.isPriorityQueue = true;
            this.size++;
            return true;
        }

        if (prioridad < this.head.priority) {
            nuevoNodo.next = this.head;
            this.head = nuevoNodo;
            this.isPriorityQueue = true;
            this.size++;
            return true;
        }

        QueueNode anterior = this.head;
        QueueNode iterador = this.head.next;

        while (iterador != null && iterador.priority <= prioridad) {
            anterior = iterador;
            iterador = iterador.next;
        }

        nuevoNodo.next = iterador;
        anterior.next = nuevoNodo;

        if (iterador == null) {
            this.tail = nuevoNodo;
        }

        this.isPriorityQueue = true;
        this.size++;

        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean search(Object object) {
        QueueNode iterador = this.head;

        while (iterador != null) {
            if (iterador.object.equals(object)) {
                return true;
            }

            iterador = iterador.next;
        }

        return false;
    }

    @Override
    public String toString() {
        String resultado = "[";
        QueueNode iterador = this.head;

        while (iterador != null) {
            resultado += iterador.object;

            if (iterador.next != null) {
                resultado += ", ";
            }

            iterador = iterador.next;
        }

        resultado += "]";

        return resultado;
    }
}
