package com.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name="hello", defaultPhase = LifecyclePhase.CLEAN)
public class HelloMojo extends AbstractMojo {
    private void sayHi() {
        System.out.println("");
        System.out.println("hello from plugin");
        System.out.println("");
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        sayHi();
    }
}
