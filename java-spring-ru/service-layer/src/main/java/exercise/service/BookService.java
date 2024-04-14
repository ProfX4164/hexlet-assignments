package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> showAll() {
        return bookRepository.findAll().stream().map(bookMapper::map).toList();
    }

    public BookDTO getById(Long id) {
        return bookMapper.map(bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id" + id + " not found"))
        );
    }

    public BookDTO create(BookCreateDTO bookData) {
        return bookMapper.map(bookRepository.save(bookMapper.map(bookData)));
    }

    public BookDTO update(Long id, BookUpdateDTO bookData) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id" + id + " not found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
