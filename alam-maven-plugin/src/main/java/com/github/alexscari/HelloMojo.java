package com.github.alexscari;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {

    @Parameter(property = "alam.username", defaultValue = "alam")
    private String username;

    @Override
    public void execute() {
        getLog().info("Hello " + username);
    }
}
