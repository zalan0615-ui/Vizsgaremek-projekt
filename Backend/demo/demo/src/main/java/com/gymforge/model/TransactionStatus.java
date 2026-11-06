package com.gymforge.model;

//----------------------------------------------------------------------//
// TransactionStatus: a tranzakció állapota (az adatbázis ENUM értékeivel egyezik)

public enum TransactionStatus {
    pending,
    completed,
    failed,
    refunded
}

//----------------------------------------------------------------------//

