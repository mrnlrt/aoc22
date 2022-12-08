package main.day7;

import java.util.List;

public class Day7 {
    public double part1(List<String> promptLines) {
        Directory root = new Directory("/", null);

        root = new PromptLines(promptLines,root)
                .execute();
        return getSmallDirectoriesSize(root,0);
    }

    private double getSmallDirectoriesSize(Directory root, double sum) {
        if(root.getSize()<=100000){
            sum = sum+root.getSize();
        }
        if(!root.getSubDirs().isEmpty()){
            for(Directory subDir: root.getSubDirs()){
                sum = getSmallDirectoriesSize(subDir, sum);
            }
        }
        return sum;
    }


    public double part2(List<String> promptLines) {
        Directory root = new Directory("/", null);

        root = new PromptLines(promptLines,root)
                .execute();
        double unusedSpace = 70000000-root.getSize();
        double spaceToFree = 30000000-unusedSpace;
        return getDirectoryToDelete(root, root.getSize(), spaceToFree);


    }

    private double getDirectoryToDelete(Directory root, double currentSpaceToFree, double spaceToFree) {
        if(root.getSize()>=spaceToFree && root.getSize()<currentSpaceToFree){
            currentSpaceToFree = root.getSize();
        }
        if(!root.getSubDirs().isEmpty()){
            for(Directory subDir: root.getSubDirs()){
                currentSpaceToFree = getDirectoryToDelete(subDir, currentSpaceToFree, spaceToFree);
            }
        }
        return currentSpaceToFree;
    }
}


