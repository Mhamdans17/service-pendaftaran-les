package tokyo.dev.pendaftaranles.controller;

import org.springframework.web.bind.annotation.*;
import tokyo.dev.pendaftaranles.dto.ApiResponse;
import tokyo.dev.pendaftaranles.dto.DaftarLesRequest;
import tokyo.dev.pendaftaranles.dto.PendaftaranLesResponse;
import tokyo.dev.pendaftaranles.dto.RekapPendaftaranApiResponse;
import tokyo.dev.pendaftaranles.service.PendaftaranLesService;

@RestController
@RequestMapping("/les")
public class PendaftaranLesController {

    private final PendaftaranLesService service;

    public PendaftaranLesController(PendaftaranLesService service) {
        this.service = service;
    }

    @PostMapping("/daftar")
    public ApiResponse<PendaftaranLesResponse> daftar(
            @RequestBody DaftarLesRequest request
    ) {
        return service.daftar(request);
    }

    @GetMapping("/rekap")
    public RekapPendaftaranApiResponse rekapByKelas(
            @RequestParam String kelas
    ) {
        return service.getRekapByKelas(kelas);
    }
}