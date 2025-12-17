package tokyo.dev.pendaftaranles.constant;

public class ApiConstants {

    private ApiConstants() {

    }

    public static final String STATUS_SUCCESS  = "SUCCESS";
    public static final String STATUS_FAILED = "FAILED";

    public static final String SUCCESS_CODE = "20000";
    public static final String INVALID_REQUEST_CODE_PEDAFTARAN = "40001";
    public static final String INVALID_REQUEST_GET_DATA_CODE = "40002";

    public static final String MSG_PENDAFTARAN_SUCCESS = "Pendaftaran Siswa Les Sukses";
    public static final String MSG_GETDATA_SUCCESS = "Get Data Les Siswa Sukses";
    public static final String MSG_UPDATE_STATUS_SUCCESS = "Status siswa berhasil diubah";

    public static final String STATUS_DAFTAR = "DAFTAR";
    public static final String STATUS_AKTIF = "AKTIF";
    public static final String STATUS_BATAL = "BATAL";
    public static final String STATUS_INVALID = "Status tidak valid";
    public static final String STATUS_ONLY_CHANGE_FROM_DAFTAR = "Status hanya bisa diubah dari DAFTAR";

    public static final String KELAS_TIDAK_TERDAFTAR = "Kelas tidak terdaftar";
    public static final String KELAS_NOT_NULL = "kelas tidak boleh kosong";

    public static final String DATA_SISWA_NULL = "Data pendaftaran tidak ditemukan";
    public static final String ID_SISWA_NOTMATCH = "Data siswa tidak sesuai dengan ID";

}
