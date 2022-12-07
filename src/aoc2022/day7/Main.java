package aoc2022.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day7/Outputs.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static long partOne(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            FileSystem fileSystem = new FileSystem();

            String line;
            while ((line = reader.readLine()) != null) {
                interpretInput(fileSystem, line);
            }

            fileSystem.goToDir("/");
            return sumSmallDirs(fileSystem, 100000);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static long partTwo(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            FileSystem fileSystem = new FileSystem();

            String line;
            while ((line = reader.readLine()) != null) {
                interpretInput(fileSystem, line);
            }

            long totalSize = 70000000;
            fileSystem.goToDir("/");
            long fileSystemSize = fileSystem.getDirSize();
            long updateSize = 30000000;

            return findSmallestMatchingDir(fileSystem, -(totalSize - fileSystemSize - updateSize));
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static long findSmallestMatchingDir(FileSystem fileSystem, long limit) {
        return findSmallestMatchingDir(fileSystem, limit, fileSystem.getDirSize());
    }

    private static long findSmallestMatchingDir(FileSystem fileSystem, long limit, long current) {
        for (Directory dir: fileSystem.getSubdirectories()) {
            long dirSize = dir.getSize();
            if (dirSize < limit) continue;
            if (dirSize < current) current = dirSize;

            FileSystem view = FileSystem.getView(fileSystem);
            view.goToDir(dir.getName());
            current = Long.min(findSmallestMatchingDir(view, limit, current), current);
        }
        return current;
    }

    private static void interpretInput(FileSystem fileSystem, String line) {
        String regex = "^\\$ .*$";

        if (line.matches(regex)) interpretCommand(fileSystem, line);
        else interpretListing(fileSystem, line);
    }

    private static void interpretCommand(FileSystem fileSystem, String line) {
        if (line.equals("$ ls")) return;

        Pattern pattern = Pattern.compile("^\\$ cd (.+)$");
        Matcher matcher = pattern.matcher(line);
        if (! matcher.find()) throw new RuntimeException();

        String dirName = matcher.group(1);
        fileSystem.goToDir(dirName);
    }

    private static void interpretListing(FileSystem fileSystem, String line) {
        String fileListingRegex = "^([1-9]\\d+) ([a-zA-Z_].*)$";
        String dirListingRegex = "^dir ([a-zA-Z_].*)$";

        Matcher filePattern = Pattern.compile(fileListingRegex).matcher(line);
        Matcher dirPattern = Pattern.compile(dirListingRegex).matcher(line);

        if (filePattern.find()) {
            long size = Long.parseLong(filePattern.group(1));
            String name = filePattern.group(2);
            fileSystem.createFile(name, size);
            return;
        }
        else if (dirPattern.find()) {
            String name = dirPattern.group(1);
            fileSystem.createDir(name);
            return;
        }

        throw new RuntimeException();
    }

    private static long sumSmallDirs(FileSystem fileSystem, long limit) {
        long sum = 0;
        for (Directory dir: fileSystem.getSubdirectories()) {
            long dirSize = dir.getSize();
            if (dirSize <= limit) sum += dirSize;

            FileSystem view = FileSystem.getView(fileSystem);
            view.goToDir(dir.getName());
            sum += sumSmallDirs(view, limit);
        }

        return sum;
    }
}
