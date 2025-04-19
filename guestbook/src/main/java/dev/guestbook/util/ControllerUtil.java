package dev.guestbook.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ControllerUtil {

    public static Pageable createPageable(int pageNum, int pageSize)    {
        if (pageNum <= 1)   pageNum = 1;
        if (pageSize <= 0)  pageSize = 10;

        return PageRequest.of(pageNum - 1, pageSize);
    }
}
