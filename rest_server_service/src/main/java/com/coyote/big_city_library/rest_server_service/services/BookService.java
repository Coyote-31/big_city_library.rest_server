package com.coyote.big_city_library.rest_server_service.services;

import java.util.ArrayList;
import java.util.List;

import com.coyote.big_city_library.rest_server_model.dao.entities.Book;
import com.coyote.big_city_library.rest_server_repository.dao.repositories.BookRepository;
import com.coyote.big_city_library.rest_server_service.dto.BookDto;
import com.coyote.big_city_library.rest_server_service.dto.BookMapper;
import com.coyote.big_city_library.rest_server_service.dto.search_books.SearchBookDto;
import com.coyote.big_city_library.rest_server_service.dto.search_books.SearchBookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class handling books
 *
 * @see BookRepository
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    protected BookMapper bookMapper;

    @Autowired
    protected SearchBookMapper searchBookMapper;

    /**
     * Adds a new given book.
     *
     * @param bookDto to add.
     * @return The added book; will never be null.
     * @see Book
     * @see BookDto
     */
    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        book = bookRepository.save(book);

        return bookMapper.toDto(book);
    }

    /**
     * Returns a list of all the books.
     *
     * @return All the books.
     * @see Book
     * @see BookDto
     */
    public List<BookDto> findAllBooks() {
        return bookMapper.toDto(bookRepository.findAll());
    }

    /**
     * Returns a book with a given id.
     *
     * @param id of a book.
     * @return The book with the given id or null if none found.
     * @see Book
     * @see BookDto
     */
    public BookDto findBookById(Integer id) {
        return bookMapper.toDto(bookRepository.findById(id).orElse(null));
    }

    /**
     * Returns a list of books with the given params.
     * If params are null return an empty list.
     *
     * @param bookTitle     the title of a book.
     * @param authorName    the author's name of the book.
     * @param publisherName the publisher's name of the book.
     * @return List of books
     * @see Book
     * @see BookDto
     */
    public List<SearchBookDto> searchBooks(String bookTitle, String authorName, String publisherName) {
        if (bookTitle.isEmpty() && authorName.isEmpty() && publisherName.isEmpty()) {
            return new ArrayList<>();
        }
        return searchBookMapper.toDto(bookRepository.search(bookTitle, authorName, publisherName));
    }

    /**
     * Updates a given book.
     *
     * @param bookDto to update.
     * @return The updated book; will never be null.
     * @see Book
     * @see BookDto
     */
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    /**
     * Deletes a given book.
     *
     * @param bookDto to delete.
     * @see Book
     * @see BookDto
     */
    public void deleteBook(BookDto bookDto) {
        bookRepository.delete(bookMapper.toModel(bookDto));
    }

    /**
     * Deletes a book with a given id
     *
     * @param id of a book.
     * @see Book
     * @see BookDto
     */
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

}
