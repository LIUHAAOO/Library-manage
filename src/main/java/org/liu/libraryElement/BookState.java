package org.liu.libraryElement;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BookState {
    private Date borrow_date;
    private Date return_date;
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String borrowDateStr = borrow_date != null ? dateFormat.format(borrow_date) : "无";
        String returnDateStr = return_date != null ? dateFormat.format(return_date) : "无";
        return "借书日期: " + borrowDateStr + ", 还书日期: " + returnDateStr;
    }
}
