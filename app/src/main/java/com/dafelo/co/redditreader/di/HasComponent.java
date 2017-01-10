package com.dafelo.co.redditreader.di;

/**
 * Created by dafelo on 24/10/16.
 */

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<C> {
    C getComponent();
}
