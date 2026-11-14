
public class AuditLog {

    private Stack<String> riwayat;

    public AuditLog() {
        riwayat = new Stack<>();
    }

    public void catat(String aktivitas) {
        riwayat.push(aktivitas);
    }

    public void tampilkanRiwayat() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada aktivitas.");
            return;
        }

        System.out.println("===== RIWAYAT AKTIVITAS =====");

        Stack<String> sementara = new Stack<>();

        while (!riwayat.isEmpty()) {
            String aktivitas = riwayat.pop();

            System.out.println(aktivitas);
            sementara.push(aktivitas);
        }

        while (!sementara.isEmpty()) {
            riwayat.push(sementara.pop());
        }
    }

    public int jumlahAktivitas() {
        return riwayat.size();
    }

    public boolean validateInvariant() {
        return riwayat.validateInvariant();
    }
}
