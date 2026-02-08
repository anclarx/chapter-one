package com.library.chapter.domain.model;

import com.library.chapter.domain.model.enums.UserPriorityLevelEnum;
import com.library.chapter.domain.model.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "users")
public class UserModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserPriorityLevelEnum priorityLevel;

    @Column(nullable = false)
    private String passwordHash;
}
