package main.day7;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Directory {
    private String name;
    private List<Directory> subDirs;
    private int filesSize;
    private Directory parent;

    public Directory(String name, Directory parentDirectory) {
        this.name = name;
        this.parent = parentDirectory;
        this.subDirs = new ArrayList<>();
        this.filesSize = 0;
    }

    public void addSubDir(String name){
        subDirs.add(new Directory(name, this));
    }

    public void addFile(String size) {
        filesSize = filesSize + parseInt(size);
    }

    public double getSize() {
        double subDirectoriesSize = subDirs
                .stream()
                .mapToDouble(Directory::getSize)
                .sum();
        return filesSize + subDirectoriesSize;
    }

    public Optional<Directory> getSubDir(String name){
        return subDirs
                .stream()
                .filter(subdir -> name.equals(subdir.getName()))
                .findFirst();
    }
}
