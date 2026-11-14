
public class FilterPutusan {

    public static SinglyLinkedList<Putusan> berdasarkanJenis(
            SinglyLinkedList<Putusan> data, String jenis) {

        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            Putusan p = data.get(i);

            if (p.getJenisNarkotika() != null &&
                    p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.addLast(p);
            }
        }

        return hasil;
    }

    public static SinglyLinkedList<Putusan> berdasarkanBerat(
            SinglyLinkedList<Putusan> data,
            double minimum, double maksimum) {

        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            Putusan p = data.get(i);
            double berat = p.getBeratBarangBuktiGram();

            if (berat >= minimum && berat <= maksimum) {
                hasil.addLast(p);
            }
        }

        return hasil;
    }

    public static SinglyLinkedList<Putusan> berdasarkanPasal(
            SinglyLinkedList<Putusan> data, String pasal) {

        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            Putusan p = data.get(i);

            if (p.getPasalTerbukti() != null &&
                    p.getPasalTerbukti().equalsIgnoreCase(pasal)) {
                hasil.addLast(p);
            }
        }

        return hasil;
    }

    public static SinglyLinkedList<Putusan> berdasarkanVonis(
            SinglyLinkedList<Putusan> data,
            int minimum, int maksimum) {

        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        for (int i = 0; i < data.size(); i++) {
            Putusan p = data.get(i);
            int vonis = p.getVonisBulan();

            if (vonis >= minimum && vonis <= maksimum) {
                hasil.addLast(p);
            }
        }

        return hasil;
    }
}

