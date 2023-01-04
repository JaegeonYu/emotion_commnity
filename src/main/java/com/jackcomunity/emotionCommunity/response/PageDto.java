package com.jackcomunity.emotionCommunity.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@Getter
public class PageDto<T> {

    private final static int VIEWPAGESIZE = 5;
    private int startPage;
    private int endPage;
    private int nowPage;
    private int totalPages;
    private Page<T> contents;

    private PageDto(int startPage, int endPage,int nowPage, int totalPages, Page<T> contents) {
        this.startPage = startPage;
        this.endPage = endPage;
        this.nowPage = nowPage;
        this.totalPages = totalPages;
        this.contents = contents;
    }

    public static <T> PageDto of( Page<T> contents) {
        int totalPages =contents.getTotalPages();
        int nowPage = contents.getPageable().getPageNumber() + 1;
        int startPage = 1 * VIEWPAGESIZE * (nowPage / VIEWPAGESIZE);
        int endPage = startPage + VIEWPAGESIZE -1;

        return new PageDto<>(startPage == 0 ? 1 : startPage, endPage > totalPages ? totalPages : endPage,nowPage, totalPages, contents);
    }
}