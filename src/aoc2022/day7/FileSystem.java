package aoc2022.day7;

import java.util.List;

public class FileSystem {
    private final Directory root;
    private Directory currentDir;

    FileSystem() {
        root = new Directory(null, "/");
        currentDir = root;
    }

    FileSystem(Directory root) {
        this.root = root;
        currentDir = root;
    }

    public static FileSystem getView(FileSystem fileSystem) {
        FileSystem newFS = new FileSystem(fileSystem.root);
        newFS.currentDir = fileSystem.currentDir;
        return newFS;
    }

    public void goToDir(String name) {
        if (name.equals(root.getName())) {
            currentDir = root;
            return;
        }
        else if (name.equals("..")) {
            Directory parent = currentDir.getParent();
            if (parent == null) throw new RuntimeException();
            currentDir = parent;
            return;
        }

        Directory dir = findDir(name);
        if (dir == null) throw new RuntimeException();

        currentDir = dir;
    }

    public List<Directory> getSubdirectories() {
        return currentDir.getDirectories();
    }

    public long getDirSize() {
        return currentDir.getSize();
    }

    public void createDir(String name) {
        currentDir.addDirectory(name);
    }

    public void createFile(String name, long size) {
        currentDir.addFile(name, size);
    }

    private Directory findDir(String name) {
        for (Directory dir: currentDir.getDirectories()) {
            if (name.equals(dir.getName())) return dir;
        }

        return null;
    }
}
