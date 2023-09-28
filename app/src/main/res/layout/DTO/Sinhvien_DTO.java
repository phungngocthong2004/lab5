package DTO;

public class Sinhvien_DTO {
    private int id;
    private String ten;
    private int tuoi;

    public Sinhvien_DTO() {
    }

    public Sinhvien_DTO(int id, String ten, int tuoi) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public Sinhvien_DTO(String ten, int tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
