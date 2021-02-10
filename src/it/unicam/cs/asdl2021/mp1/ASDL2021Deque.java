package it.unicam.cs.asdl2021.mp1;

import java.util.*;

/**
 * Implementation of the Java SE Double-ended Queue (Deque) interface
 * (<code>java.util.Deque</code>) based on a double linked list. This deque does
 * not have capacity restrictions, i.e., it is always possible to insert new
 * elements and the number of elements is unbound. Duplicated elements are
 * permitted while <code>null</code> elements are not permitted. Being
 * <code>Deque</code> a sub-interface of
 * <code>Queue<code>, this class can be used also as an implementation of a <code>Queue</code>
 * and of a <code>Stack</code>.
 * 
 * The following operations are not supported:
 * <ul>
 * <li><code>public <T> T[] toArray(T[] a)</code></li>
 * <li><code>public boolean removeAll(Collection<?> c)</code></li>
 * <li><code>public boolean retainAll(Collection<?> c)</code></li>
 * <li><code>public boolean removeFirstOccurrence(Object o)</code></li>
 * <li><code>public boolean removeLastOccurrence(Object o)</code></li>
 * </ul>
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class ASDL2021Deque<E> implements Deque<E> {

    /*
     * Current number of elements in this deque
     */
    private int size;

    /*
     * Pointer to the first element of the double-linked list used to implement
     * this deque
     */
    private Node<E> first;

    /*
     * Pointer to the last element of the double-linked list used to implement
     * this deque
     */
    private Node<E> last;

    //Variabile che tiene conto del numero di modifiche effettuate alla deque
    //(aggiunta di un elemento, rimozione, sovrascrittura, ...)
    private int edits;

    /**
     * Constructs an empty deque.
     */
    public ASDL2021Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] thisToArray = new Object[size];
        Node<E> currentNode = this.first;
        int i = 0;
        //L'array viene iterato e vengono aggiunti mano
        //a mano i nodi della deque. Si esce dal ciclo quando
        //si incontra il primo nodo null.
        while (currentNode != null) {
            thisToArray[i] = currentNode.item;
            currentNode = currentNode.next;
            i++;
        }
        return thisToArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("La collection non può essere null.");
        for (Object o : c) {
            if (o == null)
                throw new NullPointerException("Valori null non sono ammessi.");
            //Scorro gli elementi della collection specificata e se uno dei suoi elementi
            //non è contenuto in questa deque, ritorno subito false
            if (!this.contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException("La collection non può essere null.");
        //Per ogni elemento e della collection che si vuole aggiungere,
        //esegue un controllo sul valore e poi lo aggiunge.
        for (E e : c) {
            if (e == null)
                throw new NullPointerException("Valori null non sono ammessi.");
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public void clear() {
        while (!this.isEmpty())
            this.remove();
        this.edits++;
    }

    @Override
    public void addFirst(E e) {
        //La capacità di questa collection è illimitata pertanto
        //questo metodo avrà funzionamento analogo al metodo push(E e)
        this.push(e);
    }

    @Override
    public void addLast(E e) {
        //Il funzionamento di questo metodo è equivalente
        //a quello del metodo add(E e)
        this.add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        //La capacità di questa collection è illimitata pertanto
        //questo metodo avrà funzionamento analogo al metodo push(E e)
        this.push(e);
        //L'implementazione di questo metodo prevede il ritorno del valore
        //false in caso di spazio insufficiente ad ospitare un nuovo elemento.
        //Date le caratteristiche di questa Deque, tale situazione non potrà
        //mai verificarsi.
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        //Il funzionamento di questo metodo è equivalente
        //a quello del metodo add(E e)
        this.add(e);
        //L'implementazione di questo metodo prevede il ritorno del valore
        //false in caso di spazio insufficiente ad ospitare un nuovo elemento.
        //Date le caratteristiche di questa Deque, tale situazione non potrà
        //mai verificarsi.
        return true;
    }

    @Override
    public E removeFirst() {
        //Il funzionamento di questo metodo è equivalente
        //a quello del metodo remove()
        return this.remove();
    }

    @Override
    public E removeLast() {
        if (this.size == 0)
            throw new NoSuchElementException("Nessun elemento da rimuovere");
        E removedElement = this.last.item;
        //Se l'elemento da rimuovere è l'unico della deque
        //resetto semplicemente gli indici first e last
        if (this.size == 1){
            this.first = null;
            this.last = null;
        } else {
            //Altrimenti aggiorno solo l'indice last
            //e imposto il suo successore a null
            this.last = this.last.prev;
            this.last.next = null;
        }
        this.size--;
        this.edits++;
        return removedElement;
    }

    @Override
    public E pollFirst() {
        //Il funzionamento di questo metodo è equivalente
        //a quello del metodo poll()
        return this.poll();
    }

    @Override
    public E pollLast() {
        if (this.isEmpty())
            return null;
        E removedElement = this.last.item;
        if (this.size == 1){
            this.first = null;
            this.last = null;
        } else {
            this.last = this.last.prev;
            this.last.next = null;
        }
        this.size--;
        this.edits++;
        return removedElement;
    }

    @Override
    public E getFirst() {
        if (this.isEmpty())
            throw new NoSuchElementException("Nessun elemento disponibile.");
        return this.first.item;
    }

    @Override
    public E getLast() {
        if (this.isEmpty())
            throw new NoSuchElementException("Nessun elemento disponibile.");
        return this.last.item;
    }

    @Override
    public E peekFirst() {
        if (this.isEmpty())
            return null;
        else
            return this.first.item;
    }

    @Override
    public E peekLast() {
        if (this.isEmpty())
            return null;
        else
            return this.last.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException("L'elemento che si sta cercando di inserire è nullo.");
        Node<E> nodeToAdd;
        if (this.isEmpty()){
            //Se la lista è vuota, creo un nuovo nodo senza collegamenti
            // e aggiorno il puntatore al primo nodo della lista
            nodeToAdd = new Node<>(null, e, null);
            this.first = nodeToAdd;
        } else {
            //Se in lista era già presente un nodo, creo un nodo
            //collegato alla coda corrente e, viceversa, collego
            //la coda al nuovo nodo
            nodeToAdd = new Node<>(this.last, e, null);
            this.last.next = nodeToAdd;
        }
        //aggiorno la coda
        this.last = nodeToAdd;
        this.size++;
        this.edits++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        //La capacità di questa collection è illimitata pertanto
        //questo metodo avrà funzionamento analogo al metodo add(E e)
        return this.add(e);
    }

    @Override
    public E remove() {
        if (this.size == 0)
            throw new NoSuchElementException("Nessun elemento da rimuovere.");
        //L'elemento del nodo da rimuovere
        E removedElement = this.first.item;
        if (this.size == 1){
            //Se è l'unico elemento, aggiorno semplicemente i
            //riferimenti al primo e all'ultimo nodo
            this.first = null;
            this.last = null;
        } else {
            //Aggiorno il nodo iniziale
            this.first = this.first.next;
            //Elimino il riferimento al nodo da rimuovere
            this.first.prev = null;
        }
        this.size--;
        this.edits++;
        return removedElement;
    }

    @Override
    public E poll() {
        if (this.isEmpty())
            return null;
        //L'elemento del nodo da rimuovere
        E removedElement = this.first.item;
        if (this.size == 1){
            //Se è l'unico elemento, aggiorno semplicemente i
            //riferimenti al primo e all'ultimo nodo
            this.first = null;
            this.last = null;
        } else {
            //Aggiorno il nodo iniziale
            this.first = this.first.next;
            //Elimino il riferimento al nodo da rimuovere
            this.first.prev = null;
        }
        this.size--;
        this.edits++;
        return removedElement;
    }

    @Override
    public E element() {
        if (this.isEmpty())
            throw new NoSuchElementException("Nessun elemento da rimuovere.");
        return this.first.item;
    }

    @Override
    public E peek() {
        //Il funzionamento di questo metodo è equivalente
        //a quello del metodo peekFirst()
        return this.peekFirst();
    }

    @Override
    public void push(E e) {
        if (e == null)
            throw new NullPointerException("L'elemento che si sta cercando di inserire è nullo.");
        Node<E> nodeToAdd;
        if (this.size == 0) {
            nodeToAdd = new Node<>(null, e, null);
            this.last = nodeToAdd;
        } else {
            nodeToAdd = new Node<>(null, e, this.first);
            this.first.prev = nodeToAdd;
        }
        this.first = nodeToAdd;
        this.size++;
        this.edits++;
    }

    @Override
    public E pop() {
        //Date le caratteristiche di questa Deque, questo metodo
        //ha funzionamento analogo a quello del metodo remove()
        return this.remove();
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            throw new NullPointerException("L'elemento che si sta cercando di rimuovere è nullo.");
        boolean removed = false;
        Node<E> currentNode = this.first;
        if (this.contains(o)){
            //CASO 1: L'ELEMENTO DA RIMUOVERE E' NELL'UNICO NODO DELLA DEQUE
            if (this.size == 1){
                //Inizializzo nuovamente gli indici del primo e dell'ultimo nodo
                this.first = null;
                this.last = null;
                removed = true;
            } else {
                while (!removed){
                    if (Objects.equals(o, currentNode.item)){
                        //CASO 2: L'ELEMENTO DA RIMUOVERE E' NELL'ULTIMO NODO DELLA DEQUE
                        if (currentNode.next == null){
                            //Aggiorno l'indice dell'ultimo nodo
                            this.last = currentNode.prev;
                            //Elimino il collegamento col nodo obsoleto
                            this.last.next = null;
                        }
                        //CASO 3: L'ELEMENTO DA RIMUOVERE E' NEL PRIMO NODO DELLA DEQUE
                        else if (currentNode.prev == null){
                            //Aggiorno l'indice del primo nodo
                            this.first = currentNode.next;
                            //Elimino il collegamento col nodo obsoleto
                            this.first.prev = null;
                        }
                        //CASO 4: L'ELEMENTO DA RIMUOVERE E' IN UN NODO INTERNO DELLA DEQUE
                        else {
                            //Aggiorno il puntatore del nodo precedente facendolo puntare
                            //al nodo successivo a quello obsoleto
                            currentNode.prev.next = currentNode.next;
                            //Aggiorno il puntatore del nodo successivo facendolo puntare
                            //al nodo precedente a quello obsoleto
                            currentNode.next.prev = currentNode.prev;
                        }
                        removed = true;
                    }
                    currentNode = currentNode.next;
                }
            }
            size--;
            this.edits++;
        }
        return removed;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException("L'elemento che si sta cercando è nullo.");
        boolean contained = false;
        Node<E> currentNode = this.first;
        while (currentNode != null && !contained) {
            if (o.equals(currentNode.item))
                contained = true;
            else
                currentNode = currentNode.next;
        }
        return contained;
    }

    @Override
    public int size() {
        return this.size;
    }

    /*
     * Class for representing the nodes of the double-linked list used to
     * implement this deque. The class and its members/methods are protected
     * instead of private only for JUnit testing purposes.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

        protected Node<E> prev;

        protected Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /*
     * Class for implementing an iterator for this deque. The iterator is
     * fail-safe: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class Itr implements Iterator<E> {
        private Node<E> cursor;
        private int expectedEdits;

        Itr() {
            this.cursor = null;
            this.expectedEdits = ASDL2021Deque.this.edits;
        }

        public boolean hasNext() {
            if(cursor == null)
                return !ASDL2021Deque.this.isEmpty();
            return this.cursor.next != null;
        }

        public E next() {
            if (this.expectedEdits < edits)
                throw new ConcurrentModificationException("La deque è stata modificata durante l'iterazione.");
            if (!this.hasNext())
                throw new NoSuchElementException("Non esiste un elemento successivo.");
            //Primo utilizzo: il cursore è a null, quindi
            // lo posiziono sul primo elemento della deque
            if (cursor == null)
                cursor = ASDL2021Deque.this.first;
            //Il cursore è già posizionato su un elemento,
                // pertanto lo sposto sul successivo
            else
                cursor = cursor.next;
            return cursor.item;
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescItr();
    }

    /*
     * Class for implementing a descending iterator for this deque. The iterator
     * is fail-safe: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class DescItr implements Iterator<E> {
        private Node<E> cursor;
        private int expectedEdits;

        DescItr() {
            this.cursor = null;
            this.expectedEdits = ASDL2021Deque.this.edits;
        }

        public boolean hasNext() {
            if(cursor == null)
                return !ASDL2021Deque.this.isEmpty();
            return this.cursor.prev != null;
        }

        public E next() {
            if (this.expectedEdits < edits)
                throw new ConcurrentModificationException("La deque è stata modificata durante l'iterazione.");
            if (!this.hasNext())
                throw new NoSuchElementException("Non esiste un elemento successivo.");
            if (cursor == null)
                cursor = ASDL2021Deque.this.last;
            else
                cursor = cursor.prev;
            return cursor.item;
        }

    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getFirstNode() {
        return this.first;
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getLastNode() {
        return this.last;
    }

}
