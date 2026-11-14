
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvLoader {

    private static final DateTimeFormatter FORMAT_TANGGAL =
            DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy",
                    new Locale("id", "ID")
            );

    public static int load(
            String filePath,
            SinglyLinkedList<Putusan> daftarPutusan
    ) throws IOException {

        int berhasil = 0;
        int gagal = 0;

        try (BufferedReader reader =
                     Files.newBufferedReader(Path.of(filePath))) {

            String baris;
            int nomorBaris = 0;

            while ((baris = reader.readLine()) != null) {
                nomorBaris++;

                if (baris.trim().isEmpty()) {
                    continue;
                }

                try {
                    List<String> k = parseCSV(baris);

                    if (k.get(0).equalsIgnoreCase("no")
                            || k.get(0).equalsIgnoreCase("caseid")) {
                        continue;
                    }

                    Putusan putusan;

                    if (k.size() == 13) {

                        int tahun = angkaInt(k.get(3));

                        LocalDate tanggal = tahun > 0
                                ? LocalDate.of(tahun, 1, 1)
                                : null;

                        String pidana = k.get(9).trim();

                        int tahunPidana =
                                ambilAngka(pidana, "tahun");

                        int bulanPidana =
                                ambilAngka(pidana, "bulan");

                        long denda = angkaLong(k.get(10));

                        double berat = angkaDouble(k.get(7));

                        putusan = new Putusan(
                                k.get(0).trim(),
                                k.get(1).trim(),
                                k.get(2).trim(),
                                tanggal,
                                k.get(4).trim(),
                                k.get(5).trim(),
                                berat,
                                k.get(6).trim(),
                                k.get(8).trim(),
                                tahunPidana,
                                bulanPidana,
                                denda,
                                k.get(11).trim(),
                                k.get(12).trim(),
                                "",
                                ""
                        );

                    } else if (k.size() == 16) {

                        LocalDate tanggal = null;
                        String tanggalText = k.get(3).trim();

                        if (!tanggalText.isEmpty()) {
                            try {
                                tanggal = LocalDate.parse(tanggalText);
                            } catch (Exception e) {
                                tanggal = LocalDate.parse(
                                        tanggalText,
                                        FORMAT_TANGGAL
                                );
                            }
                        }

                        putusan = new Putusan(
                                k.get(0).trim(),
                                k.get(1).trim(),
                                k.get(2).trim(),
                                tanggal,
                                k.get(4).trim(),
                                k.get(5).trim(),
                                angkaDouble(k.get(6)),
                                k.get(7).trim(),
                                k.get(8).trim(),
                                angkaInt(k.get(9)),
                                angkaInt(k.get(10)),
                                angkaLong(k.get(11)),
                                k.get(12).trim(),
                                k.get(13).trim(),
                                k.get(14).trim(),
                                k.get(15).trim()
                        );

                    } else {
                        throw new IllegalArgumentException(
                                "Jumlah kolom harus 13 atau 16, ditemukan "
                                        + k.size()
                        );
                    }

                    if (putusan.getCaseId() == null
                            || putusan.getCaseId().isEmpty()) {
                        throw new IllegalArgumentException(
                                "Case ID tidak boleh kosong"
                        );
                    }

                    daftarPutusan.addLast(putusan);
                    berhasil++;

                } catch (Exception e) {
                    gagal++;
                    System.out.println(
                            "Baris " + nomorBaris
                                    + " ditolak: " + e.getMessage()
                    );
                }
            }
        }

        System.out.println("Data berhasil dimuat: " + berhasil);
        System.out.println("Data gagal dimuat   : " + gagal);

        return berhasil;
    }

    private static int angkaInt(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return 0;
        }

        return Integer.parseInt(teks.trim());
    }

    private static long angkaLong(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return 0;
        }

        return Long.parseLong(
                teks.trim().replaceAll("[^0-9]", "")
        );
    }

    private static double angkaDouble(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return 0;
        }

        return Double.parseDouble(teks.trim());
    }

    private static int ambilAngka(
            String teks,
            String satuan
    ) {
        if (teks == null) {
            return 0;
        }

        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile(
                        "(\\d+)\\s*" + satuan,
                        java.util.regex.Pattern.CASE_INSENSITIVE
                ).matcher(teks);

        return matcher.find()
                ? Integer.parseInt(matcher.group(1))
                : 0;
    }

    private static List<String> parseCSV(String baris) {
        List<String> hasil = new ArrayList<>();
        StringBuilder nilai = new StringBuilder();
        boolean dalamKutipan = false;

        for (int i = 0; i < baris.length(); i++) {
            char c = baris.charAt(i);

            if (c == '"') {
                if (dalamKutipan
                        && i + 1 < baris.length()
                        && baris.charAt(i + 1) == '"') {

                    nilai.append('"');
                    i++;

                } else {
                    dalamKutipan = !dalamKutipan;
                }

            } else if (c == ',' && !dalamKutipan) {
                hasil.add(nilai.toString());
                nilai.setLength(0);

            } else {
                nilai.append(c);
            }
        }

        hasil.add(nilai.toString());
        return hasil;
    }
}
