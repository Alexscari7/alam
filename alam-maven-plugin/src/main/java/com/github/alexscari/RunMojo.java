package com.github.alexscari;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PACKAGE)
@Execute(phase = LifecyclePhase.PACKAGE)
public class RunMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}${file.separator}${project.artifactId}-${project.version}.jar")
    private String jarPath;

    @Override
    public void execute() {
        try {
            this.getLog().info("Started:" + this.jarPath);
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", this.jarPath);
            final Process process = builder.start();

            new Thread(() -> {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                try {
                    String s;
                    while ((s = reader.readLine()) != null) {
                        System.out.println(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                RunMojo.this.getLog().info("Destroying...");
                process.destroy();
                RunMojo.this.getLog().info("Shutdown hook finished.");
            }));

            process.waitFor();
            this.getLog().info("Finished.");
        } catch (Exception e) {
            getLog().error(e);
        }

    }
}
