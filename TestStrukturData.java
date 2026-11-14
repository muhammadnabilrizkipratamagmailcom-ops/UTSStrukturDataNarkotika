
public class TestStrukturData {

    public static void main(String[] args) {

        System.out.println("===== TES SINGLY LINKED LIST =====");

        SinglyLinkedList<String> daftar = new SinglyLinkedList<>();

        daftar.addLast("Putusan 001");
        daftar.addLast("Putusan 002");
        daftar.addLast("Putusan 003");

        System.out.println("Jumlah data: " + daftar.size());
        System.out.println("Data pertama: " + daftar.get(0));
        System.out.println("Data terakhir: " + daftar.get(2));

        daftar.remove(1);

        System.out.println("Setelah menghapus data kedua:");
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println(daftar.get(i));
        }

        System.out.println("Jumlah data sekarang: " + daftar.size());
        System.out.println("Linked List valid: " + daftar.validateInvariant());

        System.out.println("\n===== TES STACK =====");

        Stack<String> riwayat = new Stack<>();

        riwayat.push("Tambah data");
        riwayat.push("Hapus data");
        riwayat.push("Cari data");

        System.out.println("Jumlah aktivitas: " + riwayat.size());
        System.out.println("Aktivitas terbaru: " + riwayat.peek());
        System.out.println("Stack valid: " + riwayat.validateInvariant());

        System.out.println("\n===== TES SELESAI =====");
    }
}
