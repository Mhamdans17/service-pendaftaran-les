package tokyo.dev.pendaftaranles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {

    @NotNull(message = "id tidak boleh kosong")
    private Long id;

    @NotBlank(message = "nama siswa tidak boleh kosong")
    private String namaSiswa;

    @NotBlank(message = "kelas tidak boleh kosong")
    private String kelas;

    @NotBlank(message = "status tidak boleh kosong")
    private String status;
}
