package com.library.chapter.domain.model;

import com.library.chapter.domain.model.enums.BookCopyStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "books_copy")
public class BookCopyModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel bookModel;

    @Column(nullable = false)
    private Integer catalogCopy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCopyStatusEnum status;
}
