package it.unicam.cs.asdl2021.mp1;

import it.unicam.cs.asdl2021.mp1.Job;
import it.unicam.cs.asdl2021.mp1.TernaryHeapMinPriorityQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TernaryHeapMinPriorityQueueTest {


    @Test
    void insert() {
        TernaryHeapMinPriorityQueue minPriorityQueue = new TernaryHeapMinPriorityQueue();
        Job job1 = new Job("job1", 5);
        Job job2 = new Job("job2", 0);
        Job job3 = new Job("job3", 3);
        Job job4 = new Job("job4", 15);
        Job job5 = new Job("job5", 8);
        minPriorityQueue.insert(job1);
        minPriorityQueue.insert(job2);
        minPriorityQueue.insert(job3);
        minPriorityQueue.insert(job4);
        minPriorityQueue.insert(job5);
        assertEquals(minPriorityQueue.size(), 5);
        assertEquals(job1, minPriorityQueue.getTernaryHeap().get(1));
        assertEquals(job1.getHandle(), 1);
        assertEquals(job2, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job2.getHandle(), 0);
        assertEquals(job3, minPriorityQueue.getTernaryHeap().get(2));
        assertEquals(job3.getHandle(), 2);
        assertEquals(job4, minPriorityQueue.getTernaryHeap().get(3));
        assertEquals(job4.getHandle(), 3);
        assertEquals(job5, minPriorityQueue.getTernaryHeap().get(4));
        assertEquals(job5.getHandle(), 4);

        //caso 1 - un solo nodo da inserire
        Job job6 = new Job("job1", 8);
        minPriorityQueue.insert(job6);
        //assertTrue(job6.getHandle() == 0);

        //caso 2 - aggiunta di un figlio sinistro con priorità minore del padre
        Job job7 = new Job("job2", 7);
        minPriorityQueue.insert(job7);
        assertTrue(job7.getHandle() == 0);
        assertTrue(job6.getHandle() == 1);

        //caso 3 - aggiunta di un figlio destro con priorità minore del padre
        Job job8 = new Job("job3", 6);
        minPriorityQueue.insert(job8);
        assertTrue(job7.getHandle() == 2);
        assertTrue(job6.getHandle() == 1);
        assertTrue(job8.getHandle() == 0);

        //caso 4 - aggiungo un figlio, con priorità maggiore rispetto al padre, per completare l albero
        Job job9 = new Job("job4", 9);
        minPriorityQueue.insert(job9);
        assertTrue(job7.getHandle() == 2);
        assertTrue(job6.getHandle() == 1);
        assertTrue(job8.getHandle() == 0);
        assertTrue(job9.getHandle() == 3);

        //caso 5 - terzo livello. aggiunta di un figlio sinistro con priorità maggiore del padre.
        Job job10 = new Job("job5", 9);
        minPriorityQueue.insert(job10);
        assertTrue(job7.getHandle() == 2);
        assertTrue(job6.getHandle() == 1);
        assertTrue(job8.getHandle() == 0);
        assertTrue(job9.getHandle() == 3);
        assertTrue(job10.getHandle() == 4);

        //caso 6 - terzo livello. aggiunta di un figlio destro con priorità minore del padre.
        Job job11 = new Job("job6", 5);
        minPriorityQueue.insert(job11);
        assertTrue(job7.getHandle() == 2);
        assertTrue(job11.getHandle() == 5);
        assertTrue(job8.getHandle() == 1);
        assertTrue(job9.getHandle() == 3);
        assertTrue(job10.getHandle() == 4);
        assertTrue(job11.getHandle() == 0);
    }

    @Test
    void minimum() {
        TernaryHeapMinPriorityQueue minPriorityQueue = new TernaryHeapMinPriorityQueue();
        Job job1 = new Job("job1", 5);
        Job job2 = new Job("job2", 0);
        Job job3 = new Job("job3", 3);
        Job job4 = new Job("job4", 15);
        Job job5 = new Job("job5", 8);
        minPriorityQueue.insert(job1);
        minPriorityQueue.insert(job2);
        minPriorityQueue.insert(job3);
        minPriorityQueue.insert(job4);
        minPriorityQueue.insert(job5);
        assertEquals(job2, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job2, minPriorityQueue.minimum());
    }

    @Test
    void extractMinimum() {
        TernaryHeapMinPriorityQueue minPriorityQueue = new TernaryHeapMinPriorityQueue();
        Job job1 = new Job("job1", 5);
        Job job2 = new Job("job2", 0);
        Job job3 = new Job("job3", 3);
        Job job4 = new Job("job4", 15);
        Job job5 = new Job("job5", 8);
        minPriorityQueue.insert(job1);
        minPriorityQueue.insert(job2);
        minPriorityQueue.insert(job3);
        minPriorityQueue.insert(job4);
        minPriorityQueue.insert(job5);
        assertEquals(minPriorityQueue.size(), 5);
        assertEquals(job2, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job2, minPriorityQueue.extractMinimum());
        assertEquals(minPriorityQueue.size(), 4);
        assertEquals(job3, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job3, minPriorityQueue.extractMinimum());
        assertEquals(minPriorityQueue.size(), 3);
        assertEquals(job1, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job1, minPriorityQueue.extractMinimum());
        assertEquals(minPriorityQueue.size(), 2);
        assertEquals(job5, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job5, minPriorityQueue.extractMinimum());
        assertEquals(minPriorityQueue.size(), 1);
        assertEquals(job4, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job4, minPriorityQueue.extractMinimum());
        assertEquals(minPriorityQueue.size(), 0);
    }

    @Test
    void decreasePriority() {
        TernaryHeapMinPriorityQueue minPriorityQueue = new TernaryHeapMinPriorityQueue();
        Job job1 = new Job("job1", 5);
        Job job4 = new Job("job4", 15);
        Job job5 = new Job("job5", 8);
        minPriorityQueue.insert(job1);
        minPriorityQueue.insert(job4);
        minPriorityQueue.insert(job5);
        assertEquals(job1, minPriorityQueue.minimum());
        assertEquals(job1, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job1.getHandle(), 0);
        assertEquals(job1.getPriority(), 5);
        minPriorityQueue.decreasePriority(job5, 3);
        assertEquals(job5, minPriorityQueue.minimum());
        assertEquals(job5, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job5.getHandle(), 0);
        assertEquals(job5.getPriority(), 3);
        minPriorityQueue.decreasePriority(job4, 0);
        assertEquals(job4, minPriorityQueue.minimum());
        assertEquals(job4, minPriorityQueue.getTernaryHeap().get(0));
        assertEquals(job4.getHandle(), 0);
        assertEquals(job4.getPriority(), 0);
    }
}