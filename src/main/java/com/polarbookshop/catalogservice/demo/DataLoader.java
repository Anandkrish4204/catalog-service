package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
//@ConditionalOnProperty(name="polar.testData.enabled", havingValue = "true")
public class DataLoader {

    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        bookRepository.deleteAll();
        var book = Book.of("1234567890","Norther Lights","Lara Craft","Manning",19.9);
        var book2 = Book.of("9078563421","Southern Lights", "Keera Adams","Packt",21.32);
        bookRepository.saveAll(List.of(book,book2));
    }

}
