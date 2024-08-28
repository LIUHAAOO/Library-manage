package org.liu.CRUD;
import org.jetbrains.annotations.NotNull;
import org.liu.Mapper.SearchBookStateMapper;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.BookInformation;
import org.liu.libraryElement.BookState;
import java.util.LinkedList;
import java.util.List;

public class AddBookState {


    public static @NotNull LinkedList<BookInformation> addBookState(@NotNull List<Book> booksList, SearchBookStateMapper mapper) {
        LinkedList<BookInformation> bookInformation = new LinkedList<>();
        for(Book i: booksList) {
            boolean canBeUsed;
            //用于保存临时书籍信息（书籍+状态）
            BookInformation tempInformation = new BookInformation();
            // 获取状态
            BookState bookState = mapper.searchBookStateByBid(i.getBid());
            //进行是否可以借阅的判断
            if(bookState == null) canBeUsed = true;
            else canBeUsed = (bookState.getReturn_date() != null);
            //写入信息
            tempInformation.setBook(i);
            tempInformation.setBookState(bookState);
            tempInformation.setCanBeUsed(canBeUsed);
            //加入到表中
            bookInformation.add(tempInformation);
        }
        //返回一个表
        return bookInformation;
    }
}
