package tokyo.dev.pendaftaranles.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tokyo.dev.pendaftaranles.constant.ApiConstants;
import tokyo.dev.pendaftaranles.dto.*;
import tokyo.dev.pendaftaranles.entity.PendaftaranLes;
import tokyo.dev.pendaftaranles.repository.PendaftaranLesRepository;
import tokyo.dev.pendaftaranles.util.KelasValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PendaftaranLesService {

    private final PendaftaranLesRepository repository;
    private final KelasValidator kelasValidator;

    // PEDAFTARAN LES
    public ApiResponse<PendaftaranLesResponse> daftar(DaftarLesRequest request) {

        kelasValidator.validate(request.getKelas());

        PendaftaranLes p = new PendaftaranLes();
        p.setNamaSiswa(request.getNamaSiswa());
        p.setKelas(request.getKelas());
        p.setJenisLes(request.getJenisLes());
        p.setStatus(ApiConstants.STATUS_DAFTAR);

        PendaftaranLes saved = repository.save(p);

        return success(
                new PendaftaranLesResponse(
                        saved.getId(),
                        saved.getNamaSiswa(),
                        saved.getKelas(),
                        saved.getJenisLes(),
                        saved.getStatus()
                ),
                ApiConstants.MSG_PENDAFTARAN_SUCCESS
        );
    }

    // REKAP KELAS
    public ApiResponse<RekapPendaftaranData> getRekapByKelas(String kelas) {

        kelasValidator.validate(kelas);

        List<PendaftaranLes> list = repository.findByKelas(kelas);

        long totalAktif = list.stream()
                .filter(p -> ApiConstants.STATUS_AKTIF.equals(p.getStatus()))
                .count();

        long totalBatal = list.stream()
                .filter(p -> ApiConstants.STATUS_BATAL.equals(p.getStatus()))
                .count();

        return success(
                new RekapPendaftaranData(
                        kelas,
                        list.size(),
                        totalAktif,
                        totalBatal
                ),
                ApiConstants.MSG_GETDATA_SUCCESS
        );
    }

    // HELPER
    private <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(
                ApiConstants.STATUS_SUCCESS,
                ApiConstants.SUCCESS_CODE,
                message,
                data
        );
    }
}

