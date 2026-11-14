
public class StatistikPutusan {

    public static void tampilkanDistribusiJenis(
            SinglyLinkedList<Putusan> data) {

        if (data.isEmpty()) {
            System.out.println("Belum ada data untuk dihitung.");
            return;
        }

        System.out.println("\n===== DISTRIBUSI JENIS NARKOTIKA =====");

        for (int i = 0; i < data.size(); i++) {

            String jenis = data.get(i).getJenisNarkotika();

            if (jenis == null || jenis.trim().isEmpty()) {
                jenis = "Tidak diketahui";
            }

            boolean sudahDihitung = false;

            // Periksa apakah jenis sudah muncul sebelumnya
            for (int j = 0; j < i; j++) {

                String jenisSebelumnya =
                        data.get(j).getJenisNarkotika();

                if (jenisSebelumnya == null ||
                        jenisSebelumnya.trim().isEmpty()) {
                    jenisSebelumnya = "Tidak diketahui";
                }

                if (jenis.equalsIgnoreCase(jenisSebelumnya)) {
                    sudahDihitung = true;
                    break;
                }
            }

            if (!sudahDihitung) {

                int jumlah = 0;

                for (int j = 0; j < data.size(); j++) {

                    String jenisData =
                            data.get(j).getJenisNarkotika();

                    if (jenisData == null ||
                            jenisData.trim().isEmpty()) {
                        jenisData = "Tidak diketahui";
                    }

                    if (jenis.equalsIgnoreCase(jenisData)) {
                        jumlah++;
                    }
                }

                System.out.println(jenis + " : " + jumlah + " putusan");
            }
        }

        System.out.println("Total putusan: " + data.size());
    }
}
