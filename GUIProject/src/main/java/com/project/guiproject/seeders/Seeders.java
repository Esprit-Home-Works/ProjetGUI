package com.project.guiproject.seeders;
import java.sql.Connection;

public abstract class Seeders {
	private Connection connection;

	static int count = 5;
	
	public abstract void seed();
}
