package com.librarymanagement.services;

import com.librarymanagement.entities.Fine;

public interface FineService {
    public Fine calculateFine(int difference);
}
