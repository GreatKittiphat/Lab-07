package ku.cs.lab03.services;

public interface Datasource<T> {
    T readData();
    void writeData(T data);
}
