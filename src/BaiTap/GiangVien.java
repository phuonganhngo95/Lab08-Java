package BaiTap;

import java.util.ArrayList;
import java.util.Scanner;

// Lớp cha GiangVien
abstract class GiangVien {
    protected String ten;
    protected String email;
    protected String diaChi;
    protected String dienThoai;

    public GiangVien(String ten, String email, String diaChi, String dienThoai) {
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    public abstract double tinhLuong();

    public String getTen() {
        return ten;
    }

    @Override
    public String toString() {
        return "Tên: " + ten + ", Email: " + email + ", Địa chỉ: " + diaChi + ", Điện thoại: " + dienThoai;
    }
}

// Lớp con GiangVienCoHuu
class GiangVienCoHuu extends GiangVien {
    private double luongThoaThuan;
    private int soGioQuyDinh;
    private int soGioDay;

    public GiangVienCoHuu(String ten, String email, String diaChi, String dienThoai, double luongThoaThuan, int soGioQuyDinh, int soGioDay) {
        super(ten, email, diaChi, dienThoai);
        this.luongThoaThuan = luongThoaThuan;
        this.soGioQuyDinh = soGioQuyDinh;
        this.soGioDay = soGioDay;
    }

    @Override
    public double tinhLuong() {
        double luong = luongThoaThuan;
        if (soGioDay > soGioQuyDinh) {
            luong += (soGioDay - soGioQuyDinh) * 200000;
        }
        return luong;
    }

    @Override
    public String toString() {
        return super.toString() + ", Lương thỏa thuận: " + luongThoaThuan + ", Số giờ quy định: " + soGioQuyDinh + ", Số giờ dạy: " + soGioDay;
    }
}

// Lớp con GiangVienThinhGiang
class GiangVienThinhGiang extends GiangVien {
    private String coQuan;
    private int soGioDay;

    public GiangVienThinhGiang(String ten, String email, String diaChi, String dienThoai, String coQuan, int soGioDay) {
        super(ten, email, diaChi, dienThoai);
        this.coQuan = coQuan;
        this.soGioDay = soGioDay;
    }

    @Override
    public double tinhLuong() {
        return soGioDay * 200000;
    }

    @Override
    public String toString() {
        return super.toString() + ", Cơ quan: " + coQuan + ", Số giờ dạy: " + soGioDay;
    }
}

// Lớp chính
class QuanLyGiangVien {
    private static ArrayList<GiangVien> danhSachGiangVien = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Quản lý giảng viên ---");
            System.out.println("1. Nhập vào thông tin của giảng viên");
            System.out.println("2. Xuất danh sách toàn bộ giảng viên");
            System.out.println("3. Xuất danh sách giảng viên cơ hữu");
            System.out.println("4. Xuất danh sách giảng viên thỉnh giảng");
            System.out.println("5. Tính tổng số tiền lương của toàn bộ giảng viên");
            System.out.println("6. Tìm giảng viên có tổng lương cao nhất");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon = Integer.parseInt(scanner.nextLine());

            switch (chon) {
                case 1:
                    nhapGiangVien();
                    break;
                case 2:
                    xuatDanhSachGiangVien();
                    break;
                case 3:
                    xuatGiangVienCoHuu();
                    break;
                case 4:
                    xuatGiangVienThinhGiang();
                    break;
                case 5:
                    tinhTongLuong();
                    break;
                case 6:
                    timGiangVienLuongCaoNhat();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    private static void nhapGiangVien() {
        System.out.println("1. Giảng viên cơ hữu");
        System.out.println("2. Giảng viên thỉnh giảng");
        System.out.print("Chọn loại giảng viên: ");
        int loai = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập tên: ");
        String ten = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine();
        System.out.print("Nhập điện thoại: ");
        String dienThoai = scanner.nextLine();

        if (loai == 1) {
            System.out.print("Nhập lương thỏa thuận: ");
            double luongThoaThuan = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhập số giờ quy định: ");
            int soGioQuyDinh = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập số giờ dạy: ");
            int soGioDay = Integer.parseInt(scanner.nextLine());
            danhSachGiangVien.add(new GiangVienCoHuu(ten, email, diaChi, dienThoai, luongThoaThuan, soGioQuyDinh, soGioDay));
        } else if (loai == 2) {
            System.out.print("Nhập cơ quan: ");
            String coQuan = scanner.nextLine();
            System.out.print("Nhập số giờ dạy: ");
            int soGioDay = Integer.parseInt(scanner.nextLine());
            danhSachGiangVien.add(new GiangVienThinhGiang(ten, email, diaChi, dienThoai, coQuan, soGioDay));
        } else {
            System.out.println("Loại giảng viên không hợp lệ!");
        }
    }

    private static void xuatDanhSachGiangVien() {
        System.out.println("\nDanh sách toàn bộ giảng viên:");
        for (GiangVien gv : danhSachGiangVien) {
            System.out.println(gv);
        }
    }

    private static void xuatGiangVienCoHuu() {
        System.out.println("\nDanh sách giảng viên cơ hữu:");
        for (GiangVien gv : danhSachGiangVien) {
            if (gv instanceof GiangVienCoHuu) {
                System.out.println(gv);
            }
        }
    }

    private static void xuatGiangVienThinhGiang() {
        System.out.println("\nDanh sách giảng viên thỉnh giảng:");
        for (GiangVien gv : danhSachGiangVien) {
            if (gv instanceof GiangVienThinhGiang) {
                System.out.println(gv);
            }
        }
    }

    private static void tinhTongLuong() {
        double tongLuong = 0;
        for (GiangVien gv : danhSachGiangVien) {
            tongLuong += gv.tinhLuong();
        }
        System.out.println("Tổng số tiền lương của toàn bộ giảng viên: " + tongLuong);
    }

    private static void timGiangVienLuongCaoNhat() {
        if (danhSachGiangVien.isEmpty()) {
            System.out.println("Danh sách giảng viên trống!");
            return;
        }

        GiangVien gvLuongCaoNhat = danhSachGiangVien.get(0);
        for (GiangVien gv : danhSachGiangVien) {
            if (gv.tinhLuong() > gvLuongCaoNhat.tinhLuong()) {
                gvLuongCaoNhat = gv;
            }
        }
        System.out.println("Giảng viên có tổng lương cao nhất: " + gvLuongCaoNhat);
    }
}