package org.yokekhei.fsd.p1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
	
	public void delete(String fileName) throws FileHandlerException {
		// Case sensitivity file name handling
		if (!list().contains(fileName)) {
			throw new FileHandlerException("'" + fileName + "' is not found in the directory.");
		}
		
		File file = new File(CommonUtils.getFilePath(fileName));
		
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			throw new FileHandlerException(e.getMessage());
		}
	}
	
	public String search(String fileName) throws FileHandlerException {
		int index = -1;
		List<File> files = new ArrayList<>();
		
		try (Stream<Path> stream = Files.walk(Paths.get(CommonUtils.ROOT_DIRECTORY))) {
			files = stream
						.filter(file -> Files.isRegularFile(file))
						.map(Path::toFile)
						.collect(Collectors.toList());
			
			FileNameComparator c = new FileNameComparator();
			Algorithms.sort(files, c);
			index = Algorithms.binarySearch(files, new File(fileName), c);
			
			if (index < 0) {
				throw new FileHandlerException("'" + fileName + "' is not found in the directory.");
			}
		} catch (IOException e) {
			throw new FileHandlerException(e.getMessage());
		}
		
		return files.get(index).getAbsolutePath();
	}
	
}
