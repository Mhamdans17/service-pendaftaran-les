package tokyo.dev.pendaftaranles.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tokyo.dev.pendaftaranles.dto.*;
import tokyo.dev.pendaftaranles.service.PendaftaranLesService;

@Validated
@RestController
@RequestMapping("/les")
public class PendaftaranLesController {

    private final PendaftaranLesService service;

    public PendaftaranLesController(PendaftaranLesService service) {
        this.service = service;
    }

    @PostMapping("/daftar")
    public ApiResponse<PendaftaranLesResponse> daftar(
            @Valid @RequestBody DaftarLesRequest request
    ) {
        return service.daftar(request);
    }

    @GetMapping("/rekap")
    public ApiResponse<RekapPendaftaranData> rekapByKelas(
            @RequestParam
            @NotBlank(message = "kelas tidak boleh kosong")
            String kelas
    ) {
        return service.getRekapByKelas(kelas);
    }
}