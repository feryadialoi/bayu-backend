ALTER TABLE transaction_logs
    ADD COLUMN status enum('SUCCESS', 'FAIL') AFTER description,
    ADD COLUMN status_detail VARCHAR(255) AFTER status;