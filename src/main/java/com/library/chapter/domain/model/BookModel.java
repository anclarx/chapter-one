package com.library.chapter.domain.model;

import com.library.chapter.domain.validation.BookValidation;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "books")
public class BookModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    private String isbn;

    @Valid
    @ConvertGroup(from = Default.class, to = BookValidation.class)
    @NotNull
    @JoinTable(
            name = "books_authors_relationship",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<AuthorModel> authors;

    @Size(min = 3, max = 255)
    private String publisher;
    private String description;

    private Integer edition;
    private Integer pages;
    private LocalDate publicationDate;
}
