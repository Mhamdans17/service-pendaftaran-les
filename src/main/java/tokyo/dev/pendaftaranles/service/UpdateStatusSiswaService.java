package tokyo.dev.pendaftaranles.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tokyo.dev.pendaftaranles.constant.ApiConstants;
import tokyo.dev.pendaftaranles.dto.ApiResponse;
import tokyo.dev.pendaftaranles.dto.PendaftaranLesResponse;
import tokyo.dev.pendaftaranles.dto.UpdateStatusRequest;
import tokyo.dev.pendaftaranles.entity.PendaftaranLes;
import tokyo.dev.pendaftaranles.exception.BadRequestException;
import tokyo.dev.pendaftaranles.repository.PendaftaranLesRepository;
import tokyo.dev.pendaftaranles.util.KelasValidator;

@Service
@RequiredArgsConstructor
public class UpdateStatusSiswaService {

    private final PendaftaranLesRepository repository;
    private final KelasValidator kelasValidator;

    @Transactional
    public ApiResponse<PendaftaranLesResponse> updateStatus(UpdateStatusRequest request) {

        kelasValidator.validate(request.getKelas());

        PendaftaranLes p = repository.findById(request.getId())
                .orElseThrow(() ->
                        new BadRequestException(ApiConstants.DATA_SISWA_NULL));

        if (!p.getNamaSiswa().equalsIgnoreCase(request.getNamaSiswa().trim())
                || !p.getKelas().equalsIgnoreCase(request.getKelas().trim())) {
            throw new BadRequestException(ApiConstants.ID_SISWA_NOTMATCH);
        }

        String statusBaru = request.getStatus().trim().toUpperCase();

        if (!ApiConstants.STATUS_AKTIF.equals(statusBaru)
                && !ApiConstants.STATUS_BATAL.equals(statusBaru)) {
            throw new BadRequestException(ApiConstants.STATUS_INVALID);
        }

        if (!ApiConstants.STATUS_DAFTAR.equals(p.getStatus())) {
            throw new BadRequestException(
                    ApiConstants.STATUS_ONLY_CHANGE_FROM_DAFTAR
            );
        }

        p.setStatus(statusBaru);
        repository.save(p);

        return new ApiResponse<>(
                ApiConstants.STATUS_SUCCESS,
                ApiConstants.SUCCESS_CODE,
                ApiConstants.MSG_UPDATE_STATUS_SUCCESS,
                new PendaftaranLesResponse(
                        p.getId(),
                        p.getNamaSiswa(),
                        p.getKelas(),
                        p.getJenisLes(),
                        p.getStatus()
                )
        );
    }
}

