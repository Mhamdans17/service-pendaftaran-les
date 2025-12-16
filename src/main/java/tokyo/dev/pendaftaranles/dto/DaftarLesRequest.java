package tokyo.dev.pendaftaranles.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DaftarLesRequest {
    @NotBlank(message = "namaSiswa tidak boleh kosong")
    private String namaSiswa;

    @NotBlank(message = "jenisLes tidak boleh kosong")
    private String jenisLes;

    @NotBlank(message = "kelas tidak boleh kosong")
    private String kelas;
}
