package main.day7;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PromptLines {
    private final List<String> promptLines;
    Directory current;
    Directory root;

    public PromptLines(List<String> promptLines, Directory root) {
        promptLines.remove(0);
        this.promptLines = promptLines;
        this.root = root;
        this.current = root;
    }

    public Directory execute() {
        for (String line : promptLines) {
            if (goTo(line)) {
                if (previous(line)) {
                    current = getParentDir(current);
                } else {
                    current = getSubDir(current, line);
                }
            } else if (isDir(line)) {
                current.addSubDir(getSubDirName(line));
            } else if (isFile(line)) {
                current.addFile(getFileName(line));
            }
        }
        return root;
    }

    private static String getFileName(String line) {
        return line.split(" ")[0];
    }

    private String getSubDirName(String line) {
        return line.replace("dir ", "");
    }

    private Directory getSubDir(Directory currentDir, String cdCommand) {
        String name = cdCommand.replace("$ cd ", "");
        return currentDir.getSubDir(name).isEmpty() ? null : currentDir.getSubDir(name).get();
    }

    private static boolean isFile(String command) {
        return !command.contains("$ ls");
    }

    private static boolean isDir(String command) {
        return command.contains("dir ");
    }

    private static boolean previous(String command) {
        return command.contains("..");
    }

    private static boolean goTo(String command) {
        return command.contains("$ cd");
    }

    private Directory getParentDir(Directory currentDirectory) {
        return currentDirectory.getParent();
    }

}

