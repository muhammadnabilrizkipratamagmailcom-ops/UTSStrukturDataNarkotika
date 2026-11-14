
public class Stack<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(T data) {
        Node baru = new Node(data);
        baru.next = top;
        top = baru;
        size++;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        T data = top.data;
        top = top.next;
        size--;

        return data;
    }

    public T peek() {
        if (top == null) {
            return null;
        }

        return top.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean validateInvariant() {
        Node bantu = top;
        int jumlah = 0;

        while (bantu != null) {
            jumlah++;

            if (jumlah > size) {
                return false;
            }

            bantu = bantu.next;
        }

        return jumlah == size;
    }
}
