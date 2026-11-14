
public class SinglyLinkedList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Menambah data di awal
    public void addFirst(T data) {
        Node baru = new Node(data);

        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            baru.next = head;
            head = baru;
        }

        size++;
    }

    // Menambah data di akhir
    public void addLast(T data) {
        Node baru = new Node(data);

        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            tail.next = baru;
            tail = baru;
        }

        size++;
    }

    // Menambah data pada posisi tertentu
    public void insert(int posisi, T data) {
        if (posisi < 0 || posisi > size) {
            throw new IndexOutOfBoundsException("Posisi tidak valid");
        }

        if (posisi == 0) {
            addFirst(data);
            return;
        }

        if (posisi == size) {
            addLast(data);
            return;
        }

        Node bantu = head;

        for (int i = 0; i < posisi - 1; i++) {
            bantu = bantu.next;
        }

        Node baru = new Node(data);
        baru.next = bantu.next;
        bantu.next = baru;
        size++;
    }

    // Mengambil data
    public T get(int posisi) {
        if (posisi < 0 || posisi >= size) {
            throw new IndexOutOfBoundsException("Posisi tidak valid");
        }

        Node bantu = head;

        for (int i = 0; i < posisi; i++) {
            bantu = bantu.next;
        }

        return bantu.data;
    }

    // Menghapus data
    public T remove(int posisi) {
        if (posisi < 0 || posisi >= size) {
            throw new IndexOutOfBoundsException("Posisi tidak valid");
        }

        Node hapus;

        if (posisi == 0) {
            hapus = head;
            head = head.next;

            if (head == null) {
                tail = null;
            }
        } else {
            Node bantu = head;

            for (int i = 0; i < posisi - 1; i++) {
                bantu = bantu.next;
            }

            hapus = bantu.next;
            bantu.next = hapus.next;

            if (hapus == tail) {
                tail = bantu;
            }
        }

        size--;
        return hapus.data;
    }

    // Mencari data dan menghitung langkah
    public int search(T data) {
        Node bantu = head;
        int langkah = 0;

        while (bantu != null) {
            langkah++;

            if (bantu.data.equals(data)) {
                return langkah;
            }

            bantu = bantu.next;
        }

        return -langkah;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Memeriksa kondisi Linked List
    public boolean validateInvariant() {
        if (size == 0) {
            return head == null && tail == null;
        }

        if (head == null || tail == null || tail.next != null) {
            return false;
        }

        Node bantu = head;
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
