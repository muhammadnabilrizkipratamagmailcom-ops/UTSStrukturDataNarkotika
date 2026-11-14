
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final SinglyLinkedList<Putusan> daftarPutusan =
            new SinglyLinkedList<>();
    private static final AuditLog audit = new AuditLog();

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n================================");
            System.out.println("   SISTEM DATA PUTUSAN NARKOTIKA");
            System.out.println("================================");
            System.out.println("1. Import data CSV");
            System.out.println("2. Tampilkan semua data");
            System.out.println("3. Tambah data");
            System.out.println("4. Hapus data");
            System.out.println("5. Cari data");
            System.out.println("6. Tampilkan audit log");
            System.out.println("7. Statistik data");
            System.out.println("8. Filter data");
            System.out.println("9. Urutkan berdasarkan vonis");
            System.out.println("10. Ekspor data ke CSV");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = bacaAngka();

            switch (pilihan) {
                case 1:
                    importData();
                    break;
                case 2:
                    tampilkanSemua();
                    break;
                case 3:
                    tambahData();
                    break;
                case 4:
                    hapusData();
                    break;
                case 5:
                    cariData();
                    break;
                case 6:
                    audit.tampilkanRiwayat();
                    break;
                case 7:
                    tampilkanStatistik();
                    break;
                case 8:
                    menuFilter();
                    break;
                case 9:
                    urutkanData();
                    break;
                case 10:
                    eksporData();
                    break;
                case 0:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);
    }

    private static int bacaAngka() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void importData() {
        if (!daftarPutusan.isEmpty()) {
            System.out.println("Data sudah terisi. Import dibatalkan agar tidak duplikat.");
            return;
        }

        try {
            CsvLoader.load("data/putusan-50.csv", daftarPutusan);
            audit.catat("Import data CSV.");
            System.out.println("Jumlah data: " + daftarPutusan.size());
        } catch (IOException e) {
            System.out.println("Gagal membaca CSV: " + e.getMessage());
        }
    }

    private static void tampilkanSemua() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            daftarPutusan.get(i).tampilkan();
        }

        System.out.println("Total data: " + daftarPutusan.size());
    }

    private static void tambahData() {
        try {
            System.out.print("Case ID: ");
            String caseId = input.nextLine();

            if (caseId.trim().isEmpty()) {
                System.out.println("Case ID tidak boleh kosong.");
                return;
            }

            for (int i = 0; i < daftarPutusan.size(); i++) {
                if (caseId.equalsIgnoreCase(
                        daftarPutusan.get(i).getCaseId())) {
                    System.out.println("Case ID sudah ada.");
                    return;
                }
            }

            System.out.print("Nomor perkara: ");
            String nomor = input.nextLine();

            System.out.print("Pengadilan: ");
            String pengadilan = input.nextLine();

            System.out.print("Tanggal putusan (YYYY-MM-DD): ");
            String tanggalInput = input.nextLine();
            LocalDate tanggal = tanggalInput.trim().isEmpty()
                    ? null : LocalDate.parse(tanggalInput);

            System.out.print("Kode terdakwa: ");
            String terdakwa = input.nextLine();

            System.out.print("Jenis narkotika: ");
            String jenis = input.nextLine();

            System.out.print("Berat barang bukti (gram): ");
            double berat = Double.parseDouble(input.nextLine());

            System.out.print("Pasal dakwaan: ");
            String pasalDakwaan = input.nextLine();

            System.out.print("Pasal terbukti: ");
            String pasalTerbukti = input.nextLine();

            System.out.print("Tuntutan (bulan): ");
            int tuntutan = Integer.parseInt(input.nextLine());

            System.out.print("Vonis (bulan): ");
            int vonis = Integer.parseInt(input.nextLine());

            System.out.print("Denda (rupiah): ");
            long denda = Long.parseLong(input.nextLine());

            System.out.print("Barang bukti: ");
            String barangBukti = input.nextLine();

            System.out.print("Keadaan memberatkan: ");
            String memberatkan = input.nextLine();

            System.out.print("Keadaan meringankan: ");
            String meringankan = input.nextLine();

            System.out.print("Halaman sumber: ");
            String halaman = input.nextLine();

            if (berat < 0 || tuntutan < 0 || vonis < 0 || denda < 0) {
                System.out.println("Nilai angka tidak boleh negatif.");
                return;
            }

            Putusan p = new Putusan(
                    caseId, nomor, pengadilan, tanggal, terdakwa,
                    jenis, berat, pasalDakwaan, pasalTerbukti,
                    tuntutan, vonis, denda, barangBukti,
                    memberatkan, meringankan, halaman
            );

            daftarPutusan.addLast(p);
            audit.catat("Menambahkan putusan: " + caseId);
            System.out.println("Data berhasil ditambahkan.");

        } catch (Exception e) {
            System.out.println("Data gagal ditambahkan. Periksa format input.");
        }
    }

    private static void hapusData() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }

        System.out.print("Masukkan posisi data yang dihapus (0 sampai "
                + (daftarPutusan.size() - 1) + "): ");

        int posisi = bacaAngka();

        if (posisi < 0 || posisi >= daftarPutusan.size()) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Putusan terhapus = daftarPutusan.remove(posisi);
        audit.catat("Menghapus putusan: " + terhapus.getCaseId());
        System.out.println("Data berhasil dihapus.");
    }

    private static void cariData() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }

        System.out.print("Masukkan case ID atau nomor perkara: ");
        String kataKunci = input.nextLine();

        int langkah = 0;
        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            langkah++;
            Putusan p = daftarPutusan.get(i);

            if ((p.getCaseId() != null &&
                    p.getCaseId().equalsIgnoreCase(kataKunci))
                    || (p.getNomorPerkara() != null &&
                    p.getNomorPerkara().equalsIgnoreCase(kataKunci))) {

                System.out.println("Data ditemukan:");
                p.tampilkan();
                ditemukan = true;
                break;
            }
        }

        System.out.println("Jumlah langkah pencarian: " + langkah);

        if (!ditemukan) {
            System.out.println("Data tidak ditemukan.");
        }
    }

    private static void tampilkanStatistik() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data untuk dihitung.");
            return;
        }

        long totalDenda = 0;
        double totalVonis = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            totalDenda += daftarPutusan.get(i).getDendaRupiah();
            totalVonis += daftarPutusan.get(i).getVonisBulan();
        }

        System.out.println("\n===== STATISTIK PUTUSAN =====");
        System.out.println("Jumlah putusan: " + daftarPutusan.size());
        System.out.println("Rata-rata vonis: "
                + totalVonis / daftarPutusan.size() + " bulan");
        System.out.println("Total denda: Rp" + totalDenda);

        StatistikPutusan.tampilkanDistribusiJenis(daftarPutusan);
    }

    private static void menuFilter() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }

        System.out.println("\n===== MENU FILTER =====");
        System.out.println("1. Berdasarkan jenis narkotika");
        System.out.println("2. Berdasarkan berat barang bukti");
        System.out.println("3. Berdasarkan pasal terbukti");
        System.out.println("4. Berdasarkan lama vonis");
        System.out.print("Pilih: ");

        int pilihan = bacaAngka();
        SinglyLinkedList<Putusan> hasil = new SinglyLinkedList<>();

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan jenis narkotika: ");
                hasil = FilterPutusan.berdasarkanJenis(
                        daftarPutusan, input.nextLine());
                break;

            case 2:
                System.out.print("Berat minimum: ");
                double min = Double.parseDouble(input.nextLine());
                System.out.print("Berat maksimum: ");
                double max = Double.parseDouble(input.nextLine());
                hasil = FilterPutusan.berdasarkanBerat(
                        daftarPutusan, min, max);
                break;

            case 3:
                System.out.print("Masukkan pasal terbukti: ");
                hasil = FilterPutusan.berdasarkanPasal(
                        daftarPutusan, input.nextLine());
                break;

            case 4:
                System.out.print("Vonis minimum: ");
                int minVonis = Integer.parseInt(input.nextLine());
                System.out.print("Vonis maksimum: ");
                int maxVonis = Integer.parseInt(input.nextLine());
                hasil = FilterPutusan.berdasarkanVonis(
                        daftarPutusan, minVonis, maxVonis);
                break;

            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        System.out.println("\nHasil filter:");
        if (hasil.isEmpty()) {
            System.out.println("Tidak ada data yang cocok.");
        } else {
            for (int i = 0; i < hasil.size(); i++) {
                hasil.get(i).tampilkan();
            }
            System.out.println("Jumlah hasil: " + hasil.size());
        }

        audit.catat("Melakukan filter data.");
    }

    private static void urutkanData() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }

        SinglyLinkedList<Putusan> hasil =
                UrutPutusan.urutBerdasarkanVonis(daftarPutusan);

        System.out.println("\n===== URUTAN VONIS TERBESAR =====");

        for (int i = 0; i < hasil.size(); i++) {
            hasil.get(i).tampilkan();
        }

        audit.catat("Mengurutkan data berdasarkan vonis.");
    }

    private static void eksporData() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Tidak ada data untuk diekspor.");
            return;
        }

        try {
            ExportPutusan.eksporCSV(
                    daftarPutusan, "docs/laporan-hasil.csv");

            audit.catat("Mengekspor data ke CSV.");

        } catch (IOException e) {
            System.out.println("Gagal mengekspor: " + e.getMessage());
        }
    }
}
