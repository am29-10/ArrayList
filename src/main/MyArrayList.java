package main;

import java.util.Comparator;

/**
 * Реализация интерфейса main.MyList с изменяемым размером массива. В дополнение к реализации интерфейса List этот класс
 * предоставляет методы для управления размером массива, который используется внутри для хранения списка.
 * @param <T> - тип элементов в данном списке.
 */
public class MyArrayList<T> implements MyList<T> {

    /**
     * Начальная емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Пустой массив.
     */
    private Object[] array;

    /**
     * Размер списка main.MyArrayList (количество содержащихся в нем элементов).
     */
    private int size;

    /**
     * Создает пустой список с начальной емкостью равной 10.
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой список с начальной емкостью указанной в качестве аргумента.
     * @param initialCapacity - начальная емкость списка.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            array = new Object[0];
        } else {
            throw new IllegalArgumentException("Неккоректное значение: "+
                    initialCapacity);
        }
    }

    /**
     * Добавляет указанный элемент в конец этого списка.
     * @param e - элемент, который будет добавлен к данному списку.
     * @return возвращает true, если эта коллекция изменилась в результате вызова.
     */
    @Override
    public boolean add(T e) {
        if (size == array.length) {
            grow();
        }
        array[size] = e;
        size++;
        return true;
    }

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     * @param index - индекс возвращаемого элемента.
     * @return возвращает элемент в указанной позиции в этом списке.
     */
    @Override
    public T get(int index) {
        return (T) array[index];
    }

    /**
     * Удаляет элемент из списка по указанному индексу.
     * @param index - индекс элемента, который нужно удалить из этого списка, если он присутствует.
     * @return возвращает true, если этот список содержит элемент с указанным индексом.
     */
    @Override
    public boolean remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = null;
        for (int i = index + 1; i < array.length - 1; i++) {
            array[i - 1] = array[i];
            array[i] = null;
        }
        System.arraycopy(array, 0, array, 0, array.length - 1);
        size--;
        return true;
    }

    /**
     * Удаляет все элементы из этого списка. Список будет пуст после возврата этого вызова.
     */
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Позволяет сортировать объекты в списке.
     * @param c - Comparator, используемый для сравнения элементов списка.
     */
    @Override
    public void sort(Comparator<? super T> c) {
        quickSort(c, 0, size - 1);
    }

    /**
     * Вставляет указанный элемент в указанную позицию в этом списке. Сдвигает элемент, находящийся в данный момент
     * в этой позиции (если есть), и любые последующие элементы вправо (добавляет единицу к их индексам).
     * @param index - индекс, по которому указанный элемент должен быть вставлен.
     * @param element - элемент, который будет добавлен к данному списку.
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Возвращает количество элементов в этом списке. Если этот список содержит больше элементов, чем Integer.MAX_VALUE,
     * то возвращает Integer.MAX_VALUE .
     * @return возвращает количество элементов в этом списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает true если этот список не содержит элементов.
     * @return возвращает true если этот список не содержит элементов.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Увеличивает массив в полтора раза, сохраняя все имеющиеся элементы.
     */
    private void grow() {
        int newCapacity = array.length + array.length/2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Позволяет производить "быструю сортировку" элементов в списке.
     * @param c - компаратор.
     * @param indexStart - индекс элемента в начале сортируемого массива.
     * @param indexEnd - индекс элемента в конце сортируемого массива.
     */
    private void quickSort(Comparator<? super T> c, int indexStart, int indexEnd) {

        int wallIndex = indexStart;
        T pivotValue = (T) array[indexEnd];

        for (int i = indexStart; i < indexEnd; i++) {
            if (c.compare((T) array[i], pivotValue) <= 0) {
                Object temp = array[i];
                array[i] = array[wallIndex];
                array[wallIndex] = temp;
                wallIndex++;
            }
        }

        Object temp = array[wallIndex];
        array[wallIndex] = array[indexEnd];
        array[indexEnd] = temp;

        if (wallIndex - 1 - indexStart > 0 ) {
            quickSort(c, indexStart, wallIndex -1 );
        }
        if (indexEnd - wallIndex - 1 > 0) {
            quickSort(c, wallIndex + 1, indexEnd);
        }
    }

}
