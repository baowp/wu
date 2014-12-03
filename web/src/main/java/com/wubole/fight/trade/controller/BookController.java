package com.wubole.fight.trade.controller;

import com.alibaba.fastjson.JSON;
import com.genlot.sports.account.shared.dto.BookDto;
import com.genlot.sports.account.shared.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by baowp on 2014/9/22.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private HttpServletRequest request;
    @Resource
    private BookService bookService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public BookDto add(BookDto book) {
        if (logger.isInfoEnabled()) {
            logger.info("params: " + JSON.toJSONString(book));
        }
        bookService.addBook(book);
        return book;
    }

    @RequestMapping(value = "/list")
    public String list() {
        List<BookDto> list = bookService.list();
        request.setAttribute("books", list);
        return "book/list";
    }
}
