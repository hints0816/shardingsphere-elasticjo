package com.gree.cron.task;

/**
 * Created by 180686 on 2021/8/18 13:41
 */

public final class FooRepositoryFactory {

    private static FooRepository fooRepository = new FooRepository();

    public static FooRepository getFooRepository() {
        return fooRepository;
    }
}