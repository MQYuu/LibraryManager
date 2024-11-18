package library.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import library.bookdata.Book;
import library.bookdata.ReferenceBook;
import library.bookdata.Textbook;

public class Main {
    public static void main(String[] args) {
        // Khoi tao doi tuong BookManager
        BookManager manager = new BookManager();

        // Khoi tao doi tuong Scanner de nguoi dung nhap lieu
        Scanner scanner = new Scanner(System.in);

        // Hien thi menu cho nguoi dung lua chon
        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Them sach");
            System.out.println("2. Xoa sach");
            System.out.println("3. Sua thong tin sach");
            System.out.println("4. Tim kiem sach theo nha xuat ban");
            System.out.println("5. Tinh tong thanh tien cho tung loai sach");
            System.out.println("6. Tinh trung binh cong don gia sach tham khao");
            System.out.println("7. Xuat sach giao khoa cua nha xuat ban");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Doc bo newline
            
            switch (choice) {
                case 1:
                    // Them sach
                    System.out.println("\n=== Them sach ===");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        System.out.print("Nhap ngay them sach giao khoa (yyyy-MM-dd): ");
                        String dateTextbook = scanner.nextLine();
                        Date textbookDate = sdf.parse(dateTextbook);  // Chuyen doi tu String thanh Date

                        System.out.print("Nhap ngay them sach tham khao (yyyy-MM-dd): ");
                        String dateReferenceBook = scanner.nextLine();
                        Date referenceBookDate = sdf.parse(dateReferenceBook);

                        System.out.print("Nhap don gia sach giao khoa: ");
                        double unitPriceTextbook = scanner.nextDouble();
                        System.out.print("Nhap so luong sach giao khoa: ");
                        int quantityTextbook = scanner.nextInt();
                        scanner.nextLine();  // Doc bo newline
                        System.out.print("Nhap nha xuat ban sach giao khoa: ");
                        String publisherTextbook = scanner.nextLine();

                        System.out.print("Nhap tinh trang sach giao khoa (new/old): ");
                        String conditionTextbook = scanner.nextLine();

                        Book textbook = new Textbook("T001", textbookDate, unitPriceTextbook, quantityTextbook, publisherTextbook, conditionTextbook);
                        manager.addBook(textbook);  // Them sach giao khoa vao he thong
                        System.out.println("Da them sach giao khoa: " + textbook.getBookId());

                        System.out.print("Nhap don gia sach tham khao: ");
                        double unitPriceReferenceBook = scanner.nextDouble();
                        System.out.print("Nhap so luong sach tham khao: ");
                        int quantityReferenceBook = scanner.nextInt();
                        scanner.nextLine();  // Doc bo newline
                        System.out.print("Nhap nha xuat ban sach tham khao: ");
                        String publisherReferenceBook = scanner.nextLine();
                        System.out.print("Nhap thue sach tham khao: ");
                        double tax = scanner.nextDouble();

                        Book referenceBook = new ReferenceBook("R001", referenceBookDate, unitPriceReferenceBook, quantityReferenceBook, publisherReferenceBook, tax);
                        manager.addBook(referenceBook);  // Them sach tham khao vao he thong
                        System.out.println("Da them sach tham khao: " + referenceBook.getBookId());

                    } catch (ParseException e) {
                        System.out.println("Loi khi nhap ngay.");
                    }
                    break;

                case 2:
                    // Xoa sach
                    System.out.println("\n=== Xoa sach ===");
                    System.out.print("Nhap ma sach can xoa: ");
                    String bookIdToRemove = scanner.nextLine();
                    boolean isRemoved = manager.removeBook(bookIdToRemove);
                    if (isRemoved) {
                        System.out.println("Da xoa sach co ma " + bookIdToRemove);
                    } else {
                        System.out.println("Khong tim thay sach co ma " + bookIdToRemove + " de xoa");
                    }
                    break;

                case 3:
                    // Sua thong tin sach
                    System.out.println("\n=== Sua thong tin sach ===");
                    System.out.print("Nhap ma sach can sua: ");
                    String bookIdToUpdate = scanner.nextLine();
                    
                    // Tim kiem sach trong danh sach
                    Book bookToUpdate = null;
                    for (Book book : manager.getBooks()) {  // Truy cap truc tiep vao danh sach sach
                        if (book.getBookId().equals(bookIdToUpdate)) {
                            bookToUpdate = book;
                            break;
                        }
                    }
                
                    if (bookToUpdate == null) {
                        System.out.println("Khong tim thay sach voi ma " + bookIdToUpdate);
                        break;
                    }
                
                    // Nhap cac thong tin can cap nhat
                    System.out.print("Nhap ngay them sach (dd/MM/yyyy): ");
                    String dateAddedStr = scanner.nextLine();
                    Date dateAdded = null;
                    
                    try {
                        // Chuyen chuoi thanh ngay theo dinh dang "dd/MM/yyyy"
                        dateAdded = new SimpleDateFormat("dd/MM/yyyy").parse(dateAddedStr);
                    } catch (ParseException e) {
                        // Xu ly truong hop khong the chuyen chuoi thanh ngay
                        System.out.println("Loi: Dinh dang ngay khong hop le. Vui long nhap lai theo dinh dang dd/MM/yyyy.");
                        break;  // Thoat khoi case neu ngay khong hop le
                    }
                
                    System.out.print("Nhap don gia moi: ");
                    double newUnitPrice = scanner.nextDouble();
                
                    System.out.print("Nhap so luong moi: ");
                    int newQuantity = scanner.nextInt();
                
                    scanner.nextLine();  // Doc bo newline
                
                    System.out.print("Nhap nha xuat ban moi: ");
                    String publisher = scanner.nextLine();
                
                    // Kiem tra loai sach va yeu cau nhap thong tin bo sung
                    System.out.print("Nhap tinh trang sach (neu la sach giao khoa) hoac thue (neu la sach tham khao): ");
                    String conditionOrTax = scanner.nextLine();
                
                    // Cap nhat sach
                    if (bookToUpdate instanceof Textbook) {
                        // Neu la sach giao khoa, yeu cau nhap tinh trang sach
                        manager.updateBook(bookIdToUpdate, dateAdded, newUnitPrice, newQuantity, publisher, conditionOrTax, 0);
                    } else if (bookToUpdate instanceof ReferenceBook) {
                        // Neu la sach tham khao, yeu cau nhap thue
                        double newTax = Double.parseDouble(conditionOrTax);  // Chuyen doi string thanh double cho thue
                        manager.updateBook(bookIdToUpdate, dateAdded, newUnitPrice, newQuantity, publisher, null, newTax);
                    } else {
                        // Sach thong thuong khong co tinh trang hay thue
                        manager.updateBook(bookIdToUpdate, dateAdded, newUnitPrice, newQuantity, publisher, null, 0);
                    }
                
                    System.out.println("Da cap nhat thong tin sach co ma " + bookIdToUpdate);
                    break;

                case 4:
                    // Tim kiem sach theo nha xuat ban
                    System.out.println("\n=== Tim kiem sach theo nha xuat ban ===");
                    System.out.print("Nhap ten nha xuat ban can tim: ");
                    String publisherToSearch = scanner.nextLine();
                    List<Book> booksFromPublisher = manager.searchByPublisher(publisherToSearch);
                    System.out.println("Tim thay " + booksFromPublisher.size() + " sach tu nha xuat ban " + publisherToSearch);
                    break;

                case 5:
                    // Tinh tong thanh tien cho tung loai sach
                    System.out.println("\n=== Tinh tong thanh tien cho tung loai sach ===");
                    double totalPriceTextbooks = manager.calculateTotalPrice(Textbook.class);
                    double totalPriceReferenceBooks = manager.calculateTotalPrice(ReferenceBook.class);
                    System.out.println("Tong thanh tien cua sach giao khoa: " + totalPriceTextbooks);
                    System.out.println("Tong thanh tien cua sach tham khao: " + totalPriceReferenceBooks);
                    break;

                case 6:
                    // Tinh trung binh cong don gia sach tham khao
                    System.out.println("\n=== Tinh trung binh cong don gia sach tham khao ===");
                    double averageUnitPriceReference = manager.calculateAverageUnitPriceForReferenceBooks();
                    System.out.println("Trung binh cong don gia cua sach tham khao: " + averageUnitPriceReference);
                    break;

                    case 7:
                    // Xuat sach giao khoa cua nha xuat ban
                    System.out.println("\n=== Xuat sach giao khoa cua nha xuat ban ===");
                    System.out.print("Nhap ten nha xuat ban can xuat sach giao khoa: ");
                    String publisherToExport = scanner.nextLine();
                    List<Textbook> textbooksPublisher = manager.exportTextbooksByPublisher(publisherToExport);
                    System.out.println("So luong sach giao khoa cua nha xuat ban " + publisherToExport + ": " + textbooksPublisher.size());
                    for (Textbook tb : textbooksPublisher) {
                        System.out.println(" - Sach giao khoa ma: " + tb.getBookId() + ", Nha xuat ban: " + tb.getPublisher() + ", Gia: " + tb.getUnitPrice() + ", So luong: " + tb.getQuantity());
                    }
                    break;
                

                case 0:
                    // Thoat
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }
    }
}
