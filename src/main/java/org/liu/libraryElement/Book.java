package org.liu.libraryElement;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class Book {
    private int bid;
    private String title;
    private String author;
    private String inf;
    @Override
    public String toString() {
        return "书籍id:" + bid + " 书籍名称:" + title + " 作者:" + author + " 简介:" + inf;
    }
}
