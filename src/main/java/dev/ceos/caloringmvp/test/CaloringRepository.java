package dev.ceos.caloringmvp.test;

import dev.ceos.caloringmvp.test.Caloring;

public interface CaloringRepository {
    Caloring findById(Long id);

    int updateCalor(Caloring caloring);

    void attack(Caloring caloring);

    void reset(Caloring caloring);

}
