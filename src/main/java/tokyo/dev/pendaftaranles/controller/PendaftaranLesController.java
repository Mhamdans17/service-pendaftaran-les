package tokyo.dev.pendaftaranles.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tokyo.dev.pendaftaranles.constant.ApiConstants;
import tokyo.dev.pendaftaranles.dto.*;
import tokyo.dev.pendaftaranles.service.PendaftaranLesService;
import tokyo.dev.pendaftaranles.service.UpdateStatusSiswaService;

@Validated
@RestController
@RequestMapping("/les")
public class PendaftaranLesController {

    private final PendaftaranLesService service;
    private final UpdateStatusSiswaService serviceUpdateStatusSiswa;

    public PendaftaranLesController(PendaftaranLesService service,
                                    UpdateStatusSiswaService serviceUpdateStatusSiswa) {
        this.service = service;
        this.serviceUpdateStatusSiswa = serviceUpdateStatusSiswa;
    }

    @PostMapping("/daftar")
    public ApiResponse<PendaftaranLesResponse> daftar(
            @Valid @RequestBody DaftarLesRequest request
    ) {
        return service.daftar(request);
    }

    @GetMapping("/rekap")
    public ApiResponse<RekapPendaftaranData> getRekapByKelas(
            @RequestParam
            @NotBlank(message = ApiConstants.KELAS_NOT_NULL)
            String kelas
    ) {
        return service.getRekapByKelas(kelas);
    }

    @PostMapping("/status")
    public ApiResponse<PendaftaranLesResponse> updateStatus(
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        return serviceUpdateStatusSiswa.updateStatus(request);
    }
}