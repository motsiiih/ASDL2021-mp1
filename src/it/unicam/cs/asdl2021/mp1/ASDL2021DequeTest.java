package it.unicam.cs.asdl2021.mp1;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ASDL2021DequeTest<E> {

    @Test
    void ASDL2021Deque() {
        ASDL2021Deque<E> deque = new ASDL2021Deque<>();
        assertEquals(deque.size(), 0);
        assertNull(deque.getFirstNode());
        assertNull(deque.getLastNode());
    }

    @Test
    void isEmpty() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        assertEquals(deque.size(), 0);
        deque.add(i);
        assertNotEquals(deque.size(), 0);
    }

    @Test
    void toArray() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertEquals(deque.size(), 0);
        deque.add(i);
        assertEquals(deque.size(), 1);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), i);
        deque.add(j);
        assertEquals(deque.size(), 2);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), j);
        Object[] dequeArray = deque.toArray();
        assertEquals(dequeArray[0], i);
        assertEquals(dequeArray[1], j);
        assertEquals(dequeArray.length, deque.size());
    }

    @Test
    void containsAll() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        String n = "Stringa 3";
        Collection<String> c1 = new ASDL2021Deque<>();
        Collection<String> c2 = new LinkedList<>();
        Collection<String> c3 = null;
        c1.add(i);
        c1.add(j);
        c2.add(null);
        assertThrows(NullPointerException.class, () -> {
            deque.containsAll(c2);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.containsAll(c3);
        });
        assertEquals(deque.size(), 0);
        assertFalse(deque.contains(i));
        assertFalse(deque.contains(j));
        assertFalse(deque.contains(n));
        assertTrue(c1.contains(i));
        assertTrue(c1.contains(j));
        assertFalse(c1.contains(n));
        assertFalse(deque.containsAll(c1));
        deque.add(i);
        assertEquals(deque.size(), 1);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), i);
        assertTrue(deque.contains(i));
        assertFalse(deque.contains(j));
        assertFalse(deque.contains(n));
        assertFalse(deque.containsAll(c1));
        deque.add(j);
        assertEquals(deque.size(), 2);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), j);
        assertTrue(deque.contains(i));
        assertTrue(deque.contains(j));
        assertFalse(deque.contains(n));
        assertTrue(deque.containsAll(c1));
    }

    @Test
    void addAll() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        String n = "Stringa 3";
        Collection<String> c1 = new ASDL2021Deque<>();
        Collection<String> c2 = new LinkedList<>();
        Collection<String> c3 = null;
        c1.add(j);
        c1.add(n);
        c2.add(null);
        assertThrows(NullPointerException.class, () -> {
            deque.addAll(c2);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.addAll(c3);
        });
        assertFalse(c1.contains(i));
        assertTrue(c1.contains(j));
        assertTrue(c1.contains(n));
        assertEquals(deque.size(), 0);
        assertFalse(deque.contains(i));
        assertFalse(deque.contains(j));
        assertFalse(deque.contains(n));
        deque.addAll(c1);
        assertEquals(deque.size(), 2);
        assertFalse(deque.contains(i));
        assertTrue(deque.contains(j));
        assertTrue(deque.contains(n));
    }

    @Test
    void clear() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertEquals(deque.size(), 0);
        deque.add(i);
        assertEquals(deque.size(), 1);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), i);
        deque.add(j);
        assertEquals(deque.size(), 2);
        assertSame(deque.getFirst(), i);
        assertSame(deque.getLast(), j);
        deque.clear();
        assertEquals(deque.size(), 0);
        assertNull(deque.getFirstNode());
        assertNull(deque.getLastNode());
    }

    @Test
    void addFirst() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertThrows(NullPointerException.class, () -> {
            deque.addFirst(null);
        });
        deque.addFirst(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.addFirst(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == i);
    }

    @Test
    void addLast() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertThrows(NullPointerException.class, () -> {
            deque.addLast(null);
        });
        deque.addLast(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.addLast(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
    }

    @Test
    void offerFirst() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertThrows(NullPointerException.class, () -> {
            deque.offerFirst(null);
        });
        boolean a = deque.offerFirst(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        boolean b = deque.offerFirst(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == i);
        assertTrue(a);
        assertTrue(b);
    }

    @Test
    void offerLast() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertThrows(NullPointerException.class, () -> {
            deque.offerLast(null);
        });
        boolean a = deque.offerLast(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        boolean b = deque.offerLast(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        assertTrue(a);
        assertTrue(b);
    }

    @Test
    void removeFirst() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.removeFirst();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.removeFirst();
        assertTrue(deque.size() == 0);
        assertTrue(deque.getFirstNode() == null);
        assertTrue(deque.getLastNode() == null);
        assertThrows(NoSuchElementException.class, () -> {
            deque.removeFirst();
        });
    }

    @Test
    void removeLast() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.removeLast();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.removeLast();
        assertTrue(deque.size() == 0);
        assertNull(deque.getFirstNode());
        assertNull(deque.getLastNode());
        assertThrows(NoSuchElementException.class, () -> {
            deque.removeLast();
        });
    }

    @Test
    void pollFirst() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertNull(deque.pollFirst());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.pollFirst();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.pollFirst();
        assertTrue(deque.size() == 0);
        assertNull(deque.getFirstNode());
        assertNull(deque.getLastNode());
        assertNull(deque.pollFirst());
    }

    @Test
    void pollLast() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertNull(deque.pollLast());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.pollLast();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.pollLast();
        assertTrue(deque.size() == 0);
        assertNull(deque.getFirstNode());
        assertNull(deque.getLastNode());
        assertNull(deque.pollLast());
    }

    @Test
    void getFirst() {
        ASDL2021Deque<Integer> deque = new ASDL2021Deque<>();
        assertThrows(NoSuchElementException.class, () -> {
            deque.getFirst();
        });
    }

    @Test
    void getLast() {
        ASDL2021Deque<Integer> deque = new ASDL2021Deque<>();
        assertThrows(NoSuchElementException.class, () -> {
            deque.getLast();
        });
    }

    @Test
    void peekFirst() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        assertTrue(deque.size() == 0);
        assertNull(deque.peekFirst());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        assertEquals(deque.peekFirst(), i);
    }

    @Test
    void peekLast() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertEquals(deque.size(), 0);
        assertNull(deque.peekLast());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        assertEquals(deque.peekLast(), i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        assertEquals(deque.peekLast(), j);
    }

    @Test
    void add() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NullPointerException.class, () -> {
            deque.add(null);
        });;
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
    }

    @Test
    void offer() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NullPointerException.class, () -> {
            deque.offer(null);
        });;
        assertTrue(deque.size() == 0);
        deque.offer(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.offer(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
    }

    @Test
    void remove() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NoSuchElementException.class, () -> {
            deque.remove();
        });
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.remove();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.remove();
        assertTrue(deque.size() == 0);
        assertTrue(deque.getFirstNode() == null);
        assertTrue(deque.getLastNode() == null);
    }

    @Test
    void poll() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertTrue(deque.size() == 0);
        assertNull(deque.poll());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.poll();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.poll();
        assertTrue(deque.size() == 0);
        assertTrue(deque.getFirstNode() == null);
        assertTrue(deque.getLastNode() == null);
        assertNull(deque.poll());
    }

    @Test
    void element() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        assertThrows(NoSuchElementException.class, () -> {
            deque.element();
        });
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        assertEquals(deque.element(), i);
    }

    @Test
    void peek() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        assertTrue(deque.size() == 0);
        assertNull(deque.peek());
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        assertEquals(deque.peek(), i);
    }

    @Test
    void push() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NullPointerException.class, () -> {
            deque.push(null);
        });;
        assertTrue(deque.size() == 0);
        deque.push(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.push(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == i);
    }

    @Test
    void pop() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NoSuchElementException.class, () -> {
            deque.pop();
        });
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.pop();
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.pop();
        assertTrue(deque.size() == 0);
        assertTrue(deque.getFirstNode() == null);
        assertTrue(deque.getLastNode() == null);
    }

    @Test
    void testRemove() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        String n = "Stringa 3";
        String m = "Stringa 4";
        assertThrows(NullPointerException.class, () -> {
            deque.remove(null);
        });
        assertTrue(deque.size() == 0);
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.add(n);
        assertTrue(deque.size() == 3);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == n);
        deque.remove(n);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        deque.remove(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == j);
        assertTrue(deque.getLast() == j);
        deque.remove(j);
        assertTrue(deque.size() == 0);
        assertTrue(deque.getFirstNode() == null);
        assertTrue(deque.getLastNode() == null);
        assertFalse(deque.remove(m));
    }

    @Test
    void contains() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        assertThrows(NullPointerException.class, () -> {
            deque.contains(null);
        });
        assertTrue(deque.size() == 0);
        assertFalse(deque.contains(i));
        assertFalse(deque.contains(j));
        deque.add(i);
        assertTrue(deque.size() == 1);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == i);
        assertTrue(deque.contains(i));
        assertFalse(deque.contains(j));
        deque.add(j);
        assertTrue(deque.size() == 2);
        assertTrue(deque.getFirst() == i);
        assertTrue(deque.getLast() == j);
        assertTrue(deque.contains(i));
        assertTrue(deque.contains(j));
        deque.remove(i);
        assertFalse(deque.contains(i));
        assertTrue(deque.contains(j));
    }

    @Test
    void iterator() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        deque.add(i);
        deque.add(j);
        Iterator<String> it = deque.iterator();
        assertTrue(it.hasNext());
        String ii = it.next();
        assertEquals(ii, i);
        assertTrue(it.hasNext());
        String jj = it.next();
        assertEquals(jj, j);
        assertFalse(it.hasNext());
    }

    @Test
    void descendingIterator() {
        ASDL2021Deque<String> deque = new ASDL2021Deque<>();
        String i = "Stringa 1";
        String j = "Stringa 2";
        deque.add(i);
        deque.add(j);
        Iterator<String> it = deque.descendingIterator();
        assertTrue(it.hasNext());
        String ii = it.next();
        assertEquals(ii, j);
        assertTrue(it.hasNext());
        String jj = it.next();
        assertEquals(jj, i);
        assertFalse(it.hasNext());
    }
}