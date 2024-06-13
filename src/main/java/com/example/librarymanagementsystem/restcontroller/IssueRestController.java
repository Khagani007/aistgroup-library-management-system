package com.example.librarymanagementsystem.restcontroller;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Issue;
import com.example.librarymanagementsystem.entity.IssuedBook;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.IssueService;
import com.example.librarymanagementsystem.service.IssuedBookService;
import com.example.librarymanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/issue")
public class IssueRestController {
    private final MemberService memberService;
    private final BookService bookService;
    private final IssueService issueService;
    private final IssuedBookService issuedBookService;

    @Autowired
    public IssueRestController(MemberService memberService, BookService bookService, IssueService issueService, IssuedBookService issuedBookService) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.issueService = issueService;
        this.issuedBookService = issuedBookService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam Map<String, String> payload) {

        String memberIdStr = (String) payload.get("member");
        String[] bookIdsStr = payload.get("books").split(",");

        Long memberId = null;
        List<Long> bookIds = new ArrayList<Long>();
        try {
            memberId = Long.parseLong(memberIdStr);
            for (String s : bookIdsStr) {
                bookIds.add(Long.parseLong(s));
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return "invalid number format";
        }

        Member member = memberService.get(memberId);
        List<Book> books = bookService.get(bookIds);

        Issue issue = new Issue();
        issue.setMember(member);
        issue = issueService.addNew(issue);

        for (Book book : books) {
            book.setStatus(AppConstants.BOOK_STATUS_ISSUED);
            book = bookService.save(book);

            IssuedBook ib = new IssuedBook();
            ib.setBook(book);
            ib.setIssue(issue);
            issuedBookService.addNew(ib);

        }

        return "success";
    }

    @RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
    public String returnAll(@PathVariable(name = "id") Long id) {
        Issue issue = issueService.get(id);
        if (issue != null) {
            List<IssuedBook> issuedBooks = issue.getIssuedBooks();
            for (IssuedBook ib : issuedBooks) {
                ib.setReturned(AppConstants.BOOK_RETURNED);
                issuedBookService.save(ib);

                Book book = ib.getBook();
                book.setStatus(AppConstants.BOOK_STATUS_AVAILABLE);
                bookService.save(book);
            }

            issue.setReturned(AppConstants.BOOK_RETURNED);
            issueService.save(issue);

            return "successful";
        } else {
            return "unsuccessful";
        }
    }

    @RequestMapping(value = "/{id}/return", method = RequestMethod.POST)
    public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
        Issue issue = issueService.get(id);
        String[] issuedBookIds = payload.get("ids").split(",");
        if (issue != null) {

            List<IssuedBook> issuedBooks = issue.getIssuedBooks();
            for (IssuedBook ib : issuedBooks) {
                if (Arrays.asList(issuedBookIds).contains(ib.getId().toString())) {
                    ib.setReturned(AppConstants.BOOK_RETURNED);
                    issuedBookService.save(ib);

                    Book book = ib.getBook();
                    book.setStatus(AppConstants.BOOK_STATUS_AVAILABLE);
                    bookService.save(book);
                }
            }

            return "successful";
        } else {
            return "unsuccessful";
        }
    }

}
