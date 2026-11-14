
import java.time.LocalDate;

public class Putusan {
    private String caseId;
    private String nomorPerkara;
    private String pengadilan;
    private LocalDate tanggalPutusan;
    private String terdakwaKode;
    private String jenisNarkotika;
    private double beratBarangBuktiGram;
    private String pasalDakwaan;
    private String pasalTerbukti;
    private int tuntutanBulan;
    private int vonisBulan;
    private long dendaRupiah;
    private String barangBukti;
    private String keadaanMemberatkan;
    private String keadaanMeringankan;
    private String sourcePages;

    public Putusan(
            String caseId,
            String nomorPerkara,
            String pengadilan,
            LocalDate tanggalPutusan,
            String terdakwaKode,
            String jenisNarkotika,
            double beratBarangBuktiGram,
            String pasalDakwaan,
            String pasalTerbukti,
            int tuntutanBulan,
            int vonisBulan,
            long dendaRupiah,
            String barangBukti,
            String keadaanMemberatkan,
            String keadaanMeringankan,
            String sourcePages
    ) {
        this.caseId = caseId;
        this.nomorPerkara = nomorPerkara;
        this.pengadilan = pengadilan;
        this.tanggalPutusan = tanggalPutusan;
        this.terdakwaKode = terdakwaKode;
        this.jenisNarkotika = jenisNarkotika;
        this.beratBarangBuktiGram = beratBarangBuktiGram;
        this.pasalDakwaan = pasalDakwaan;
        this.pasalTerbukti = pasalTerbukti;
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
        this.dendaRupiah = dendaRupiah;
        this.barangBukti = barangBukti;
        this.keadaanMemberatkan = keadaanMemberatkan;
        this.keadaanMeringankan = keadaanMeringankan;
        this.sourcePages = sourcePages;
    }

    public String getCaseId() { return caseId; }
    public String getNomorPerkara() { return nomorPerkara; }
    public String getPengadilan() { return pengadilan; }
    public LocalDate getTanggalPutusan() { return tanggalPutusan; }
    public String getTerdakwaKode() { return terdakwaKode; }
    public String getJenisNarkotika() { return jenisNarkotika; }
    public double getBeratBarangBuktiGram() { return beratBarangBuktiGram; }
    public String getPasalDakwaan() { return pasalDakwaan; }
    public String getPasalTerbukti() { return pasalTerbukti; }
    public int getTuntutanBulan() { return tuntutanBulan; }
    public int getVonisBulan() { return vonisBulan; }
    public long getDendaRupiah() { return dendaRupiah; }
    public String getBarangBukti() { return barangBukti; }
    public String getKeadaanMemberatkan() { return keadaanMemberatkan; }
    public String getKeadaanMeringankan() { return keadaanMeringankan; }
    public String getSourcePages() { return sourcePages; }

    public void tampilkan() {
        System.out.println("ID Perkara        : " + caseId);
        System.out.println("Nomor Perkara     : " + nomorPerkara);
        System.out.println("Pengadilan        : " + pengadilan);
        System.out.println("Tanggal Putusan   : " + tanggalPutusan);
        System.out.println("Kode Terdakwa     : " + terdakwaKode);
        System.out.println("Jenis Narkotika   : " + jenisNarkotika);
        System.out.println("Berat Barang Bukti: " + beratBarangBuktiGram + " gram");
        System.out.println("Pasal Dakwaan     : " + pasalDakwaan);
        System.out.println("Pasal Terbukti    : " + pasalTerbukti);
        System.out.println("Tuntutan          : " + tuntutanBulan + " bulan");
        System.out.println("Vonis             : " + vonisBulan + " bulan");
        System.out.println("Denda             : Rp" + dendaRupiah);
        System.out.println("Barang Bukti      : " + barangBukti);
        System.out.println("Memberatkan       : " + keadaanMemberatkan);
        System.out.println("Meringankan       : " + keadaanMeringankan);
        System.out.println("Halaman Sumber    : " + sourcePages);
        System.out.println("----------------------------------------");
    }
}
