package dev.feryadi.backend.bayu.command;

public interface SupplierCommand<RESPONSE> extends Command{
    RESPONSE execute();
}
