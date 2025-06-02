package Kurva_ja_Perdolle;

import java.util.concurrent.locks.*;
import java.util.ArrayList;
import java.util.List;

public class ReadersWritersExclusiveWriter {
    private final Lock lock = new ReentrantLock();
    private final Condition canRead = lock.newCondition();
    private final Condition canWrite = lock.newCondition();

    private int activeReaders = 0;
    private int activeWriters = 0; // 0 или 1, чтобы обеспечить только одного писателя

    // Метод для начала записи (писателя)
    public void startWrite() throws InterruptedException {
        lock.lock();
        try {
            // Ждем, пока не будет свободен доступ (нет активных читателей или писателей)
            while (activeReaders > 0 || activeWriters > 0) {
                canWrite.await();
            }
            // Теперь можем писать
            activeWriters++;
        } finally {
            lock.unlock();
        }
    }

    // Метод для завершения записи
    public void endWrite() {
        lock.lock();
        try {
            activeWriters--;
            // После завершения пишущего потока разрешаем чтение и другим писать
            canRead.signalAll();
            canWrite.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // Метод для начала чтения
    public void startRead() throws InterruptedException {
        lock.lock();
        try {
            // Читатели могут читать только когда нет активных писателей
            while (activeWriters > 0) {
                canRead.await();
            }
            activeReaders++;
        } finally {
            lock.unlock();
        }
    }

    // Метод для завершения чтения
    public void endRead() {
        lock.lock();
        try {
            activeReaders--;
            if (activeReaders == 0) {
                // Если все читатели ушли, разрешаем писать
                canWrite.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    // Поток писателя
    class Writer implements Runnable {
        private final int id;

        public Writer(int id) { this.id = id; }

        @Override
        public void run() {
            try {
                System.out.println("Писатель " + id + " пытается начать писать");
                startWrite(); // ждет, пока не сможет начать писать
                System.out.println("Писатель " + id + " начал писать");
                Thread.sleep(1000 + (int)(Math.random() * 1000)); // имитация работы
                System.out.println("Писатель " + id + " закончил писать");
                endWrite(); // освобождает ресурс
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    // Поток читателя
    class Reader implements Runnable {
        private final int id;

        public Reader(int id) { this.id = id; }

        @Override
        public void run() {
            try {
                System.out.println("Читатель " + id + " пытается начать читать");
                startRead(); // ждет, пока не сможет читать
                System.out.println("Читатель " + id + " начал читать");
                Thread.sleep(500 + (int)(Math.random() * 500)); // имитация работы
                System.out.println("Читатель " + id + " закончил читать");
                endRead(); // освобождает ресурс
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReadersWritersExclusiveWriter system = new ReadersWritersExclusiveWriter();

        int numWriters = 2;
        int numReaders = 2;

        List<Thread> threads = new ArrayList<>();

        // Создаем и запускаем всех писателей сначала
        for (int i=1; i<=numWriters; i++) {
            Thread t = new Thread(system.new Writer(i));
            threads.add(t);
            t.start();
        }

        // Ждем завершения всех писателей
        for (Thread t : threads) t.join();

        System.out.println("Все писатели завершили работу. Теперь читаем.");

        threads.clear();

        // Создаем и запускаем всех читателей после писателей
        for (int i=1; i<=numReaders; i++) {
            Thread t = new Thread(system.new Reader(i));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Все читатели завершили работу.");
    }
}