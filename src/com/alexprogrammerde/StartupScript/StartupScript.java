package com.alexprogrammerde.StartupScript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StartupScript extends JavaPlugin implements Listener {
	
    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
        	ProcessBuilder processBuilder = new ProcessBuilder();
        	
        	processBuilder.command("bash", "-c", "python boot.py");
        	
        	try {

        		Process process = processBuilder.start();

        		StringBuilder output = new StringBuilder();

        		BufferedReader reader = new BufferedReader(
        				new InputStreamReader(process.getInputStream()));

        		String line;
        		while ((line = reader.readLine()) != null) {
        			output.append(line + "\n");
        		}

        		int exitVal = process.waitFor();
        		if (exitVal == 0) {
        			System.out.println("Success!");
        			System.out.println(output);
        			System.exit(0);
        		} else {
        		}

        	} catch (IOException e) {
        		e.printStackTrace();
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }	
        });
    }
}