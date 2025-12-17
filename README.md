# Service Pendaftaran Les

Service **Pendaftaran Les** adalah REST API berbasis **Spring Boot** yang digunakan untuk mengelola proses pendaftaran siswa les, validasi kelas, perubahan status siswa, serta rekap data pendaftaran per kelas.

Project ini dirancang dengan pendekatan **clean code**, **separation of concern**, dan **best practice Spring Boot** agar mudah dikembangkan dan di-debug.

---

## Fitur Utama

### Pendaftaran Siswa
- Mendaftarkan siswa ke kelas les
- Status awal otomatis **DAFTAR**
- Validasi:
  - Kelas harus terdaftar & aktif
  - Request body tervalidasi

### Update Status Siswa
- Mengubah status siswa dari:
  - `DAFTAR → AKTIF`
  - `DAFTAR → BATAL`
- Validasi:
  - Data siswa harus sesuai (ID, nama, kelas)
  - Status hanya boleh diubah dari `DAFTAR`

### Rekap Pendaftaran per Kelas
- Menampilkan:
  - Total pendaftar
  - Total siswa aktif
  - Total siswa batal
- Validasi kelas sebelum proses

### Centralized Validation
- Validasi kelas dipisahkan ke `KelasValidator`
- Lebih rapi, reusable, dan mudah di-maintain

### Request & Response Logging
- Logging request & response metadata
- Menggunakan **Correlation ID** untuk tracing
- Tidak menyimpan body (ringan & production-ready)

## Setup Service
### Prasyarat
Pastikan tools berikut sudah terpasang:
- Java 17 atau lebih baru
- Maven
- Database (MySQL)

### Clone Repository
```bash
git clone https://github.com/username/service-pendaftaran-les.git
cd service-pendaftaran-les
````
### Run Query & Postman Collection
- Lokasi File
``` bash
src/main/java/tokyo/dev/pendaftaranles/doc
```
