
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportPutusan {

    public static void eksporCSV(
            SinglyLinkedList<Putusan> data,
            String namaFile) throws IOException {

        Path lokasi = Path.of(namaFile);

        if (lokasi.getParent() != null) {
            Files.createDirectories(lokasi.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                lokasi, StandardCharsets.UTF_8)) {

            writer.write(
                    "caseId,nomorPerkara,pengadilan,tanggalPutusan," +
                            "terdakwaKode,jenisNarkotika,beratBarangBuktiGram," +
                            "pasalDakwaan,pasalTerbukti,tuntutanBulan," +
                            "vonisBulan,dendaRupiah,barangBukti," +
                            "keadaanMemberatkan,keadaanMeringankan,sourcePages"
            );

            writer.newLine();

            for (int i = 0; i < data.size(); i++) {
                Putusan p = data.get(i);

                String[] kolom = {
                        p.getCaseId(),
                        p.getNomorPerkara(),
                        p.getPengadilan(),
                        p.getTanggalPutusan() == null
                                ? "" : p.getTanggalPutusan().toString(),
                        p.getTerdakwaKode(),
                        p.getJenisNarkotika(),
                        String.valueOf(p.getBeratBarangBuktiGram()),
                        p.getPasalDakwaan(),
                        p.getPasalTerbukti(),
                        String.valueOf(p.getTuntutanBulan()),
                        String.valueOf(p.getVonisBulan()),
                        String.valueOf(p.getDendaRupiah()),
                        p.getBarangBukti(),
                        p.getKeadaanMemberatkan(),
                        p.getKeadaanMeringankan(),
                        p.getSourcePages()
                };

                for (int j = 0; j < kolom.length; j++) {
                    if (j > 0) {
                        writer.write(",");
                    }
                    writer.write(amanCSV(kolom[j]));
                }

                writer.newLine();
            }
        }

        System.out.println("Ekspor berhasil!");
        System.out.println("Lokasi file: " + namaFile);
        System.out.println("Jumlah data: " + data.size());
    }

    private static String amanCSV(String nilai) {
        if (nilai == null) {
            return "\"\"";
        }

        String hasil = nilai.replace("\"", "\"\"");
        return "\"" + hasil + "\"";
    }
}
