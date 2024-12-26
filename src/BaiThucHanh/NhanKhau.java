package BaiThucHanh;

import java.util.Scanner;

public class NhanKhau {
    protected String hoVaTen;
    private String ngaySinh;
    private boolean gioiTinh;
    public NhanKhau() {}
    public NhanKhau(String hoVaTen, String ngaySinh, boolean gioiTinh) {
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên: ");
        this.hoVaTen = sc.nextLine();
        System.out.println("Nhập ngày sinh: ");
        this.ngaySinh = sc.nextLine();
        System.out.println("Nhập giới tính (Nam/Nữ): ");
        String gender = sc.nextLine();
        if ("nam".equalsIgnoreCase(gender)) {
            this.gioiTinh = true;
        } else {
            this.gioiTinh = false;
        }
    }

    public void hienThiTT() {
        System.out.println("HỌ TÊN: " + hoVaTen);
        System.out.println("NGÀY SINH: " + ngaySinh);
        System.out.println("GIỚI TÍNH: " + gioiTinh);
    }
}

class HoKhau extends NhanKhau {
    private String diaChiHienNay, noiCongTac;
    public HoKhau() {}
    public HoKhau(String hoVaTen, String ngaySinh, boolean gioiTinh, String diaChiHienNay, String noiCongTac) {
        super(hoVaTen, ngaySinh, gioiTinh);
        this.diaChiHienNay = diaChiHienNay;
        this.noiCongTac = noiCongTac;
    }

    public void nhapThongTin() {
        super.nhapThongTin();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập địa chỉ hiện nay: ");
        this.diaChiHienNay = sc.nextLine();
        System.out.println("Nhập nơi công tác: ");
        this.noiCongTac = sc.nextLine();
    }

    public void hienThiTT() {
        super.hienThiTT();
        System.out.println("ĐỊA CHỈ HIỆN NAY: " + diaChiHienNay);
        System.out.println("NƠI CÔNG TÁC: " + noiCongTac);
    }
}

class SoHoKhau {
    private int soNhanKhau;
    protected HoKhau[] arrThanhVien;
    public SoHoKhau() {}
    public SoHoKhau(int soNhanKhau) {
        this.soNhanKhau = soNhanKhau;
        arrThanhVien = new HoKhau[soNhanKhau];
    }

    public void nhapDuLieu() {
        for (int i = 0; i < arrThanhVien.length; i++) {
            arrThanhVien[i] = new HoKhau();
            arrThanhVien[i].nhapThongTin();
        }
    }

    public void hienThiDuLieu() {
        for (int i = 0; i < arrThanhVien.length; i++) {
            System.out.println("Nhập tt thành viên thứ " + (i+1) + ":");
            HoKhau tv = arrThanhVien[i];
            tv.hienThiTT();
        }
    }
}

class QuanLyHoKhau {
    private SoHoKhau[] arrSoHoKhau;
    public int showMenu() {
        System.out.println("=============MENU=============");
        System.out.println("1. Khai báo sổ hộ khẩu");
        System.out.println("2. Danh sách sổ hộ khẩu");
        System.out.println("3. Tim người");
        System.out.println("Vui lòng nhập từ 1-->3");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void khaiBaoSHK() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Khai báo số hộ khẩu]");
        System.out.println("Nhập số hộ khẩu cần khai báo: ");
        arrSoHoKhau = new SoHoKhau[sc.nextInt()];
        for (int i = 0; i < arrSoHoKhau.length; i++) {
            System.out.println("Nhập số nhân khẩu hộ thứ " + (i+1) + ":");
            arrSoHoKhau[i] = new SoHoKhau(sc.nextInt());
            arrSoHoKhau[i].nhapDuLieu();
        }
    }

    public void danhSachSHK() {
        System.out.println("[Danh sách sổ hộ khẩu]");
        for (int i = 0; i < arrSoHoKhau.length; i++) {
            System.out.println("Hộ khẩu thứ " + (i+1) + ":");
            arrSoHoKhau[i].hienThiDuLieu();
        }
    }

    public void timNguoi() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên người cần tìm: ");
        String tenCanTim = sc.nextLine();
        boolean found = false;

        for (SoHoKhau hoKhau : arrSoHoKhau) {
            for (HoKhau thanhVien : hoKhau.arrThanhVien) {
                if (thanhVien.hoVaTen.equalsIgnoreCase(tenCanTim)) {
                    System.out.println("Đã tìm thấy: ");
                    thanhVien.hienThiTT();
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy người với tên: " + tenCanTim);
        }
    }

    public static void main(String[] args) {
        int lc;
        QuanLyHoKhau quanly = new QuanLyHoKhau();
        do {
            lc = quanly.showMenu();
            switch (lc) {
                case 1:
                    quanly.khaiBaoSHK();
                    break;
                case 2:
                    quanly.danhSachSHK();
                    break;
                case 3:
                    quanly.timNguoi();
                    break;
            }
        }while (lc>0 && lc<=3);
    }
}