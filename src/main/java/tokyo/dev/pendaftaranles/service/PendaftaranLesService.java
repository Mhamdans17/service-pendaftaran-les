package tokyo.dev.pendaftaranles.service;

import org.springframework.stereotype.Service;
import tokyo.dev.pendaftaranles.dto.ApiResponse;
import tokyo.dev.pendaftaranles.dto.DaftarLesRequest;
import tokyo.dev.pendaftaranles.dto.PendaftaranLesResponse;
import tokyo.dev.pendaftaranles.dto.RekapPendaftaranApiResponse;
import tokyo.dev.pendaftaranles.entity.PendaftaranLes;
import tokyo.dev.pendaftaranles.repository.PendaftaranLesRepository;

import java.util.List;

@Service
public class PendaftaranLesService {

    private final PendaftaranLesRepository repository;

    public PendaftaranLesService(PendaftaranLesRepository repository) {
        this.repository = repository;
    }

    public ApiResponse<PendaftaranLesResponse> daftar(DaftarLesRequest request) {

        PendaftaranLes p = new PendaftaranLes();
        p.setNamaSiswa(request.getNamaSiswa());
        p.setKelas(request.getKelas());
        p.setJenisLes(request.getJenisLes());
        p.setStatus("DAFTAR");

        PendaftaranLes saved = repository.save(p);

        PendaftaranLesResponse data = new PendaftaranLesResponse(
                saved.getId(),
                saved.getNamaSiswa(),
                saved.getKelas(),
                saved.getJenisLes(),
                saved.getStatus()
        );

        return new ApiResponse<>(
                "SUCCESS",
                "20001",
                "Pendaftaran Siswa Sukses",
                data
        );
    }

    public RekapPendaftaranApiResponse getRekapByKelas(String kelas) {

        List<PendaftaranLes> list = repository.findByKelas(kelas);

        long totalDaftar = list.size();

        long totalAktif = list.stream()
                .filter(p -> "AKTIF".equals(p.getStatus()))
                .count();

        long totalBatal = list.stream()
                .filter(p -> "BATAL".equals(p.getStatus()))
                .count();

        return new RekapPendaftaranApiResponse(
                "SUCCESS",
                "20002",
                "Data Pendaftaran Les Berhasil Diambil",
                kelas,
                totalDaftar,
                totalAktif,
                totalBatal
        );
    }
}
