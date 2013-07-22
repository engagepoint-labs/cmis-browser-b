package com.engagepoint.university.ep2013b.browser;

import com.engagepoint.university.ep2013b.browser.test.WebStories;
import org.jbehave.core.io.StoryFinder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: evgeniy.shevchenko
 * Date: 7/22/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class BrowserComponentIT extends WebStories {

    @Override
    protected List<String> storyPaths() {
        List<String> stories =new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story"), null);
        return stories;
    }
}
