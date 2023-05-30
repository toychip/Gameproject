package com.gameproject.flash.domian;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {
    private List<T> content;
    private int currentPageNumber;
    private int totalPageNumber;

    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.currentPageNumber = page.getNumber();
        this.totalPageNumber = page.getTotalPages();
    }
}
