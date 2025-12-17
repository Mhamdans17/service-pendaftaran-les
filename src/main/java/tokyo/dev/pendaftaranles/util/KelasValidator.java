package tokyo.dev.pendaftaranles.util;

import org.springframework.stereotype.Component;
import tokyo.dev.pendaftaranles.constant.ApiConstants;
import tokyo.dev.pendaftaranles.exception.BadRequestException;
import tokyo.dev.pendaftaranles.repository.KelasRepository;

@Component
public class KelasValidator {

    private final KelasRepository kelasRepository;

    public KelasValidator(KelasRepository kelasRepository) {
        this.kelasRepository = kelasRepository;
    }

    public void validate(String kelas) {

        if (!kelasRepository.existsByNamaKelasAndAktifTrue(kelas)) {
            throw new BadRequestException(ApiConstants.KELAS_TIDAK_TERDAFTAR);
        }
    }
}
