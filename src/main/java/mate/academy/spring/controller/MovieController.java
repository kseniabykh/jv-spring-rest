package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.mapper.MovieMapper;
import mate.academy.spring.model.dto.MovieRequestDto;
import mate.academy.spring.model.dto.MovieResponseDto;
import mate.academy.spring.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    public final MovieService movieService;
    public final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream().map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponseDto create(@RequestBody MovieRequestDto requestDto) {
        return movieMapper.toDto(movieService.add(movieMapper.toModel(requestDto)));
    }
}