package aoc2022.day7;

import java.util.LinkedList;
import java.util.List;

public class Directory {
    private final List<File> files;
    private final List<Directory> directories;
    private final Directory parent;
    private String name;

    Directory(Directory parent, String name) {
        files = new LinkedList<>();
        directories = new LinkedList<>();
        this.parent = parent;
        this.name = name;
    }

    public void addFile(String fileName, long size) {
        files.add(new File(fileName, size));
    }

    public void addDirectory(String dirName) {
        directories.add(new Directory(this, dirName));
    }

    public long getSize() {
        long sum = 0;
        for (File file: files) {
            sum += file.getSize();
        }

        for (Directory dir: directories) {
            sum += dir.getSize();
        }

        return sum;
    }

    public String getName() {
        return name;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public Directory getParent() {
        return parent;
    }
}
