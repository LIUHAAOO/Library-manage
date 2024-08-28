package org.liu.midElements;

import lombok.Data;
import org.liu.libraryElement.BookInformation;

import java.util.LinkedList;
@Data
public class searchElement {
    public LinkedList<BookInformation>finalResult;
    public String name;
}
