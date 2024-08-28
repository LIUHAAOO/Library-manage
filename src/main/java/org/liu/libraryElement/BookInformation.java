package org.liu.libraryElement;

import lombok.Data;

@Data
public class BookInformation {
    private Book book;
    private BookState bookState;
    private boolean canBeUsed;
    @Override
    public String toString() {
        return (book != null ? book.toString() : "无书籍信息") +
                ", " + (bookState != null ? bookState.toString() : "无状态信息") +
                ", " + (canBeUsed ? "可被借阅" : "不可被借阅");
    }
    
}
