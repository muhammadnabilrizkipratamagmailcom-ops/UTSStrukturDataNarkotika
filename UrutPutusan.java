
public class UrutPutusan {

    public static SinglyLinkedList<Putusan> urutBerdasarkanVonis(
            SinglyLinkedList<Putusan> data) {

        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        // Salin data asli
        for (int i = 0; i < data.size(); i++) {
            hasil.addLast(data.get(i));
        }

        // Selection Sort: vonis terbesar ke terkecil
        for (int i = 0; i < hasil.size() - 1; i++) {

            int indexTerbesar = i;

            for (int j = i + 1; j < hasil.size(); j++) {
                if (hasil.get(j).getVonisBulan() >
                        hasil.get(indexTerbesar).getVonisBulan()) {
                    indexTerbesar = j;
                }
            }

            if (indexTerbesar != i) {
                Putusan terbesar = hasil.get(indexTerbesar);
                Putusan sementara = hasil.get(i);

                hasil.remove(indexTerbesar);
                hasil.remove(i);

                hasil.insert(i, terbesar);
                hasil.insert(indexTerbesar, sementara);
            }
        }

        return hasil;
    }
}
