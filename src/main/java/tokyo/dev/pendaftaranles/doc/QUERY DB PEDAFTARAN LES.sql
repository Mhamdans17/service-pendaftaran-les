CREATE DATABASE db_lest;

USE db_lest;

CREATE TABLE pendaftaran_les (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nama_siswa VARCHAR(100) NOT NULL,
    kelas VARCHAR(50) NOT NULL,
    jenis_les VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE kelas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nama_kelas VARCHAR(50) NOT NULL UNIQUE,
    aktif BOOLEAN DEFAULT true
);

INSERT INTO kelas (nama_kelas, aktif) VALUES
('Kelas 7', true),
('Kelas 8', true),
('Kelas 9', true);

