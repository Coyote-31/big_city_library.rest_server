package com.coyote.big_city_library.rest_server.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exemplary")
@Getter @Setter @NoArgsConstructor
public class Exemplary {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "exemplary")
    private Set<Loan> loans = new HashSet<>();

}
