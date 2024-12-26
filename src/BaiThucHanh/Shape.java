package BaiThucHanh;

import java.util.Scanner;

public abstract class Shape {
    public abstract void inputData();
    public abstract void calArea();
}

class Circle extends Shape {
    float radius;
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập bán kính hình tròn: ");
        radius = sc.nextFloat();
    }
    public void calArea() {
        float area = (float) (radius*radius*Math.PI);
        System.out.printf("Diện tích hình tròn là: %2f", area);
    }
}

class Rectangle extends Shape {
    float width, height;
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chiều rộng: ");
        width = sc.nextFloat();
        System.out.println("Nhập chiều cao: ");
        height = sc.nextFloat();
    }

    public void calArea() {
        float area = width*height;
        System.out.printf("Diện tích tứ giác là: %2f", area);
    }
}

class Triangle extends Shape {
    float height, bottom;
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chiều cao: ");
        height = sc.nextFloat();
        System.out.println("Nhập cạnh đáy: ");
        bottom = sc.nextFloat();
    }

    public void calArea() {
        float area = (bottom*height)/2;
        System.out.printf("Diện tích tam giác là: %2f", area);
    }
}

class MainClass {
    public int showMenu() {
        System.out.println("\n|=============MENU==============|");
        System.out.println("1. Tính diện tích hình tròn");
        System.out.println("2. Tính diện tích hình tứ giác");
        System.out.println("3. Tính diện tích hình tam giác");
        System.out.println("Vui lòng chọn từ 1-->3");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void tinhDienTich(Shape hinh) {
        hinh.inputData();
        hinh.calArea();
    }

    public static void main(String[] args) {
        MainClass a = new MainClass();
        int lc;
        do {
            lc =  a.showMenu();
            switch (lc) {
                case 1:
                    a.tinhDienTich(new Circle());
                    break;
                case 2:
                    a.tinhDienTich(new Rectangle());
                    break;
                case 3:
                    a.tinhDienTich(new Triangle());
                    break;
            }
        }while (lc>0 && lc <= 3);
        System.out.println("Chương trình kết thúc");
    }
}