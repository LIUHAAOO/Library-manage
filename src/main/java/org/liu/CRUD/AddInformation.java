package org.liu.CRUD;
import lombok.Data;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.Student;
import java.util.Scanner;
@Data
public class AddInformation {

    public static Book addBook()  {
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎进入增加书籍功能");

        System.out.println("输入书籍的名称");
        String scannerTittle = scanner.next();

        System.out.println("输入书籍的作者");
        String scannerAuthor = scanner.next();

        System.out.println("输入书籍的简介");
        String scannerInf = scanner.next();

        Book book = new Book();
        book.setAuthor(scannerAuthor);
        book.setInf(scannerInf);
        book.setTitle(scannerTittle);
        return book;
    }

    public static Student addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎进入录入学生功能");

        System.out.println("输入学生姓名");
        String scannerName = scanner.next();

        System.out.println("输入学生学号");
        Long scannerNumber = scanner.nextLong();

        System.out.println("输入学生姓名");
        String scannerPassword = scanner.next();

        Student student = new Student();
        student.setSname(scannerName);
        student.setSnum(scannerNumber);
        student.setPassword(scannerPassword);

        return student;
    }
}

