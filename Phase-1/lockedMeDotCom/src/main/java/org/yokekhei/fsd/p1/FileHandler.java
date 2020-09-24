package org.yokekhei.fsd.p1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
	
	public FileHandler() {
		File directory = new File(CommonUtils.ROOT_DIRECTORY);
		
		if (!directory.exists()) {
			directory.mkdir();
		}
	}
	
	public TreeSet<String> list() throws FileHandlerException {
		try (Stream<Path> stream = Files.walk(Paths.get(CommonUtils.ROOT_DIRECTORY), 1)) {
			return new TreeSet<>(stream
					.filter(file -> Files.isRegularFile(file))
					.map(Path::getFileName)
					.map(Path::toString)
					.collect(Collectors.toSet()));
		} catch (IOException e) {
			throw new FileHandlerException(e.getMessage());
		}
	}
	
	public void add(String fileName, String content) throws FileHandlerException {
		try (FileWriter fw = new FileWriter(CommonUtils.getFilePath(fileName));
				BufferedWriter bw = new BufferedWriter(fw);
				) {
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			throw new FileHandlerException(e.getMessage());
		}
	}
	
	public void delete(String fileName) {
		System.out.println("delete " + fileName);
	}
	
	public void search(String fileName) {
		System.out.println("search " + fileName);
	}
	
}
