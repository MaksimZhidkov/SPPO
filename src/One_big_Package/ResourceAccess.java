package One_big_Package;

//Вариант 4 Предложите систему команд и реализуйте консольную утилиту,
// позволяющую создавать графовые структуры, добавлять узлы и связи,
// причем каждый узел определяется именем и описанием,
// а каждая связь — именем узла источника,
// именем узла приемника, описанием отношения.
// Полученный граф необходимо распечатать в консоли,
// в форматах матриц смежности и инцидентности.

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

public class ResourceAccess {
    private static final int MAX_CONCURRENT_THREADS = 2;
    private final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_THREADS);

    public void adjacencyMatrixReaders() {
        try {
            semaphore.acquire();
            // Критическая секция: доступ к ресурсу
            System.out.println(Thread.currentThread().getName() + " Вошел в комнату");
            System.out.println(Thread.currentThread().getName() + " Читает граф");
            Graph graph = Graph.getGraph();
            graph.createAdjacencyMatrix();

            Thread.sleep(5000); // Симуляция работы с ресурсом
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " ушел");
        }
    }

    public void identityMatrixReaders() {
        try {
            semaphore.acquire();
            // Критическая секция: доступ к ресурсу
            System.out.println(Thread.currentThread().getName() + " Вошел в комнату");
            System.out.println(Thread.currentThread().getName() + " Читает граф");
            Graph graph = Graph.getGraph();
            graph.createIdentityMatrix();

            Thread.sleep(5000); // Симуляция работы с ресурсом
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " ушел");
        }
    }

    public void nodeWriter() {
        try {
            semaphore.acquire(2);
            // Критическая секция: доступ к ресурсу
            System.out.println(Thread.currentThread().getName() + " Вошел в комнату");
            System.out.println(Thread.currentThread().getName() + " Создаёт список узлов");
            Node node;
            Graph graph = Graph.getGraph();
            for (int i = 1; i<6;i++){
            node =new   Node("A"+i,"some_description_node");
            graph.addNode(node);
            node =new   Node("B"+i,"some_description_node");
            graph.addNode(node);
            }
            Thread.sleep(2000); // Симуляция работы с ресурсом
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(2);
            System.out.println(Thread.currentThread().getName() + " ушел");
        }
    }

    public void edgeWriter() {
        try {
            semaphore.acquire(2);
            // Критическая секция: доступ к ресурсу
            System.out.println(Thread.currentThread().getName() + " Вошел в комнату");
            System.out.println(Thread.currentThread().getName() + " Создаёт список связей");

            Edge edge;
            Graph graph = Graph.getGraph();
            for (int i=1;i<6; i++){
                edge = new Edge("A"+i,"B"+i,"some_description");
                graph.addEgede(edge);
            }
            for (int i=1;i<5; i++){
                edge = new Edge("A"+i,"A"+(i+1),"some_description");
                graph.addEgede(edge);
                edge = new Edge("B"+i,"B"+(i+1),"some_description");
                graph.addEgede(edge);
            }
            edge = new Edge("A5","A1","some_description");
            graph.addEgede(edge);
            edge = new Edge("B5","B1","some_description");
            graph.addEgede(edge);

            Thread.sleep(2000); // Симуляция работы с ресурсом
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(2);
            System.out.println(Thread.currentThread().getName() + " ушел");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        One_big_Package.ResourceAccess example = new One_big_Package.ResourceAccess();

        Thread thread1 = new Thread(example::nodeWriter, "Писатель узлов");
        Thread thread2 = new Thread(example::edgeWriter, "Писатель связей");
        Thread thread3 = new Thread(example::adjacencyMatrixReaders, "Читатель смежности");
        Thread thread4 = new Thread(example::identityMatrixReaders, "Читатель инцидентности");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        Graph graph = Graph.getGraph();
        graph.printMatrix();

    }
}