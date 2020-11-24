/*
    The book project lets a user keep track of different books they would like to read, are currently
    reading, have read or did not finish.
    Copyright (C) 2020  Karan Kumar

    This program is free software: you can redistribute it and/or modify it under the terms of the
    GNU General Public License as published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful, but WITHOUT ANY
    WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
    PURPOSE.  See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with this program.
    If not, see <https://www.gnu.org/licenses/>.
 */

package com.karankumar.bookproject.backend.service;

import com.karankumar.bookproject.backend.entity.Book;
import com.karankumar.bookproject.backend.entity.Publisher;
import com.karankumar.bookproject.backend.repository.PublisherRepository;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Log
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(
            PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher findById(Long id) {
        return publisherRepository.getOne(id);
    }

    public void addBookToPublisher( Book book, Publisher publisher ) {
        Set<Book> publisherBooks = publisher.getBooks();
        publisherBooks.add(book);
        publisher.setBooks(publisherBooks);
        save(publisher);
    }

    public void save(@NonNull Publisher publisher){
        if(!StringUtils.isEmpty(publisher.getName())){
            publisherRepository.save(publisher);
        }

    }
}
