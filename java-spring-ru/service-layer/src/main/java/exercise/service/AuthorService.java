package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private AuthorMapper authorMapper;
    
    public List<AuthorDTO> showAll() {
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getById(Long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id" + id + " not found"))
        );
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        return authorMapper.map(authorRepository.save(authorMapper.map(authorData)));
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO authorData) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id" + id + " not found"));
        authorMapper.update(authorData, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
